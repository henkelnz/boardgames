package dark_journey
/**
 * Represents a six-sided die in Dark Journey.
 *
 * @property color The die color (e.g., "Red", "Blue", "White")
 * @property sides The six sides of the die
 * @property facingSide Index (0–5) of the currently facing side
 */
class Dice() {
    fun get(types : Array<DieType>) : ArrayList<Die> {
        val dice = ArrayList<Die>()
        for (t in types) {
            dice.add(Die(type=t))
        }
        return dice
    }
}