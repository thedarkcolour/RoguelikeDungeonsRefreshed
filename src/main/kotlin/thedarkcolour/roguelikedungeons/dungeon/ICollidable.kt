package thedarkcolour.roguelikedungeons.dungeon

import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.Complete

@Complete
interface ICollidable {
    val start: BlockPos
    val end: BlockPos

    fun getBounds(): BoundingBox
    fun collidesWith(other: ICollidable): Boolean
}