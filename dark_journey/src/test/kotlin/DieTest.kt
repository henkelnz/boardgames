package dark_journey

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.assertions.throwables.shouldThrow

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
})
