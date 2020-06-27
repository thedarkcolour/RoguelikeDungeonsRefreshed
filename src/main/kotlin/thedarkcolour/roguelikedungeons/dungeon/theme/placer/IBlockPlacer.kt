package thedarkcolour.roguelikedungeons.dungeon.theme.placer

import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.dungeon.shape.IShape
import java.util.*

interface IBlockPlacer {
    fun place(worldIn: IWorld, rand: Random, pos: BlockPos, fillAir: Boolean, replaceSolid: Boolean): Boolean

    // default methods

    fun place(worldIn: IWorld, rand: Random, pos: BlockPos): Boolean {
        return place(worldIn, rand, pos, fillAir = true, replaceSolid = true)
    }

    fun fill(worldIn: IWorld, rand: Random, shape: IShape) {
        shape.fill(worldIn, rand, this, fillAir = true, replaceSolid = true)
    }

    fun fill(worldIn: IWorld, rand: Random, shape: IShape, fillAir: Boolean, replaceSolid: Boolean) {
        shape.fill(worldIn, rand, this, fillAir, replaceSolid)
    }
}