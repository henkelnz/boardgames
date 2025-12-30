package dark_journey

import kotlin.random.Random

/**
 * Represents a six-sided die in Dark Journey.
 *
 * @property type The die type (e.g., "Red", "Blue", "White")
 * @property sides The six sides of the die
 * @property facingSide Index (0–5) of the currently facing side
 */

enum class DieType {
    RED, BLUE, WHITE, GREEN, YELLOW, BLACK, SILVER, GOLD, STEALTH
}

data class DieSide(
    val miss: Boolean = false,
    val power: Int = 0,
    val range: Int = 0,
    val surges: Int = 0,
    val damage: Int = 0
)

object DieDefs {
    val redDieSides = arrayOf(
        DieSide(miss = true),
        DieSide(damage = 4),
        DieSide(damage = 3, range = 1, surges = 1),
        DieSide(damage = 1, range = 2, surges = 1),
        DieSide(damage = 2, range = 2),
        DieSide(damage = 3, range = 1)
    )
    
    val blueDieSides = arrayOf(
        DieSide(miss = true),
        DieSide(damage = 2, range = 1),
        DieSide(damage = 2, range = 2),
        DieSide(damage = 1, range = 3, surges = 1),
        DieSide(damage = 1, range = 3, surges = 1),
        DieSide(damage = 0, range = 4, surges = 1)
    )
    
    val whiteDieSides = arrayOf(
        DieSide(miss = true),
        DieSide(damage = 3, range = 1, surges = 1),
        DieSide(damage = 3, range = 1, surges = 1),
        DieSide(damage = 2, range = 2),
        DieSide(damage = 1, range = 3, surges = 1),
        DieSide(damage = 1, range = 3, surges = 1)
    )
    
    val yellowDieSides = arrayOf(
        DieSide(damage = 1, range = 1),
        DieSide(damage = 1, range = 2),
        DieSide(range = 2, surges = 1),
        DieSide(range = 2, surges = 1),
        DieSide(range = 3),
        DieSide(range = 3)
    )

    val greenDieSides = arrayOf(
        DieSide(damage = 3),
        DieSide(damage = 3),
        DieSide(damage = 2, surges = 1),
        DieSide(damage = 2, surges = 1),
        DieSide(damage = 1, range = 1),
        DieSide(damage = 2, range = 1)
    )
    
    val blackDieSides = arrayOf(
        DieSide(),
        DieSide(surges=1),
        DieSide(surges=1),
        DieSide(power=1),
        DieSide(power=1),
        DieSide(power=1)
    )
    
    val silverDieSides = arrayOf(
        DieSide(),
        DieSide(surges=2),
        DieSide(surges=2),
        DieSide(power=2),
        DieSide(power=2),
        DieSide(power=2)
    )
    
    val goldDieSides = arrayOf(
        DieSide(),
        DieSide(surges=3),
        DieSide(surges=3),
        DieSide(power=3),
        DieSide(power=3),
        DieSide(power=3)
    )
    
    val stealthDieSides = arrayOf(
        DieSide(miss=true),
        DieSide(miss=true),
        DieSide(),
        DieSide(),
        DieSide(),
        DieSide()
    )
    
    val sidesMap = mapOf(
        DieType.RED to redDieSides,
        DieType.BLUE to blueDieSides,
        DieType.WHITE to whiteDieSides,
        DieType.GREEN to greenDieSides,
        DieType.YELLOW to yellowDieSides,
        DieType.BLACK to blackDieSides,
        DieType.SILVER to silverDieSides,
        DieType.GOLD to goldDieSides,
        DieType.STEALTH to stealthDieSides
    )
}

class Die(var type : DieType) {
    companion object {
        fun get(types : Collection<DieType>) : ArrayList<Die> {
            var dice = ArrayList<Die>()
            for (type in types) {
                dice.add(Die(type))
            }
            return dice
        }
        
        fun types(dice : Collection<Die>) : ArrayList<DieType> {
            var types = ArrayList<DieType>()
            for (die in dice) {
                types.add(die.type)
            }
            return types
        }
        
        fun toString(dice : Collection<Die>) : String {
            return dice.joinToString()
        }
    }

    //var type: DieType
    //    private set
    var facingSide: Int = 0
        private set
    var sides: Array<DieSide>
        private set

    init {
        sides=DieDefs.sidesMap.getValue(type)
        require(sides.size == 6) { "A die must have exactly 6 sides" }
    }
    
    fun upgrade() {
        if (type == DieType.BLACK) {
            sides = DieDefs.sidesMap.getValue(DieType.SILVER)
            type = DieType.SILVER
        }
        else if (type == DieType.SILVER) {
            sides = DieDefs.sidesMap.getValue(DieType.GOLD)
            type = DieType.GOLD
        }
    }

    /**
     * Rolls the die, randomly selecting one of the six sides.
     *
     * @return The DieSide that is now facing up
     */
    fun roll(): DieSide {
        facingSide = Random.nextInt(sides.size)
        return sides[facingSide]
    }
    
    fun roll(player : Player) : DieSide {
        facingSide = FairRandom.result(player, type)
        return sides[facingSide]
    }
    
    fun setSide(sideIndex: Int) {
        facingSide = sideIndex
    }
    
    fun isPower() : Boolean {
        if (type in listOf(DieType.BLACK, DieType.SILVER, DieType.GOLD))
            return true
        else 
            return false
    }

    /**
     * Convenience accessor for the currently facing side.
     */
    fun currentSide(): DieSide = sides[facingSide]
    
    override fun toString(): String {
        return "$type die"
    }
}
