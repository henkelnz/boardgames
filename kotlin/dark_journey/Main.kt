/**
 * Pretty-prints the result of a single die roll.
 */
fun prettyPrintDieResult(die: Die) {
    val side = die.currentSide()

    println("┌────────────────────────┐")
    println("│ Rolled ${die.color} Die".padEnd(24) + "│")
    println("├────────────────────────┤")

    if (side.miss) {
        println("│        ✖ MISS ✖        │")
    } else {
        println("│  ♥ Wounds : ${side.wounds}".padEnd(24) + "│")
        println("│  ⚡ Surges : ${side.surges}".padEnd(24) + "│")
        println("│  → Range  : ${side.range}".padEnd(24) + "│")
    }

    println("└────────────────────────┘")
}

/**
 * Command-line entry point.
 */
fun main() {
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

    val redDie = Die(
        color = "Red",
        sides = redDieSides
    )
    
    val blueDie = Die(
        color = "Blue",
        sides = blueDieSides
    )

    redDie.roll()
    prettyPrintDieResult(redDie)
    blueDie.roll()
    prettyPrintDieResult(blueDie)
}
