package thedarkcolour.roguelikedungeons.dungeon.shape

import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.ICollidable
import thedarkcolour.roguelikedungeons.dungeon.theme.placer.IBlockPlacer
import java.util.*

@Complete
enum class ShapeType(val get: (BlockPos, BlockPos) -> IShape) {
    SOLID_RECTANGLE(::SolidRectangleShape),
    HOLLOW_RECTANGLE(::HollowRectangleShape),
    PYRAMID_RECTANGLE(::PyramidRectangleShape),
    WIREFRAME_RECTANGLE(::WireframeRectangleShape),
    SPHERE(::SphereShape);

    fun fill(worldIn: IWorld, rand: Random, start: BlockPos, end: BlockPos, block: IBlockPlacer, fillAir: Boolean = true, replaceSolid: Boolean = true) {
        get(start, end).fill(worldIn, rand, block, fillAir, replaceSolid)
    }

    fun get(collidable: ICollidable): IShape {
        return get(collidable.start, collidable.end)
    }
}