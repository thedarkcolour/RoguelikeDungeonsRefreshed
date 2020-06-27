package thedarkcolour.roguelikedungeons.dungeon.segment

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.MutableBlockPos
import thedarkcolour.roguelikedungeons.dungeon.adjacentDirections
import thedarkcolour.roguelikedungeons.dungeon.level.DungeonLevel
import thedarkcolour.roguelikedungeons.dungeon.shape.ShapeType
import thedarkcolour.roguelikedungeons.dungeon.theme.FIRE_PLACER
import thedarkcolour.roguelikedungeons.dungeon.theme.IRON_BARS_PLACER
import thedarkcolour.roguelikedungeons.dungeon.theme.NETHERRACK_PLACER
import thedarkcolour.roguelikedungeons.dungeon.theme.Theme
import thedarkcolour.roguelikedungeons.dungeon.toMutablePos
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

@Complete
class FireArchSegment : ISegment {
    override fun generateWall(
            worldIn: DungeonWorldIn,
            rand: Random,
            level: DungeonLevel,
            direction: Direction,
            theme: Theme,
            pos: BlockPos
    ) {
        val stair = theme.primary.stairs
        val walls = theme.primary.wall

        val cursor = pos.toMutablePos()
        val start = pos.toMutablePos().move(direction, 3)
        val end = start.toMutablePos()

        val adjacentDirections = adjacentDirections(direction)

        start.move(adjacentDirections[0])
        end.move(0, 2, 0)
        end.move(direction)

        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, walls)

        cursor.setPos(pos)
        cursor.move(direction, 2)
        stair.setOrientation(direction.opposite, false).place(worldIn, cursor)
        cursor.move(0, 2, 0)
        stair.setOrientation(direction.opposite, true).place(worldIn, cursor)
        cursor.move(0, -2, 0)
        cursor.move(direction)
        NETHERRACK_PLACER.place(worldIn, pos)
        cursor.move(0, 1, 0)
        FIRE_PLACER.place(worldIn, pos)
        cursor.move(direction.opposite)
        IRON_BARS_PLACER.place(worldIn, pos)

        for (adjacentDirection in adjacentDirections) {
            cursor.setPos(pos)
            cursor.move(direction)
            cursor.move(adjacentDirection)
            cursor.move(0, 2, 0)
            stair.setOrientation(direction.opposite, true).place(worldIn, cursor)
        }
    }
}