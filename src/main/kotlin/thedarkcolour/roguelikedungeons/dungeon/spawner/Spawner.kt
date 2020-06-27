package thedarkcolour.roguelikedungeons.dungeon.spawner

import net.minecraft.block.Blocks
import net.minecraft.entity.EntityType
import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.dungeon.toMutablePos
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*
import kotlin.collections.ArrayList

class Spawner(val type: EntityType<*>) {
    private val potentials = ArrayList<SpawnPotential>()

    fun generate(worldIn: DungeonWorldIn, rand: Random, pos: BlockPos, level: Int) {
        val cursor = pos.toMutablePos()
        worldIn.setBlockState(pos, Blocks.SPAWNER.defaultState, 2)
    }
}