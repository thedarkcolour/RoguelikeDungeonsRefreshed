package thedarkcolour.roguelikedungeons.dungeon.level

import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.level.mst.LevelGeneratorMST
import java.util.*

@Complete
enum class LevelGeneratorType(val get: (Random, LevelSettings) -> ILevelGenerator) {
    CLASSIC(::LevelGeneratorClassic),
    MINIMUM_SPANNING_TREE(::LevelGeneratorMST);
}