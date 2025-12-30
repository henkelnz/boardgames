package dark_journey

/**
 * Pretty-prints the result of a single die roll.
 */
fun prettyPrintDieResult(die: Die) {
    val side = die.currentSide()

    println("┌────────────────────────┐")
    println("│ Rolled ${die.type.name} Die".padEnd(24) + "│")
    println("├────────────────────────┤")

    if (side.miss) {
        println("│        ✖ MISS ✖        │")
    } else {
        println("│  ♥ Damage : ${side.damage}".padEnd(24) + "│")
        println("│  ⚡ Surges : ${side.surges}".padEnd(24) + "│")
        println("│  → Range  : ${side.range}".padEnd(24) + "│")
        println("│  / Power  : ${side.power}".padEnd(24) + "│")
    }

    println("└────────────────────────┘")
}

/**
 * Command-line entry point.
 */
fun main() {
    val dice = Dice()
    val types = arrayOf(DieType.RED, DieType.GREEN, DieType.BLACK)
    val ds = dice.get(types)
    for (i in ds) {
        i.roll()
        prettyPrintDieResult(i)
    }    
}
