package thedarkcolour.roguelikedungeons.dungeon.shape

import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.theme.placer.IBlockPlacer
import java.util.*

@Complete
interface IShape : Iterable<BlockPos> {
    fun fill(worldIn: IWorld, rand: Random, block: IBlockPlacer, fillAir: Boolean = true, replaceSolid: Boolean = true)
    val coordinates: MutableList<BlockPos>
}