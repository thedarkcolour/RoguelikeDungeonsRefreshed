package thedarkcolour.roguelikedungeons.dungeon.theme.placer

import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.util.checkNotEmpty
import java.util.*
import kotlin.math.abs

@Complete
class StripedBlockPlacer(private vararg val blocks: IBlockPlacer) : IBlockPlacer {
    init {
        checkNotEmpty(blocks)
    }

    override fun place(worldIn: IWorld, rand: Random, pos: BlockPos, fillAir: Boolean, replaceSolid: Boolean): Boolean {
        val size = blocks.size
        val choice = abs(pos.x % size + pos.y % size + pos.z % size) % size

        return blocks[choice].place(worldIn, rand, pos, fillAir, replaceSolid)
    }
}