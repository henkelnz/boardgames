package dark_journey

import kotlin.math.*

data class DungeonMap(val wad : Array<CharArray>, val sqc : Array<CharArray>) {
    companion object {
        fun load(wadRsc : String, sqcRsc : String) : DungeonMap {
            val inAry = arrayOf(wadRsc, sqcRsc)
            val outAry = arrayOf(ArrayList<CharArray>(), ArrayList<CharArray>())
            for (i in 0..1) {
                // Reads file from resource
                var resourceName = inAry[i]
                val inputStream = Thread.currentThread()
                    .contextClassLoader
                    .getResourceAsStream(resourceName)
                    ?: error("Could not find resource: $resourceName")
                //println(inputStream?.bufferedReader()?.readText())
                val br = inputStream?.bufferedReader()
                val md = ArrayList<CharArray>()
                var line = br?.readLine()
                while (line != null) {
                    outAry[i].add(line.toCharArray()!!)
                    line = br?.readLine()
                }
            }    
            
            return DungeonMap(outAry[0].toTypedArray(), outAry[1].toTypedArray())
        }
    }
    
    fun areAdjacent(a : Pair<Int, Int>, b : Pair<Int, Int>) : Boolean {
        // TODO: handle walls & doors? or let LOS handle that (probably)
        if (abs(a.first - b.first) <= 1 && abs(a.second - b.second) <= 1)
            return true
        else
            return false
    }
    
            
    fun superCoverLine(a : Pair<Int, Int>, b: Pair<Int, Int>, los : String = "NoCheck") : ArrayList<Pair<Int, Int>> {
        var squares = ArrayList<Pair<Int, Int>>()
        val dx = (b.first - a.first).toDouble()
        val dy = (b.second - a.second).toDouble()
        val nx = abs(dx)
        val ny = abs(dy)
        val signX = sign(dx)
        val signY = sign(dy)
        var p = arrayOf(a.first.toDouble(), a.second.toDouble())
        squares.add(Pair(a.first, a.second))
        var ix = 0.0
        var iy = 0.0
        priorSquare = squares[0]
        while(ix < nx && iy < ny) {
            val mvx = (0.5 + ix) / nx
            val mvy = (0.5 + iy) / ny
            if (mvx <= mvy) {
                p[0] += signX
                ++ix
            }
            if (mvy <= mvx) {
                p[1] += signY
                ++iy
            }
            val newSquare = Pair(p[0].toInt(), p[1].toInt())
            if (los != "NoCheck") {
                if blocksLos(priorSquare, newSquare, los) {
                    return squares
                }
            }
            squares.add(newSquare)
            priorSquare = newSquare
        }
        if (b !in squares)
            squares.add(b)
        return squares
    }
    
    fun blocksLos(firstSquare, secondSquare, losType) {
        
    }

    /*
    fun losBlocker(priorSpace : IntArray, space : IntArray) : Boolean {
        if (sqc[space.first][space.second] in arrayOf('B','.')) {
            return true
        }
        return false
    }
    */
    /*
     function walk_grid(p0, p1) {
  let dx = p1.x - p0.x;
  let dy = p1.y - p0.y;
  let nx = Math.abs(dx); // Steps in X
  let ny = Math.abs(dy); // Steps in Y
  let sign_x = dx > 0 ? 1 : -1;
  let sign_y = dy > 0 ? 1 : -1;
  let p = {x: p0.x, y: p0.y};
  let points = [{x: p.x, y: p.y}];
  for (let ix = 0, iy = 0; ix < nx || iy < ny;) {
    // Compare distance to next vertical edge vs. next horizontal edge
    if ((0.5 + ix) / nx < (0.5 + iy) / ny) { // Next step is horizontal (to next column)
      p.x += sign_x;
      ix++;
    } else { // Next step is vertical (to next row)
      p.y += sign_y;
      iy++;
    }
    points.push({x: p.x, y: p.y});
  }
  return points;
}
     */
}