import kotlin.random.Random

/**
 * Represents a six-sided die in Descent: Journeys in the Dark (1st Edition).
 *
 * @property color The die color (e.g., "Red", "Blue", "White")
 * @property sides The six sides of the die
 * @property facingSide Index (0–5) of the currently facing side
 */
class Die(
    val color: String,
    val sides: Array<DieSide>
) {

    var facingSide: Int = 0
        private set

    init {
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
}
