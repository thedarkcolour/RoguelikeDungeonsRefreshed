package thedarkcolour.roguelikedungeons.dungeon

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.room.IDungeonRoom
import thedarkcolour.roguelikedungeons.dungeon.shape.ShapeType
import thedarkcolour.roguelikedungeons.dungeon.theme.Theme
import java.util.*

@Complete
class DungeonNode(val entrances: Array<Direction>, val pos: BlockPos) : ICollidable {
    var room: IDungeonRoom? = null

    private fun size(): Int {
        return room?.size ?: 6
    }

    fun encase(worldIn: IWorld, rand: Random, theme: Theme) {
        val size = size()
        val start = pos.add(-size, 3, -size)
        val end = pos.add(size, 8, size)

        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, theme.primary.wall)
    }

    fun isNear(pos: BlockPos): Boolean {
        return pos.distanceHorizontal(pos) < size()
    }

    fun getBounds(size: Int): BoundingBox {
        val start = pos.add(-size, 3, -size)
        val end = pos.add(size, 8, size)

        return BoundingBox(start, end)
    }

    override fun getBounds(): BoundingBox {
        return getBounds(size())
    }

    override fun collidesWith(other: ICollidable): Boolean {
        return getBounds().collidesWith(other)
    }

    override val start: BlockPos
        get() = getBounds().start
    override val end: BlockPos
        get() = getBounds().end
}