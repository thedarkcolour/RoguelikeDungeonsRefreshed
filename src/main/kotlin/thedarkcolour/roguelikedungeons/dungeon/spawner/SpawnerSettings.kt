package thedarkcolour.roguelikedungeons.dungeon.spawner

import net.minecraft.entity.EntityType
import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

class SpawnerSettings {
    // never used
    //private val spawners = hashMapOf<EntityType<*>, WeightedPool<Spawner>>()

    fun generate(worldIn: DungeonWorldIn, rand: Random, pos: BlockPos, type: EntityType<*>, level: Int) {
        val a = Spawner(type)//if (spawners.containsKey(type)) spawners[type]!!.get(rand) else Spawner(type)

        a.generate(worldIn, rand, pos, level)
    }
}