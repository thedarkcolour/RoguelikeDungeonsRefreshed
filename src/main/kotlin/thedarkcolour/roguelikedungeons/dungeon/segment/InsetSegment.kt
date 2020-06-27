package thedarkcolour.roguelikedungeons.dungeon.segment

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.dungeon.adjacentDirections
import thedarkcolour.roguelikedungeons.dungeon.level.DungeonLevel
import thedarkcolour.roguelikedungeons.dungeon.shape.ShapeType
import thedarkcolour.roguelikedungeons.dungeon.theme.AIR_PLACER
import thedarkcolour.roguelikedungeons.dungeon.theme.Theme
import thedarkcolour.roguelikedungeons.dungeon.toMutablePos
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

class InsetSegment : ISegment {
    override fun generateWall(worldIn: DungeonWorldIn, rand: Random, level: DungeonLevel, direction: Direction, theme: Theme, pos: BlockPos) {
        val air = AIR_PLACER
        val stair = theme.secondary.stairs


        val cursor = pos.toMutablePos()
        val start = pos.toMutablePos()
        val end = pos.toMutablePos()

        val adjs = adjacentDirections(direction)

        start.setPos(pos)
        start.move(direction, 2)
        end.setPos(start)
        start.move(adjs[0], 1)
        end.move(adjs[1], 1)
        end.move(0, 2, 0)
        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, air)
        start.move(direction, 1)
        end.move(direction, 1)
        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, theme.secondary.wall)

        for (d in adjs) {
            cursor.setPos(pos)
            cursor.move(0, 2, 0)
            cursor.move(direction, 2)
            cursor.move(d, 1)
            stair.setOrientation(direction.opposite, true)
            stair.place(worldIn, cursor)
            cursor.setPos(pos)
            cursor.move(direction, 2)
            cursor.move(d, 1)
            stair.setOrientation(d.opposite, false)
            stair.place(worldIn, cursor)
        }

        cursor.setPos(pos)
        cursor.move(Direction.UP, 1)
        cursor.move(direction, 3)
        air.place(worldIn, cursor)
        cursor.move(Direction.UP, 1)
        stair.setOrientation(direction.opposite, true)
        stair.place(worldIn, cursor)
    }
}