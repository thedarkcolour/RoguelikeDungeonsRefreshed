package thedarkcolour.roguelikedungeons.dungeon.room

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.level.LevelSettings
import thedarkcolour.roguelikedungeons.dungeon.shape.HollowRectangleShape
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

@Complete
interface IDungeonRoom : Comparable<IDungeonRoom> {
    fun generate(worldIn: DungeonWorldIn, rand: Random, settings: LevelSettings, entrances: Array<Direction>, pos: BlockPos): Boolean

    val size: Int

    fun isValidLocation(worldIn: IWorld, direction: Direction, pos: BlockPos): Boolean {
        val size = size

        val start = BlockPos(pos.x - size, pos.y - 2, pos.z - size)
        val end = BlockPos(pos.x + size, pos.y + 5, pos.z + size)

        for (cursor in HollowRectangleShape(start, end)) {
            if (worldIn.getBlockState(cursor).isSolid) {
                return false
            }
        }

        return true
    }

    /**
     * Compares this object with the specified object for order. Returns zero if this object is equal
     * to the specified [other] object, a negative number if it's less than [other], or a positive number
     * if it's greater than [other].
     */
    override fun compareTo(other: IDungeonRoom): Int {
        return size - other.size
    }
}