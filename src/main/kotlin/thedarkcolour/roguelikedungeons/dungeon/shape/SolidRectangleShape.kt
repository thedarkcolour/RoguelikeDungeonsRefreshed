package thedarkcolour.roguelikedungeons.dungeon.shape

import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.Complete

@Complete
class SolidRectangleShape(start: BlockPos, end: BlockPos) : AbstractShape(start, end) {

    /**
     * Returns an iterator over the elements of this object.
     */
    override fun iterator(): Iterator<BlockPos> {
        return BlockPos.getAllInBoxMutable(start, end).iterator()
    }
}