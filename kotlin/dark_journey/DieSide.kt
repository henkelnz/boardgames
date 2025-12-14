/**
 * Represents a single side of a die in
 * Dark Journey
 *
 * @property miss Whether this side is a miss
 * @property wounds Number of wounds dealt
 * @property range Range provided
 * @property surges Number of surges generated
 */
data class DieSide(
    val miss: Boolean = false,
    val range: Int = 0,
    val surges: Int = 0,
    val wounds: Int = 0
)