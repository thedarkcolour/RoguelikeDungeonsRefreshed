package thedarkcolour.roguelikedungeons.dungeon.theme.placer

import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.util.pickRandomly
import java.util.*

open class JumbleBlockPlacer :
    MultiBlockPlacer {
    constructor(vararg blocks: IBlockPlacer) : super(*blocks)
    constructor(vararg blocks: BlockState) : super(*blocks)

    override fun place(worldIn: IWorld, rand: Random, pos: BlockPos, fillAir: Boolean, replaceSolid: Boolean): Boolean {
        val block = rand.pickRandomly(blocks)
        return block.place(worldIn, rand, pos, fillAir, replaceSolid)
    }
}