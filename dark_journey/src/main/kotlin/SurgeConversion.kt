package dark_journey

import kotlin.text.Regex

class SurgeConversion(val cost : Int, val abilityType : SpecialAbilityType, val level : Int = 0) {
    companion object {
        fun fromString(string : String) : SurgeConversion {
            val m = Regex("(\\d+)_for_(.+)").find(string)
            if (m != null) {
                val (c, a) = m.destructured
                val specialAbility = SpecialAbility.fromString(a)
                return SurgeConversion(c.toInt(), specialAbility.type, specialAbility.level)
            }
            else
                error("Invalid surge conversion format")
        }
    }
    
    fun spend(surges : Int) : SpecialAbility {
        if(level == 0) {
            check(surges == cost) {"spending more surges than are allowed to be spent"}
        }
        check(surges % cost == 0) {"surges spent must be a multiple of cost"}
        return(SpecialAbility(abilityType, surges/cost))
    }
}