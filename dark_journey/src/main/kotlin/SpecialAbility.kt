package dark_journey

import kotlin.text.Regex

enum class SpecialAbilityType {ADD_DAMAGE, ADD_FATIGUE, ADD_NOTHING, ADD_RANGE, ADD_SURGES, BLAST, BREATH}

data class SpecialAbility(val type : SpecialAbilityType, val level : Int) {
    companion object {
        fun fromString(string : String) : SpecialAbility {
            val m = Regex("\\+(\\d+)(D|R)").find(string)
            if (m != null) {
                val (s,dr) = m.destructured
                val i = s.toInt()
                if (dr == "D") 
                    return SpecialAbility(SpecialAbilityType.ADD_DAMAGE, i) 
                else if (dr == "R") 
                    return SpecialAbility(SpecialAbilityType.ADD_RANGE, i)
                else
                    error("Unknown special ability $string")
            }
            else
                error("Unknown special ability $string")
        }
    }
    
    fun apply(attack : Attack) {
        when(type) {
            SpecialAbilityType.ADD_DAMAGE -> attack.extraDamage += level
            SpecialAbilityType.ADD_RANGE -> attack.extraRange += level
            else -> error("not yet implemented")
        }
    }
}