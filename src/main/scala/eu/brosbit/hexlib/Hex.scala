package eu.brosbit.hexlib

case class CubePoint(x:Int, y:Int, z:Int)

case class MapPosition(var r:Int, var c:Int)

class Hex(rows:Int, cols:Int) {

///TODO: eliminate round world
  def distance(from:MapPosition, to: MapPosition, round:Boolean = false):Int =
    if round && (from.c - to.c).abs > cols/2 then
      if to.c > from.c then distanceFromCubes(coordinateToCube(from), coordinateToCube(MapPosition(to.r, to.c-cols)))
      else distanceFromCubes(coordinateToCube(from), coordinateToCube(MapPosition(to.r, to.c+cols)))
    else  distanceFromCubes(coordinateToCube(from), coordinateToCube(to))


  def neighbours(position: MapPosition, distance: Int = 1, round:Boolean = false): List[MapPosition] = {
    val cubePoints = (for (z <- -distance to distance; x <- -distance to distance) yield {
      val y = -x - z
      CubePoint(x, y, z)
    }).filter(cp => cp.y.abs <= distance && (cp.x != 0 || cp.z != 0))
    //println(cubePoints)
    //println(position)
    //println(cubePoints.map(cubeToCoordinate(_)))
    val hexPoint = cubePoints.map(cubeToCoordinate(_))
    val hexPoint2 = if round then  hexPoint.map(c =>
      MapPosition(position.r + c.r, (position.c + c.c + (position.r & 1 & c.r) + cols) % cols))
    else hexPoint.map(c =>
      MapPosition(position.r + c.r, position.c + c.c + (position.r & 1 & c.r) ))
    hexPoint2.filter(mp => mp.r < rows && mp.r > -1 && mp.c > -1 && mp.c < cols).toList
  }
  
  private def coordinateToCube(p: MapPosition):CubePoint = {
    val x = p.c - (p.r - (p.r & 1))/2
    CubePoint(x, -x - p.r, p.r)
  }

  private def distanceFromCubes(from:CubePoint, to:CubePoint):Int =
      ((from.x - to.x).abs + (from.y - to.y).abs + (from.z - to.z).abs)/2

  private def cubeToCoordinate(c:CubePoint):MapPosition = {
    MapPosition(c.z, c.x + (c.z - (c.z&1))/2)
  }
}

