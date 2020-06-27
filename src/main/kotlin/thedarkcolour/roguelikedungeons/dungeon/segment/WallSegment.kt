package thedarkcolour.roguelikedungeons.dungeon.segment

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.dungeon.MutableBlockPos
import thedarkcolour.roguelikedungeons.dungeon.adjacentDirections
import thedarkcolour.roguelikedungeons.dungeon.level.DungeonLevel
import thedarkcolour.roguelikedungeons.dungeon.shape.ShapeType
import thedarkcolour.roguelikedungeons.dungeon.theme.AIR_PLACER
import thedarkcolour.roguelikedungeons.dungeon.theme.Theme
import thedarkcolour.roguelikedungeons.dungeon.toMutablePos
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

class WallSegment : ISegment {
    override fun generateWall(
        worldIn: DungeonWorldIn,
        rand: Random,
        level: DungeonLevel,
        dir: Direction,
        theme: Theme,
        pos: BlockPos
    ) {
        val air = AIR_PLACER
        val stair = theme.secondary.stairs

        val cursor = pos.toMutablePos()
        val start = MutableBlockPos()
        val end = MutableBlockPos()

        val adj = adjacentDirections(dir)

        cursor.move(dir, 2)
        start.setPos(cursor)
        start.move(adj[0], 1)
        end.setPos(cursor)
        end.move(adj[1], 1)
        end.move(0, 2, 0)
        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, air)

        val secrets = level.settings.secrets
        val room = secrets.generateRoom(worldIn, rand, level.settings, dir, pos)

        start.move(dir, 1)
        end.move(dir, 1)
        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, theme.secondary.wall, fillAir = false, replaceSolid = true)

        cursor.move(0, 2, 0)
        for (d in adj) {
            val c = cursor.toMutablePos()
            c.move(d, 1)
            stair.setOrientation(d.opposite, true)
            stair.place(worldIn, c)
        }

        if (room != null) {
            cursor.setPos(pos)
            val wall = theme.primary.wall
            cursor.move(dir, 3)
            wall.place(worldIn, rand, cursor)
            cursor.move(0, 1, 0)
            wall.place(worldIn, rand, cursor)
        }
    }
}