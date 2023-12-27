package eu.brosbit.hexlibjs

import eu.brosbit.hexlib.*

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import scalajs.js
import js.JSConverters.*
import js.Array

@JSExportTopLevel("Hex")
class HexJS(rows:Int, cols:Int) extends Hex(rows, cols){}

@JSExportTopLevel("PathFinder")
class PathFinderJS(hex:Hex) extends PathFinder(hex){}

@JSExportTopLevel("HexLibrary")
class HexLibrary(hex:Hex, pathFinder: PathFinder):
  /*private var hex: Hex = Hex(10, 10)
  private var pathFinder: PathFinder = PathFinder(hex)

  def main(args: Array[String]): Unit =
    println("Hello hexlib")
  @JSExport
  def init(rows:Int, cols:Int) =
    hex = Hex(rows, cols)
    pathFinder = PathFinder(hex)
    */
  @JSExport
  def distance(r1:Int, c1:Int, r2:Int, c2:Int):Int = hex.distance(MapPosition(r1, c1), MapPosition(r2, c2))
  @JSExport
  def neighbours(row:Int, col:Int, distance:Int):js.Array[js.Array[Int]] =
    hex.neighbours(MapPosition(row, col), distance).map(mp => js.Array(mp.r, mp.c)).toJSArray

  @JSExport
  def findPath(r1:Int, c1:Int, r2:Int, c2:Int, map:js.Array[js.Array[Int]]):js.Array[js.Array[Int]] =
     pathFinder.findPath(MapPosition(r1, c1), MapPosition(r2, c2), map.map(arr => arr.toArray).toArray)
       .map(mp => js.Array(mp.r, mp.c)).toJSArray
  @JSExport
  def countPath(path:js.Array[js.Array[Int]], map:js.Array[js.Array[Int]]) =
    pathFinder.countPath(path.map(arr => MapPosition(arr(0), arr(1))).toList, map.map(_.toArray).toArray)

end HexLibrary
