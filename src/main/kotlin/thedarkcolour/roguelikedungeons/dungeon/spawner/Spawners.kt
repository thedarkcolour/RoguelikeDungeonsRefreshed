package thedarkcolour.roguelikedungeons.dungeon.spawner

import net.minecraft.entity.EntityType
import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.compat.getPigHumanoid
import thedarkcolour.roguelikedungeons.dungeon.level.LevelSettings
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import thedarkcolour.roguelikedungeons.util.pickRandomly
import java.util.*

val COMMON_SPAWNERS = arrayOf(
    EntityType.CREEPER,
    EntityType.CAVE_SPIDER,
    EntityType.SPIDER,
    EntityType.SKELETON,
    EntityType.ZOMBIE,
    EntityType.SILVERFISH,
    EntityType.ENDERMAN,
    EntityType.WITCH,
    EntityType.WITHER,
    EntityType.BAT,
    EntityType.MAGMA_CUBE,
    EntityType.BLAZE,
    EntityType.SLIME,
    EntityType.TNT,
    getPigHumanoid(),
)

fun generateRandomSpawner(worldIn: DungeonWorldIn, rand: Random, settings: LevelSettings, pos: BlockPos) {
    generateSpawner(worldIn, rand, settings, pos, rand.pickRandomly(COMMON_SPAWNERS))
}

fun generateSpawner(worldIn: DungeonWorldIn, rand: Random, settings: LevelSettings, pos: BlockPos, type: EntityType<*>) {
    val difficulty = settings.getDifficulty(pos)
    // val spawners = settings.spawners

    // if (spawners == null) {
    Spawner(type).generate(worldIn, rand, pos, difficulty)
    // }
}