package thedarkcolour.roguelikedungeons.dungeon.shape

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.correctBounds
import thedarkcolour.roguelikedungeons.dungeon.leftDirection
import thedarkcolour.roguelikedungeons.dungeon.toMutablePos
import kotlin.math.acos
import kotlin.math.sqrt
import kotlin.math.tan

@Complete
class PyramidRectangleShape(start: BlockPos, end: BlockPos) : AbstractShape(start, end) {

    /**
     * Returns an iterator over the elements of this object.
     */
    override fun iterator(): Iterator<BlockPos> {
        return ShapeIterator(start, end)
    }

    private class ShapeIterator(private val start: BlockPos, end: BlockPos) : Iterator<BlockPos> {
        val diff: BlockPos
        var cursor = BlockPos.Mutable()
        var direction = Direction.NORTH
        val thetaX: Double
        val thetaZ: Double

        init {
            val bounds = correctBounds(start, end)
            val s = bounds.first
            val e = bounds.second
            diff = e.subtract(s)

            val hx = sqrt((diff.x * diff.x + diff.y * diff.y).toDouble())
            thetaX = acos(diff.y / hx)

            val hz = sqrt((diff.z * diff.z + diff.y * diff.y).toDouble())
            thetaZ = acos(diff.y / hz)
        }

        /**
         * Returns `true` if the iteration has more elements.
         */
        override fun hasNext(): Boolean {
            return cursor.y < diff.y
        }

        /**
         * Returns the next element in the iteration.
         */
        override fun next(): BlockPos {
            val toReturn = start.toMutablePos()
            toReturn.move(Direction.UP, cursor.y)

            if (direction == Direction.NORTH || direction == Direction.SOUTH) {
                toReturn.move(leftDirection(direction), cursor.x)
                toReturn.move(direction, cursor.z)
            } else {
                toReturn.move(direction, cursor.x)
                toReturn.move(leftDirection(direction), cursor.z)
            }

            if (direction != Direction.NORTH) {
                direction = leftDirection(direction)
                return toReturn
            }

            cursor.move(Direction.SOUTH)

            if (inRange()) {
                direction = leftDirection(direction)
                return toReturn
            }

            cursor.setPos(cursor.x + 1, cursor.y, 0)

            if (inRange()) {
                direction = leftDirection(direction)
                return toReturn
            }

            cursor.setPos(0, cursor.y + 1, cursor.z)
            direction = leftDirection(direction)

            return toReturn
        }

        private fun inRange(): Boolean {
            val y = diff.y - cursor.y
            if (cursor.x >= tan(thetaX) * y) {
                return false
            }
            return cursor.z < tan(thetaZ) * y
        }
    }
}