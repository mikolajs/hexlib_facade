package eu.brosbit.hexlib

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, Queue}
case class MapPositionWithDistance(pos:MapPosition, dist:Int)
class PathFinder(hex:Hex): 

  def findPath(from:MapPosition, to:MapPosition, map:Array[Array[Int]]):List[MapPosition] =
    val checked = Array.ofDim[Int](map.length, map(0).length)
    for i <- 0 until checked.length
        j <- 0 until checked(0).length do
      checked(i)(j) = Int.MaxValue

    val stack = mutable.Stack[MapPosition]()
    checked(from.r)(from.c) = 0
    stack.push(from)
    while stack.nonEmpty do
      val point = stack.pop()
      hex.neighbours(point).foreach( n =>
        if map(n.r)(n.c) > -1 && checked(n.r)(n.c) >  checked(point.r)(point.c) + map(n.r)(n.c) then
          checked(n.r)(n.c) = checked(point.r)(point.c) + map(n.r)(n.c)
          stack.push(n)
      )
    //printMap(checked)
    if checked(to.r)(to.c) == Int.MaxValue then List()
    else takePath(from:MapPosition, to:MapPosition, checked:Array[Array[Int]])

  private def takePath(from: MapPosition, to: MapPosition, map: Array[Array[Int]]):List[MapPosition] =
    var path:List[MapPosition] = Nil
    path = to :: path
    while true do
      val ns = hex.neighbours(path.head)
      val mpMin = ns.minBy(mp => map(mp.r)(mp.c))
      if mpMin.r == from.r && mpMin.c == from.c then return path
      else path = mpMin :: path
    path

  private def printMap(map:Array[Array[Int]]):Unit =
    map.foreach(ar =>
      ar.foreach(p =>
        val n = if p == Int.MaxValue then "-1 "
          else if p > 9 then p + " " else p + "  "
        print(n)
      )
      println()
    )
  def countPath(path:List[MapPosition], map:Array[Array[Int]]):Int = path.foldLeft(0)((a, mp) => a + map(mp.r)(mp.c))

end PathFinder

