package dark_journey

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.core.type.TypeReference
//import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File

/**
 * Represents a weapon (artificial or natural) in Dark Journey
 * 
 */

data class Weapon(
    val name : String,
    val dieTypes : List<DieType>,
    val hands : Int,
    val offhand : List<String>,
    val surgeopt: List<String>,
    val special : List<String>, // TODO: enum?
    val price: Int
)

data class WeaponGroup(
    val group : String,
    val weapons : Map<String, Weapon>
) {
    fun weapon(name : String) : Weapon {
        return weapons[name] ?: error("Weapon $name not found in WeaponGroup $group")    
    }
}

object WeaponDefs {
    
    val weaponGroups : Map<String, WeaponGroup>
    
    init {
        weaponGroups = loadWeaponGroups()
    }
    
    fun loadWeaponGroups(resourceName: String = "weapons.yaml"): Map<String, WeaponGroup> {
        val mapper = ObjectMapper(YAMLFactory())
            .registerKotlinModule()

        val inputStream = Thread.currentThread()
            .contextClassLoader
            .getResourceAsStream(resourceName)
            ?: error("Could not find resource: $resourceName")

        return inputStream.use {
            mapper.readValue(it, object : TypeReference<Map<String, WeaponGroup>>() {})
        }
    }    
        
    fun weaponGroup(name : String) : WeaponGroup {
        return weaponGroups[name] ?: error("Weapon group $name not found in weapons.yaml")
    }    
}
