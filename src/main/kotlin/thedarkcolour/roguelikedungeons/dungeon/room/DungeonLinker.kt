package thedarkcolour.roguelikedungeons.dungeon.room

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.HORIZONTALS
import thedarkcolour.roguelikedungeons.dungeon.leftDirection
import thedarkcolour.roguelikedungeons.dungeon.level.LevelSettings
import thedarkcolour.roguelikedungeons.dungeon.rightDirection
import thedarkcolour.roguelikedungeons.dungeon.shape.ShapeType
import thedarkcolour.roguelikedungeons.dungeon.theme.IRON_BARS_PLACER
import thedarkcolour.roguelikedungeons.dungeon.toMutablePos
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

@Complete
class DungeonLinker : IDungeonRoom {

    override val size = 6

    override fun generate(worldIn: DungeonWorldIn, rand: Random, settings: LevelSettings, entrances: Array<Direction>, pos: BlockPos): Boolean {
        val primary = settings.theme.primary

        val pillar = primary.pillar
        val wall = primary.wall
        val floor = primary.floor
        val bars = IRON_BARS_PLACER

        val start = pos.toMutablePos()
        val end = pos.toMutablePos()

        start.move(-4, -1, -4)
        end.move(4, 9, 4)
        ShapeType.HOLLOW_RECTANGLE.fill(worldIn, rand, start, end, wall, fillAir = false, replaceSolid = true)

        start.setPos(pos)
        end.setPos(pos)

        start.move(-4, 9, -4)
        end.move(4, 9, 4)
        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, wall, fillAir = true, replaceSolid = true)

        start.setPos(pos)
        end.setPos(pos)

        start.move(-4, -1, -4)
        end.move(4, -1, 4)
        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, floor)

        for (dir in HORIZONTALS) {
            start.setPos(pos)
            start.move(dir, 4)
            end.setPos(pos)
            end.move(0, 8, 0)
            start.move(0, -1, 0)
            start.move(leftDirection(dir), 4)
            end.move(rightDirection(dir), 4)
            ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, bars, fillAir = true, replaceSolid = false)

            start.setPos(pos)
            end.setPos(pos)
            start.move(dir, 3)
            start.move(leftDirection(dir), 3)
            end.move(dir, 4)
            end.move(leftDirection(dir), 4)
            end.move(Direction.UP, 8)
            ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, pillar)
        }

        return true
    }
}