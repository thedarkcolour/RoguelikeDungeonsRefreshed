package thedarkcolour.roguelikedungeons.dungeon.theme.placer

import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import java.util.*

open class SimpleBlockPlacer(protected var state: BlockState) :
    IBlockPlacer {
    fun place(worldIn: IWorld, pos: BlockPos): Boolean {
        return worldIn.setBlockState(pos, state, 2)
    }

    override fun place(worldIn: IWorld, rand: Random, pos: BlockPos, fillAir: Boolean, replaceSolid: Boolean): Boolean {
        return if ((!fillAir && worldIn.isAirBlock(pos)) || (!replaceSolid && !worldIn.isAirBlock(pos))) {
            false
        } else worldIn.setBlockState(pos, state, 2)
    }
}