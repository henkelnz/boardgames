package dark_journey

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.assertions.throwables.shouldThrow

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
})
