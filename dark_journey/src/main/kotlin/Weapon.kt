package dark_journey

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File

/**
 * Represents a weapon (artificial or natural) in Dark Journey
 * 
 */

data class Weapon(
    val name : String,
    val dieTypes : Collection<DieType>,
    val hands : Int,
    val offhand : Collection<String>,
    val surgeopt: Collection<String>,
    val special : Collection<String> // TODO: enum?
)

object WeaponDefs {
    fun allWeapons() : Map<String, Any> {
        val weapons = HashMap<String,Weapon>()
        val mapper = ObjectMapper(YAMLFactory()).registerKotlinModule()
        //val sword = mapper.readValue(File("./res/yaml/sword.yaml"), Weapon::class.java)
        //val weapons = mapper.readValue(File("./res/yaml/weapons.yaml"), Map<String,Weapon::class.java>)
        //weapons.put("Sword",sword)
        val weaponMap : Map<String,Any> = mapper.readValue(File("./res/yaml/weapons.yaml").inputStream())
        return weaponMap
    }
}
