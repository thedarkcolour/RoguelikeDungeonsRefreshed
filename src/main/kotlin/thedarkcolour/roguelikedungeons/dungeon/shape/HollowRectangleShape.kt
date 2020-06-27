package thedarkcolour.roguelikedungeons.dungeon.shape

import net.minecraft.block.Blocks
import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.correctBounds
import thedarkcolour.roguelikedungeons.dungeon.theme.placer.IBlockPlacer
import thedarkcolour.roguelikedungeons.dungeon.toMutablePos
import thedarkcolour.roguelikedungeons.util.intSetOf
import java.util.*

@Complete
class HollowRectangleShape(start: BlockPos, end: BlockPos) : AbstractShape(start, end) {

    override fun fill(worldIn: IWorld, rand: Random, block: IBlockPlacer, fillAir: Boolean, replaceSolid: Boolean) {
        val set = intSetOf(start.x, start.y, start.z, end.x, end.y, end.z)
        val air = Blocks.AIR.defaultState

        for (pos in BlockPos.getAllInBoxMutable(start, end)) {
            if (set.contains(pos.x) || set.contains(pos.y) || set.contains(pos.z)) {
                block.place(worldIn, rand, pos, fillAir, replaceSolid)
            } else {
                worldIn.setBlockState(pos, air, 2)
            }
        }
    }

    /**
     * Returns an iterator over the elements of this object.
     */
    override fun iterator(): Iterator<BlockPos> {
        return ShapeIterator(start, end)
    }

    private class ShapeIterator(start: BlockPos, end: BlockPos) : Iterator<BlockPos> {
        val start: BlockPos
        val end: BlockPos
        var cursor: BlockPos.Mutable

        init {
            val bounds = correctBounds(start, end)

            this.start = bounds.first
            this.end = bounds.second

            cursor = start.toMutablePos()
        }

        /**
         * Returns `true` if the iteration has more elements.
         */
        override fun hasNext(): Boolean {
            return cursor.y <= end.y
        }

        /**
         * Returns the next element in the iteration.
         */
        override fun next(): BlockPos {
            if (cursor.z == end.z && cursor.x == end.x) {
                cursor.move(0, 1, 0)
            } else if (cursor.x == end.x) {
                cursor.move(Direction.SOUTH)
            }

            if (cursor.y != start.y && cursor.y != end.y && cursor.z != start.z && cursor.z != end.z && cursor.x == start.x) {
                cursor.move(Direction.EAST, end.x - start.x)
            } else {
                cursor.move(Direction.EAST)
            }

            return cursor
        }
    }
}