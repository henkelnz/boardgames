package dark_journey

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.assertions.throwables.shouldThrow

class WeaponTest : StringSpec({
    "Get weapon dice" {
        val shop = WeaponDefs.weaponGroup("Shop")
        println(shop)
        
        val sword = shop.weapon("Sword")
        val expected = listOf(DieType.RED, DieType.GREEN)
        sword.dieTypes shouldBe expected
        
        val axe = shop.weapon("Axe")
        axe.hands shouldBe 2
        axe.offhand shouldBe listOf<String>()
        
        val sunburst = shop.weapon("Sunburst")
        sunburst.surgeopt shouldBe listOf("3_for_+1D", "3_for_BLAST_1")
    }
    
    "Invalid weapon group" {
        shouldThrow<IllegalStateException> {
            val wg = WeaponDefs.weaponGroup("Bad")    
        }
    }
    
    "Invalid weapon" {
        shouldThrow<IllegalStateException> {
            val wg = WeaponDefs.weaponGroup("Shop").weapon("Bad")    
        }
    }
    
})