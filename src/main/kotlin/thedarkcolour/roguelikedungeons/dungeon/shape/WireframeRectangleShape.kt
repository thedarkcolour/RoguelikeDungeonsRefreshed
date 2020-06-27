package thedarkcolour.roguelikedungeons.dungeon.shape

import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.correctBounds
import thedarkcolour.roguelikedungeons.dungeon.toMutablePos

@Complete
class WireframeRectangleShape(start: BlockPos, end: BlockPos) : AbstractShape(start, end) {

    /**
     * Returns an iterator over the elements of this object.
     */
    override fun iterator(): Iterator<BlockPos> {
        return ShapeIterator(start, end)
    }

    private class ShapeIterator(private val start: BlockPos, private val end: BlockPos) : Iterator<BlockPos> {
        private var cursor = correctBounds(start, end).first.toMutablePos()

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
            val toReturn = cursor

            if (cursor.z == end.z && cursor.x == end.x) {
                cursor.setPos(start.x, cursor.y + 1, start.z)
                return toReturn
            }

            if (cursor.y == start.y || cursor.y == end.y) {
                if (cursor.x == end.x) {
                    cursor.setPos(start.x, cursor.y, cursor.z + 1)
                    return toReturn
                }
                if (cursor.z == start.z || cursor.z == end.z) {
                    cursor.move(1, 0, 0)
                    return toReturn
                }
                if (cursor.x == start.x) {
                    cursor.setPos(end.x, cursor.y, cursor.z)
                    return toReturn
                }
            }

            if (cursor.x == start.x) {
                cursor.setPos(end.x, cursor.y, cursor.z)
                return toReturn
            }

            if (cursor.x == end.x) {
                cursor.setPos(start.x, cursor.y, end.z)
                return toReturn
            }

            cursor.setPos(end.x, cursor.y, cursor.z)
            return toReturn
        }
    }
}