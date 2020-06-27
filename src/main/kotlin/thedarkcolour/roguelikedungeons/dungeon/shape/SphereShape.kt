package thedarkcolour.roguelikedungeons.dungeon.shape

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.correctBounds
import thedarkcolour.roguelikedungeons.dungeon.leftDirection
import thedarkcolour.roguelikedungeons.dungeon.toMutablePos
import thedarkcolour.roguelikedungeons.dungeon.vertical

@Complete
class SphereShape(start: BlockPos, end: BlockPos) : AbstractShape(start, end) {

    /**
     * Returns an iterator over the elements of this object.
     */
    override fun iterator(): Iterator<BlockPos> {
        return ShapeIterator(start, end)
    }

    private class ShapeIterator(private val center: BlockPos, end: BlockPos) : Iterator<BlockPos> {
        private val radius: Int
        private var layer = 0
        private var row = 0
        private var column = 0
        private var direction = Direction.NORTH
        private var top = true

        init {
            val bounds = correctBounds(center, end)
            val diff = bounds.second.subtract(bounds.first)

            var r = diff.x
            r = if (r < diff.y) diff.y else r
            r = if (r < diff.z) diff.z else r
            radius = r
        }

        /**
         * Returns `true` if the iteration has more elements.
         */
        override fun hasNext(): Boolean {
            return layer < radius
        }

        /**
         * Returns the next element in the iteration.
         */
        override fun next(): BlockPos {
            val toReturn = center.toMutablePos()

            toReturn.move(vertical(top), layer)
            toReturn.move(direction, row)
            toReturn.move(leftDirection(direction), column)

            if (direction != Direction.NORTH || top) {
                if (direction == Direction.NORTH) {
                    top = false
                }
                direction = leftDirection(direction)
                return toReturn
            }

            ++column

            if (inRange(column, layer, row)) {
                direction = leftDirection(direction)
                top = true
                return toReturn
            } else {
                column = 0
            }

            ++row

            if (inRange(column, layer, row)) {
                direction = leftDirection(direction)
                top = true
                return toReturn
            } else {
                row = 0
            }

            ++layer
            direction = leftDirection(direction)
            top = true
            return toReturn
        }

        private fun inRange(x: Int, y: Int, z: Int): Boolean {
            return x * x + y * y + z * z < radius * radius
        }
    }
}