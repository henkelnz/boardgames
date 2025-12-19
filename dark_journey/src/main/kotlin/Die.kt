package dark_journey

import kotlin.random.Random

/**
 * Represents a six-sided die in Dark Journey.
 *
 * @property color The die color (e.g., "Red", "Blue", "White")
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
    val wounds: Int = 0
)

object DieDefs {
    val redDieSides = arrayOf(
        DieSide(miss = true),
        DieSide(wounds = 4),
        DieSide(wounds = 3, range = 1, surges = 1),
        DieSide(wounds = 1, range = 2, surges = 1),
        DieSide(wounds = 2, range = 2),
        DieSide(wounds = 3, range = 1)
    )
    
    val blueDieSides = arrayOf(
        DieSide(miss = true),
        DieSide(wounds = 2, range = 1),
        DieSide(wounds = 2, range = 2),
        DieSide(wounds = 1, range = 3, surges = 1),
        DieSide(wounds = 1, range = 3, surges = 1),
        DieSide(wounds = 0, range = 4, surges = 1)
    )
    
    val whiteDieSides = arrayOf(
        DieSide(miss = true),
        DieSide(wounds = 3, range = 1, surges = 1),
        DieSide(wounds = 3, range = 1, surges = 1),
        DieSide(wounds = 2, range = 2),
        DieSide(wounds = 1, range = 3, surges = 1),
        DieSide(wounds = 1, range = 3, surges = 1)
    )
    
    val yellowDieSides = arrayOf(
        DieSide(wounds = 1, range = 1),
        DieSide(wounds = 1, range = 2),
        DieSide(range = 2, surges = 1),
        DieSide(range = 2, surges = 1),
        DieSide(range = 3),
        DieSide(range = 3)
    )

    val greenDieSides = arrayOf(
        DieSide(wounds = 3),
        DieSide(wounds = 3),
        DieSide(wounds = 2, surges = 1),
        DieSide(wounds = 2, surges = 1),
        DieSide(wounds = 1, range = 1),
        DieSide(wounds = 2, range = 1)
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

class Die(
    val type: DieType
) {
    companion object {
        fun get(types : Collection<DieType>) : ArrayList<Die> {
            var dice = ArrayList<Die>()
            for (type in types) {
                dice.add(Die(type))
            }
            return dice
        }
        
        fun toString(dice : Collection<Die>) : String {
            return dice.joinToString()
        }
    }

    var facingSide: Int = 0
        private set
    var sides: Array<DieSide>
        private set

    init {
        sides=DieDefs.sidesMap.getValue(type)
        require(sides.size == 6) { "A die must have exactly 6 sides" }
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

    /**
     * Convenience accessor for the currently facing side.
     */
    fun currentSide(): DieSide = sides[facingSide]
    
    override fun toString(): String {
        return "$type die"
    }
}
