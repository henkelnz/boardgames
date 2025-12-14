/**
 * Represents a roll consisting of multiple dice.
 *
 * @property dice The dice included in this roll
 */
class Roll(
    val dice: Array<Die>
) {

    /**
     * Rolls all dice.
     *
     * @return The array of dice after rolling
     */
    fun roll(): Array<Die> {
        dice.forEach { it.roll() }
        return dice
    }

    /**
     * Re-rolls only the dice at the specified indices.
     *
     * @param diceIndices Indices of dice to re-roll
     * @return The array of dice after re-rolling
     */
    fun reroll(diceIndices: Array<Int>): Array<Die> {
        diceIndices.forEach { index ->
            require(index in dice.indices) {
                "Die index $index is out of bounds"
            }
            dice[index].roll()
        }
        return dice
    }

    /**
     * Aggregates the current facing sides of all dice into a RollResult.
     *
     * Any miss on a die results in a miss overall.
     */
    fun result(): RollResult {
        var totalWounds = 0
        var totalSurges = 0
        var totalRange = 0
        var miss = false

        dice.forEach { die ->
            val side = die.currentSide()
            totalWounds += side.wounds
            totalSurges += side.surges
            totalRange += side.range
            if (side.miss) {
                miss = true
            }
        }

        return RollResult(
            wounds = totalWounds,
            surges = totalSurges,
            range = totalRange,
            miss = miss
        )
    }
}
