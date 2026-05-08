# Dark Journey — Kiro Session Notes

Kotlin clone of Descent: Journeys in the Dark (1st Ed.). Gradle/Kotlin JVM project using Kotest + Jackson.

## Architecture

### Dice (`Die.kt`, `Dice.kt`, `FairRandom.kt`)
- 9 `DieType`s: RED, BLUE, WHITE, GREEN, YELLOW (attack) + BLACK, SILVER, GOLD (power) + STEALTH
- `DieSide`: miss, damage, range, surges, power
- `DieDefs.sidesMap`: all 6 sides per die type
- `Die.upgrade()`: BLACK→SILVER→GOLD
- `FairRandom`: shuffle-bag per (Player, DieType) so miss rate stays ≤25%
- `Dice` class is redundant with `Die.get()`

### Attack (`Attack.kt`, `SurgeConversion.kt`, `SpecialAbility.kt`)
- `Attack(nonPowerDieTypes, powerDieTypes, surgeConversions, specialAbilities)`
- Power dice: `powerToDamage(i)` / `powerToRange(i)` to spend power
- `spendSurges(n, conversionIndex)` → triggers `SpecialAbility`
- `SurgeConversion.fromString("2_for_+1D")` parses surge cost + effect
- `SpecialAbility` types: ADD_DAMAGE, ADD_RANGE implemented; ADD_FATIGUE, BLAST, BREATH defined but not implemented
- `summarize()` → `AttackSummary(miss, damage, range, surges, power)`

### Weapons (`Weapon.kt`, `weapons.yaml`)
- Loaded from YAML via Jackson
- Tiers: Shop (Sword, Axe, Crossbow, Sunburst), Copper/Silver/Gold (empty)
- `WeaponDefs.weaponGroup(name).weapon(name)`
- `Weapon.surgeConversions()` / `specialAbilities()` parse string lists

### Map (`DungeonMap.kt`)
- Two parallel char grids: `wad` (walls/doors: O=open, R/L/r/l=doors) and `sqc` (contents: G=goal, U/B/C/A=markers, E=exit)
- `areAdjacent`: Chebyshev distance ≤1
- `superCoverLine(a, b)`: supercover grid traversal for path/LOS tracing

## Known Bugs / TODOs
- `superCoverLine` uses undeclared `priorSquare` variable (compile error)
- `blocksLos(firstSquare, secondSquare, losType)` has no body and no parameter types
- `DungeonMap.superCoverLine` is an instance method but test calls it as `DungeonMap.superCoverLine(...)` (should be companion object)
- `SpecialAbility.fromString` doesn't handle BLAST/BREATH
- LOS test in `DungeonMapTest` is incomplete
- No hero/monster/character classes yet
- No game state / turn structure yet
