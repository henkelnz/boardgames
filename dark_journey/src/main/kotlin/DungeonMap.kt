package dark_journey

data class DungeonMap(val wad : Array<CharArray>, val sqc : Array<CharArray>) {
    companion object {
        fun load(wadRsc : String, sqcRsc : String) : DungeonMap {
            val inAry = arrayOf(wadRsc, sqcRsc)
            val outAry = arrayOf(ArrayList<CharArray>(), ArrayList<CharArray>())
            for (i in 0..1) {
                // Reads file from resource
                var resourceName = inAry[i]
                val inputStream = Thread.currentThread()
                    .contextClassLoader
                    .getResourceAsStream(resourceName)
                    ?: error("Could not find resource: $resourceName")
                //println(inputStream?.bufferedReader()?.readText())
                val br = inputStream?.bufferedReader()
                val md = ArrayList<CharArray>()
                var line = br?.readLine()
                while (line != null) {
                    outAry[i].add(line.toCharArray()!!)
                    line = br?.readLine()
                }
            }    
            
            return DungeonMap(outAry[0].toTypedArray(), outAry[1].toTypedArray())
        }
    }
    
    /*
    override fun equals(that : Any?) : Boolean {
        println("checking equality")
        if (this === that)
            return true
        if (that !is DungeonMap)
            return false
        if (this.wad.size != that.wad.size)
            return false
        for (i in 1..<this.wad.size)
            if(this.wad[i] != that.wad[i] || this.sqc[i] != that.sqc[i])
                return false
        
        return true
    }
    
    overide fun HashCode() : Int {
        
    }
    */
}