package dark_journey

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain

class DieTest : StringSpec({

    "Red die: repeated rolls produce all sides" {
        val redDieSides = DieDefs.redDieSides
        var uniqueOutputs = HashSet<DieSide>()
        var tries = 0
        while (uniqueOutputs.size < redDieSides.size && tries < 1000) {
            var die = Die(DieType.RED)
            die.roll()
            uniqueOutputs.add(die.currentSide())
            ++tries
        }
        uniqueOutputs shouldBe redDieSides.toSet()
        println("All 6 sides rolled in $tries tries")
    }
    
    "Get a set of dice from types" {
        val dieTypes = ArrayList<DieType>()
        dieTypes.addAll(DieType.entries)
        val dice = Die.get(dieTypes)
        dice.size shouldBe dieTypes.size
        for (t in dieTypes) {
            Die.toString(dice) shouldContain "$t die"
        }
    }
})
