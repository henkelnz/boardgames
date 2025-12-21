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
    
    "Upgrade power die" {
        var powerDie = Die(DieType.BLACK)
        powerDie.upgrade()
        powerDie.type shouldBe DieType.SILVER
        
        val silverDieSides = DieDefs.sidesMap.getValue(DieType.SILVER).toSet()
        var uniqueOutputs = HashSet<DieSide>()
        var tries = 0
        while (uniqueOutputs.size < silverDieSides.size && tries < 1000) {
            powerDie.roll()
            uniqueOutputs.add(powerDie.currentSide())
            ++tries
        }
        uniqueOutputs shouldBe silverDieSides.toSet()
        
        powerDie.upgrade()
        powerDie.type shouldBe DieType.GOLD

        val goldDieSides = DieDefs.sidesMap.getValue(DieType.GOLD).toSet()
        uniqueOutputs = HashSet<DieSide>()
        tries = 0
        while (uniqueOutputs.size < goldDieSides.size && tries < 1000) {
            powerDie.roll()
            uniqueOutputs.add(powerDie.currentSide())
            ++tries
        }
        uniqueOutputs shouldBe goldDieSides.toSet()
    }
})
