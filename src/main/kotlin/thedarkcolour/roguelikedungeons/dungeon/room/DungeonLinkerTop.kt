package thedarkcolour.roguelikedungeons.dungeon.room

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.dungeon.*
import thedarkcolour.roguelikedungeons.dungeon.level.LevelSettings
import thedarkcolour.roguelikedungeons.dungeon.shape.ShapeType
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

class DungeonLinkerTop : IDungeonRoom {

    override val size = 6

    override fun generate(worldIn: DungeonWorldIn, rand: Random, settings: LevelSettings, entrances: Array<Direction>, pos: BlockPos): Boolean {
        val theme = settings.theme

        val pillar = theme.primary.pillar
        val wall = theme.primary.wall
        val floor = theme.primary.floor
        val stair = theme.primary.stairs

        val start = pos.toMutablePos()
        val end = pos.toMutablePos()
        val cursor = pos.toMutablePos()

        start.move(-4, -1, -4)
        end.move(4, 5, 4)
        ShapeType.HOLLOW_RECTANGLE.fill(worldIn, rand, start, end, wall, fillAir = false, replaceSolid = true)

        cursor.move(Direction.UP, 5)
        settings.theme.primary.lightBlock.place(worldIn, rand, cursor)

        start.setPos(pos)
        end.setPos(pos)
        start.move(-4, -1, -4)
        end.move(4, -1, 4)
        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, floor, fillAir = true, replaceSolid = true)

        for (dir in HORIZONTALS) {
            start.setPos(pos)
            end.setPos(pos)
            start.move(dir, 3)
            start.move(leftDirection(dir), 3)
            end.move(dir, 4)
            end.move(leftDirection(dir), 4)
            end.move(Direction.UP, 4)
            ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, pillar, fillAir = true, replaceSolid = true)
            start.setPos(pos)
            start.move(dir, 3)
            start.move(leftDirection(dir), 2)
            start.move(Direction.UP, 4)
            end.setPos(start)
            end.move(rightDirection(dir), 4)
            ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, wall, fillAir = true, replaceSolid = true)
            start.move(dir.opposite)
            end.move(dir.opposite)
            ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, stair.setOrientation(dir.opposite, true), fillAir = true, replaceSolid = true)
            for (o in adjacentDirections(dir)) {
                cursor.setPos(pos)
                cursor.move(dir, 3)
                cursor.move(Direction.UP, 2)
                cursor.move(o, 2)
                stair.setOrientation(o.opposite, true).place(worldIn, cursor)
                cursor.move(Direction.UP)
                wall.place(worldIn, rand, cursor)
                cursor.move(o.opposite)
                stair.setOrientation(o.opposite, true).place(worldIn, cursor)
            }
        }

        return true
    }
}