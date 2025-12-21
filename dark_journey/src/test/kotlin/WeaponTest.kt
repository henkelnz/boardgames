package dark_journey

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class WeaponTest : StringSpec({
    "Get weapon dice" {
        val pwd = System.getProperty("user.dir")
        println(pwd)
        val weapons = WeaponDefs.allWeapons()
        println(weapons)
    }
    
})