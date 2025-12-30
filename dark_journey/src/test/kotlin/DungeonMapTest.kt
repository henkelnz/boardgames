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
})