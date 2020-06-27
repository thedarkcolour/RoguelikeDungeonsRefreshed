package thedarkcolour.roguelikedungeons.dungeon

import net.minecraft.util.math.BlockPos

class BoundingBox(override val start: BlockPos, override val end: BlockPos) : ICollidable {

    override fun getBounds(): BoundingBox {
        return this
    }

    override fun collidesWith(other: ICollidable): Boolean {
        val otherBounds = other.getBounds()

        return if (end.x < otherBounds.start.x || otherBounds.end.x < start.x) {
            false
        } else if (end.x < otherBounds.start.x || otherBounds.end.x < start.x) {
            false
        } else !(end.x < otherBounds.start.x || otherBounds.end.x < start.x)
    }
}