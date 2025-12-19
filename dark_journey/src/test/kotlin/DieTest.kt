package dark_journey

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain

class DieTest : StringSpec({

    "Repeated rolls produce all sides" {
        for (dieType in DieType.entries) {
            var dieSides = DieDefs.sidesMap.getValue(dieType).toSet()
            var uniqueOutputs = HashSet<DieSide>()
            var tries = 0
            while (uniqueOutputs.size < dieSides.size && tries < 1000) {
                var die = Die(dieType)
                die.roll()
                uniqueOutputs.add(die.currentSide())
                ++tries
            }
            uniqueOutputs shouldBe dieSides.toSet()
            println("All sides rolled in $tries tries on $dieType")
        }    
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
