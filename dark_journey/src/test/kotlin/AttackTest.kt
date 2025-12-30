package dark_journey

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain

class AttackTest : StringSpec({
    
    "Ranged Attack" {
        val crossbow = WeaponDefs.weaponGroup("Shop").weapon("Crossbow")
        val powerDieTypes = arrayListOf(DieType.BLACK, DieType.BLACK)
        
        val attack = Attack(crossbow.dieTypes, powerDieTypes, crossbow.surgeConversions())
        val expectedNonPowerDieTypes = arrayListOf(DieType.BLUE, DieType.GREEN)
        for (i in 0..<expectedNonPowerDieTypes.size) {
            attack.nonPowerDice[i].type shouldBe expectedNonPowerDieTypes[i]
        }
        val nonPowerDice = attack.nonPowerDice
        nonPowerDice[0].setSide(3) // d=1, r=3, s=1
        nonPowerDice[1].setSide(2) // d=2, s=1
        val powerDice = attack.powerDice
        powerDice[0].setSide(3) // p=1
        powerDice[1].setSide(1) // s=1
        var expectedSummary = AttackSummary(damage=3,range=3,surges=3,power=1)
        attack.summarize() shouldBe expectedSummary
        
        attack.powerToDamage(0)
        expectedSummary = AttackSummary(damage=4,range=3,surges=3,power=0)
        attack.summarize() shouldBe expectedSummary
        
        attack.spendSurges(3, 1)
        expectedSummary = AttackSummary(damage=4,range=4,surges=0,power=0)
        attack.summarize() shouldBe expectedSummary
    }
})