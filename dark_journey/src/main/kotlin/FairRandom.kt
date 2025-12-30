package dark_journey

import kotlin.random.Random
import kotlin.ranges.*

object FairRandom {
    
    var results : HashMap<Player, HashMap<DieType, ArrayList<Int>>>
    var index : HashMap<Player, HashMap<DieType, Int>>
    
    init {
        results = HashMap<Player, HashMap<DieType, ArrayList<Int>>>()
        index = HashMap<Player, HashMap<DieType, Int>>()
        reset()    
    }
    
    fun reset() {
        for (player in Player.entries) {
            var resultsMap = HashMap<DieType, ArrayList<Int>>()
            var indexMap = HashMap<DieType, Int>()
            for (dieType in DieType.entries) {
                resultsMap.put(dieType, ArrayList<Int>())
                indexMap.put(dieType, 0)
            }
            results.put(player, resultsMap)
            index.put(player, indexMap)
        }
        
    }
    
    fun result(player : Player, dieType : DieType) : Int {
        var r = results.getValue(player).getValue(dieType)
        var i = index.getValue(player).getValue(dieType)
        if (0 == r.size || i == r.size) {
            val newSize = Random.nextInt(1,4)
            r = ArrayList<Int>(newSize*6)
            for (i in 1..newSize) {
                r.addAll(0..5)
            }
            r.shuffle()
            results.getValue(player).put(dieType,r)
            i = 0
            index.getValue(player)[dieType] = 0
        }
        val playerIndexMap = index.getValue(player)
        playerIndexMap.put(dieType, i+1)
        return r[i]
    }
}