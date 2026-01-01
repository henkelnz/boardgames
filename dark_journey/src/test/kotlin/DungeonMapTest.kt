package dark_journey

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DungeonMapTest : StringSpec({
    
    "Loading from file produces expected grid" {
        val expWad = arrayOf(
            "..OO...OO.....".toCharArray(),
            ".OOORLOOOOO...".toCharArray(),
            "OOOOOOOOOOrlOO".toCharArray(),
            "OOOOOOOOOOrlOO".toCharArray(),
            ".OOORLOOOOO...".toCharArray(),
            "..OO...OO.....".toCharArray(),
            "..OO..........".toCharArray(),
            "..OO..........".toCharArray(),
            "..OO..........".toCharArray(),
            "..OO..........".toCharArray()
        )
        expWad.forEach{println(it)}
        val expSqc = arrayOf(
            "..GO...OU.....".toCharArray(),
            ".OOOOOOOOOO...".toCharArray(),
            "OOOOOOOOOOOEEE".toCharArray(),
            "OOOOOOOOOOOEEE".toCharArray(),
            ".OOBOOOOOOO...".toCharArray(),
            "..OO...OC.....".toCharArray(),
            "..OO..........".toCharArray(),
            "..OO..........".toCharArray(),
            "..OO..........".toCharArray(),
            "..OA..........".toCharArray()
        )
        expSqc.forEach{println(it)}
        
        val expected  = DungeonMap(expWad, expSqc)
        
        val map = DungeonMap.load("testMap1.wad", "testMap1.sqc")
        
        map.wad shouldBe expected.wad
        map.sqc shouldBe expected.sqc
    }
    
    "Calculate supercover squares" {
        var sp = Pair(1,1)
        var ep = Pair(12,9)
        var expectedSquares = arrayListOf(
            Pair(1,1), Pair(2,1), Pair(2,2), Pair(3,2), Pair(3,3), Pair(4,3), Pair(4,4), Pair(5,4), Pair(6,4),
            Pair(6,5), Pair(7,5), Pair(7,6), Pair(8,6), Pair(9,6), Pair(9,7), Pair(10,7), Pair(10,8), Pair(11, 8),
            Pair(11,9), Pair(12,9)
        )
        var squares = DungeonMap.superCoverLine(sp, ep)
        squares shouldBe expectedSquares
        
        sp = Pair(1,1)
        ep = Pair(4,2)
        expectedSquares = arrayListOf(
            Pair(1,1), Pair(2,1), Pair(3,2), Pair(4,2)
        )
        squares = DungeonMap.superCoverLine(sp, ep)
        squares shouldBe expectedSquares
    }
    
    "Calculate adjacency, LOS, and distance from square" {
        val map = DungeonMap.load("testMap1.wad", "testMap1.sqc")
        
        val a = Pair(5, 3)
        
        map.areAdjacent(a, Pair(4,2)) shouldBe true
        map.areAdjacent(a, Pair(3,3)) shouldBe false
        
        
    }
})