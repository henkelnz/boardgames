/**
 * Represents the combined result of one or more die rolls.
 */
data class RollResult(
    val miss: Boolean = false,
    val range: Int = 0,
    val surges: Int = 0,
    val wounds: Int = 0
)
