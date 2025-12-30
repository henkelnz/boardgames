package dark_journey

data class AttackSummary(
    var miss : Boolean = false,
    var power : Int = 0,
    var range : Int = 0,
    var surges : Int = 0,
    var damage : Int = 0,
    var special : ArrayList<String> = ArrayList<String>() // TODO: create a type for special effects
) {

}

class Attack(
    nonPowerDieTypes : List<DieType>, 
    powerDieTypes : List<DieType>, 
    var surgeConversions : List<SurgeConversion> = ArrayList<SurgeConversion>(), 
    var specialAbilities : List<SpecialAbility> = ArrayList<SpecialAbility>()
) {
    var nonPowerDice : ArrayList<Die>
    var powerDice : ArrayList<Die>
    var toDamage : ArrayList<Boolean>
    var toRange : ArrayList<Boolean>
    var spentSurges : Int
    var extraDamage : Int
    var extraRange : Int
    
    init {
        nonPowerDice = Die.get(nonPowerDieTypes)
        powerDice = Die.get(powerDieTypes)
        toDamage = ArrayList<Boolean>(powerDice.size).apply {addAll(List(powerDice.size) {false})}
        toRange = ArrayList<Boolean>(powerDice.size).apply {addAll(List(powerDice.size) {false})}
        surgeConversions = surgeConversions + listOf(SurgeConversion(1, SpecialAbilityType.ADD_NOTHING, 1))
        spentSurges = 0
        extraDamage = 0
        extraRange = 0
    }
    
    fun roll() : ArrayList<DieSide> {
        var result = ArrayList<DieSide>(nonPowerDice.size + powerDice.size)
        for(die in nonPowerDice) {
            var side = die.roll()
            result.add(side)
        }
        for(die in powerDice) {
            var side = die.roll()
            result.add(side)
        }
        return result
    }
    
    fun powerToDamage(index : Int) {
        check(0 < powerDice[index].currentSide().power) {"attempted to spend power enhancement but die shows surges or blank"}
        toDamage[index] = true
    }
    
    fun powerToRange(index : Int) {
        check(0 < powerDice[index].currentSide().power) {"attempted to spend power enhancement but die shows surges or blank"}
        toRange[index] = true
    }
    
    fun spendSurges(surges : Int, surgeConversionIndex : Int) {
        val summary = summarize()
        if (summary.surges < surges) {
            error("spending more surges than remain")
        }
        spentSurges += surges
        val special = surgeConversions[surgeConversionIndex].spend(surges)
        specialAbilities = specialAbilities + listOf(special)
    }
    
    fun summarize() : AttackSummary {
        var summary = AttackSummary()
        for (die in nonPowerDice) {
            val side = die.currentSide()
            if (side.miss) {
                summary.miss = true
            }
            summary.range += side.range
            summary.surges += side.surges
            summary.damage += side.damage
        }
        
        summary.surges -= spentSurges
        for (s in specialAbilities) {
            s.apply(this)
        }
        
        summary.range += extraRange
        summary.damage += extraDamage
        
        for (i in 0..<powerDice.size) {
            if (toDamage[i]) {
                summary.damage += powerDice[i].currentSide().power
            }
            else if (toRange[i]) {
                summary.range += powerDice[i].currentSide().power
            }
            else {
                summary.surges += powerDice[i].currentSide().surges
                summary.power += powerDice[i].currentSide().power
            }
        }
        return summary
    }
}