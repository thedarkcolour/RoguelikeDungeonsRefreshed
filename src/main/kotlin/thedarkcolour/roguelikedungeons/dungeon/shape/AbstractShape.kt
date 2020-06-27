package thedarkcolour.roguelikedungeons.dungeon.shape

import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.theme.placer.IBlockPlacer
import java.util.*

@Complete
abstract class AbstractShape(protected val start: BlockPos, protected val end: BlockPos) : IShape {
    override val coordinates = this.toMutableList()

    override fun fill(worldIn: IWorld, rand: Random, block: IBlockPlacer, fillAir: Boolean, replaceSolid: Boolean) {
        for (pos in this) {
            block.place(worldIn, rand, pos, fillAir, replaceSolid)
        }
    }
}