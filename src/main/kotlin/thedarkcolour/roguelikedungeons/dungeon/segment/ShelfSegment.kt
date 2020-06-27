package thedarkcolour.roguelikedungeons.dungeon.segment

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.MutableBlockPos
import thedarkcolour.roguelikedungeons.dungeon.adjacentDirections
import thedarkcolour.roguelikedungeons.dungeon.level.DungeonLevel
import thedarkcolour.roguelikedungeons.dungeon.shape.ShapeType
import thedarkcolour.roguelikedungeons.dungeon.theme.AIR_PLACER
import thedarkcolour.roguelikedungeons.dungeon.theme.Theme
import thedarkcolour.roguelikedungeons.dungeon.toMutablePos
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

@Complete
class ShelfSegment : ISegment {
    override fun generateWall(
            worldIn: DungeonWorldIn,
            rand: Random,
            level: DungeonLevel,
            direction: Direction,
            theme: Theme,
            pos: BlockPos
    ) {
        val air = AIR_PLACER
        val stair = theme.secondary.stairs

        val cursor = pos.toMutablePos()
        val start: MutableBlockPos
        val end: MutableBlockPos

        val adjacentDirections = adjacentDirections(direction)

        cursor.move(direction, 2)
        start = cursor.toMutablePos()
        start.move(adjacentDirections[0], 1)
        end = cursor.toMutablePos()
        end.move(adjacentDirections[1], 1)

        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, theme.secondary.wall, fillAir = false, replaceSolid = true)

        start.move(direction, 1)
        end.move(direction, 1)
        end.move(0, 2, 0)

        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, theme.secondary.wall, fillAir = false, replaceSolid = true)

        start.move(direction.opposite, 1)
        start.move(0, 1, 0)
        end.move(direction.opposite, 1)

        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, air, fillAir = false, replaceSolid = true)

        cursor.move(0, 2, 0)

        for (adj in adjacentDirections) {
            val adjacentPos = cursor.add(adj.directionVec)

            stair.setOrientation(adj.opposite, true)
            stair.place(worldIn, adjacentPos)
        }
    }
}