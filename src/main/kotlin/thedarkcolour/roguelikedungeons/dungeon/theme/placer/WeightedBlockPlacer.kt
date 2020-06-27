package thedarkcolour.roguelikedungeons.dungeon.theme.placer

import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.loot.weight.IWeighted
import thedarkcolour.roguelikedungeons.loot.weight.WeightedPool
import thedarkcolour.roguelikedungeons.util.checkNotEmpty
import java.util.*

@Complete
class WeightedBlockPlacer constructor(entries: Array<out IWeighted<IBlockPlacer>>) : IBlockPlacer {
    private val blocks = WeightedPool<IBlockPlacer>()

    init {
        blocks.addAll(entries)
        // catch it early on
        checkNotEmpty(blocks)
    }

    override fun place(worldIn: IWorld, rand: Random, pos: BlockPos, fillAir: Boolean, replaceSolid: Boolean): Boolean {
        val block = blocks.get(rand)
        return block.place(worldIn, rand, pos, fillAir, replaceSolid)
    }
}