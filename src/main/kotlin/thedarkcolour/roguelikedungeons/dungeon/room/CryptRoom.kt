package thedarkcolour.roguelikedungeons.dungeon.room

import net.minecraft.entity.EntityType
import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.dungeon.*
import thedarkcolour.roguelikedungeons.dungeon.level.LevelSettings
import thedarkcolour.roguelikedungeons.dungeon.shape.ShapeType
import thedarkcolour.roguelikedungeons.dungeon.spawner.generateSpawner
import thedarkcolour.roguelikedungeons.dungeon.theme.AIR_PLACER
import thedarkcolour.roguelikedungeons.dungeon.theme.QUARTZ_BLOCK_PLACER
import thedarkcolour.roguelikedungeons.loot.treasure.TreasureType
import thedarkcolour.roguelikedungeons.loot.treasure.generateChest
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import thedarkcolour.roguelikedungeons.util.pickRandomly
import java.util.*

class CryptRoom : IDungeonRoom {

    override fun generate(
        worldIn: DungeonWorldIn,
        rand: Random,
        settings: LevelSettings,
        entrances: Array<Direction>,
        pos: BlockPos
    ): Boolean {

        val primary = settings.theme.primary
        val air = AIR_PLACER
        val stair = primary.stairs
        val walls = primary.wall
        val floor = primary.floor

        val cursor = MutableBlockPos()
        val start = pos.toMutablePos()
        val end = pos.toMutablePos()

        start.move(-3, 0, -3)
        end.move(3, 4, 3)
        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, air)

        start.setPos(pos.x - 9, pos.y -1, pos.z - 9)
        end.setPos(pos.x + 9, pos.y -1, pos.z + 9)
        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, floor)

        start.setPos(pos)
        end.setPos(pos)
        start.move(-9, 5, -9)
        end.move(9, 6, 9)
        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, walls, fillAir = false, replaceSolid = true)

        for (dir in HORIZONTALS) {
            val doorways = entrances.toHashSet()

            if (doorways.contains(dir) && doorways.contains(leftDirection(dir))) {
                start.setPos(pos)
                end.setPos(pos)
                start.move(dir, 3)
                end.move(leftDirection(dir), 5)
                end.move(dir, 5)
                end.move(Direction.UP, 4)
                ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, air)
            }
            if (doorways.contains(dir)) {
                // doorway air
                start.setPos(pos)
                end.setPos(pos)
                start.move(dir, 3)
                start.move(leftDirection(dir), 2)
                end.move(dir, 8)
                end.move(rightDirection(dir), 2)
                end.move(Direction.UP, 4)
                ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, air)
                for (o in adjacentDirections(dir)) {
                    if (doorways.contains(o)) {
                        cursor.setPos(pos)
                        cursor.move(dir, 7)
                        cursor.move(o, 3)
                        cursor.move(Direction.UP)
                        crypt(worldIn, rand, settings, cursor, o)
                    } else {
                        start.setPos(pos)
                        end.setPos(pos)
                        start.move(dir, 4)
                        start.move(o, 3)
                        end.move(dir, 8)
                        end.move(o, 8)
                        end.move(Direction.UP, 4)
                        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, air)
                        cursor.setPos(pos)
                        cursor.move(dir, 6)
                        cursor.move(o, 3)
                        cursor.move(Direction.UP)
                        sarcophagus(worldIn, rand, settings, cursor, o)
                    }
                }
            } else {
                cursor.setPos(pos)
                cursor.move(dir, 4)
                mausoleumWall(worldIn, rand, settings, cursor, dir)
            }
            cursor.setPos(pos)
            cursor.move(dir, 3)
            cursor.move(leftDirection(dir), 3)
            pillar(worldIn, rand, settings, cursor)
            start.setPos(pos)
            start.move(dir, 8)
            start.move(Direction.UP, 4)
            end.setPos(start)
            start.move(leftDirection(dir), 2)
            end.move(rightDirection(dir), 2)
            stair.setOrientation(dir.opposite, true)
            ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, stair, fillAir = true, replaceSolid = false)
        }

        return true
    }

    private fun crypt(
        worldIn: DungeonWorldIn,
        rand: Random,
        settings: LevelSettings,
        pos: MutableBlockPos,
        dir: Direction
    ) {
        val theme = settings.theme

        val walls = theme.primary.wall
        val stair = theme.primary.stairs

        val cursor = pos.toMutablePos()
        val start = pos.toMutablePos()
        val end = pos.toMutablePos()
        
        start.move(Direction.DOWN)
        start.move(leftDirection(dir))
        end.move(Direction.UP, 3)
        end.move(rightDirection(dir))
        end.move(dir, 3)

        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, walls)

        cursor.setPos(pos)
        cursor.move(dir.opposite)
        cursor.move(Direction.UP, 2)
        stair.setOrientation(dir.opposite, true).place(worldIn, cursor)
        cursor.move(Direction.UP)
        walls.place(worldIn, rand, cursor)

        for (o in adjacentDirections(dir)) {
            cursor.setPos(pos)
            cursor.move(dir.opposite)
            cursor.move(Direction.UP)
            cursor.move(o)
            stair.setOrientation(dir.opposite, true).place(worldIn, cursor)
            cursor.move(Direction.UP)
            walls.place(worldIn, rand, cursor)
            cursor.move(Direction.UP)
            walls.place(worldIn, rand, cursor)
            start.setPos(pos)
            start.move(Direction.UP, 3)
            start.move(dir.opposite, 2)
            start.move(o, 2)
            end.setPos(start)
            end.move(dir, 7)
            stair.setOrientation(o, true)
            ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, stair, fillAir = true, replaceSolid = false)
        }

        start.setPos(pos)
        start.move(Direction.UP, 3)
        start.move(dir.opposite, 2)
        end.setPos(start)
        start.move(leftDirection(dir))
        end.move(rightDirection(dir))
        stair.setOrientation(dir.opposite, true)
        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, stair)

        tomb(worldIn, rand, settings, pos, dir)
    }

    private fun tomb(
        worldIn: DungeonWorldIn,
        rand: Random,
        settings: LevelSettings,
        pos: BlockPos.Mutable,
        dir: Direction
    ) {
        val theme = settings.theme
        val cursor = MutableBlockPos()

        val stair = theme.primary.stairs
        val tombStone = QUARTZ_BLOCK_PLACER
        val air = AIR_PLACER

        cursor.setPos(pos)
        cursor.move(dir, 2)
        cursor.move(Direction.UP)
        stair.setOrientation(dir.opposite, true).place(worldIn, cursor)

        cursor.move(dir.opposite)
        stair.setOrientation(dir, true).place(worldIn, cursor)

        cursor.setPos(pos)
        cursor.move(dir, 2)
        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, pos, cursor, air)

        if (rand.nextInt(4) == 0) return

        cursor.setPos(pos)
        tombStone.place(worldIn, cursor)

        if (rand.nextInt(5) != 0) return

        cursor.move(dir)
        val spawnerType = if (rand.nextBoolean()) EntityType.SKELETON else EntityType.ZOMBIE
        generateSpawner(worldIn, rand, settings, cursor, spawnerType)

        cursor.move(dir)
        val chestType = rand.pickRandomly(arrayOf(TreasureType.ARMOR, TreasureType.WEAPONS))
        
        generateChest(worldIn, rand, cursor, chestType, settings.getDifficulty(cursor), false)
    }

    private fun sarcophagus(
        worldIn: DungeonWorldIn,
        rand: Random,
        settings: LevelSettings,
        pos: BlockPos,
        dir: Direction
    ) {
        val theme = settings.theme

        val walls = theme.primary.wall
        val stair = theme.primary.stairs

        val cursor = pos.toMutablePos()
        val start = pos.toMutablePos()
        val end: MutableBlockPos

        start.move(Direction.DOWN)
        start.move(dir, 5)
        end = start.toMutablePos()
        start.move(leftDirection(dir), 2)
        end.move(rightDirection(dir), 2)
        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, walls)

        cursor.setPos(pos)
        cursor.move(dir, 5)
        cursor.move(Direction.UP, 3)
        stair.setOrientation(dir.opposite, true).place(worldIn, cursor)

        start.setPos(pos)

        for (o in adjacentDirections(dir)) {
            start.setPos(pos)
            start.move(Direction.DOWN)
            start.move(dir)
            start.move(o, 3)
            end.setPos(start)
            end.move(dir, 4)
            end.move(Direction.UP, 4)
            ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, walls)
            cursor.setPos(pos)
            cursor.move(Direction.DOWN)
            cursor.move(dir, 5)
            cursor.move(o, 2)
            pillar(worldIn, rand, settings, cursor)
            start.setPos(pos)
            start.move(Direction.UP, 3)
            start.move(o, 2)
            end.setPos(start)
            end.move(dir, 3)
            stair.setOrientation(o.opposite, true)
            ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, stair)
        }

        cursor.setPos(pos)
        tomb(worldIn, rand, settings, cursor, dir)

        cursor.move(Direction.UP)
        stair.setOrientation(dir.opposite, false).place(worldIn, cursor)
        cursor.move(Direction.DOWN, 2)
        stair.setOrientation(dir.opposite, true).place(worldIn, cursor)
        cursor.move(dir)
        walls.place(worldIn, rand, cursor)
        cursor.move(dir)
        walls.place(worldIn, rand, cursor)
        cursor.move(dir)
        stair.setOrientation(dir, false).place(worldIn, cursor)
        cursor.move(Direction.UP)
        stair.setOrientation(dir, true).place(worldIn, cursor)
        cursor.move(Direction.UP)
        stair.setOrientation(dir, false).place(worldIn, cursor)

        for (o in adjacentDirections(dir)) {
            cursor.setPos(pos)
            cursor.move(Direction.DOWN)
            cursor.move(o)
            start.setPos(cursor)
            end.setPos(cursor)
            end.move(dir, 3)
            stair.setOrientation(o, false)
            ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, stair)
            start.move(Direction.UP)
            end.move(Direction.UP)
            stair.setOrientation(o, true)
            ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, stair)
            start.move(Direction.UP)
            end.move(Direction.UP)
            stair.setOrientation(o, false)
            ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, stair)
        }
    }

    private fun mausoleumWall(
        worldIn: DungeonWorldIn,
        rand: Random,
        settings: LevelSettings,
        pos: MutableBlockPos,
        dir: Direction
    ) {
        val theme = settings.theme
        val walls = theme.primary.wall

        val cursor = MutableBlockPos()
        val start = MutableBlockPos()
        val end = MutableBlockPos()

        start.setPos(pos)
        end.setPos(pos)
        start.move(leftDirection(dir), 3)
        end.move(rightDirection(dir), 3)
        end.move(dir, 4)
        end.move(Direction.UP, 4)
        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, walls)

        cursor.setPos(pos)
        cursor.move(Direction.UP)
        tomb(worldIn, rand, settings, cursor, dir)

        cursor.move(Direction.UP, 2)
        tomb(worldIn, rand, settings, cursor, dir)

        for (o in adjacentDirections(dir)) {
            cursor.setPos(pos)
            cursor.move(Direction.UP)
            cursor.move(o, 2)
            tomb(worldIn, rand, settings, cursor, dir)
            cursor.move(Direction.UP, 2)
            tomb(worldIn, rand, settings, cursor, dir)
        }
    }

    private fun pillar(worldIn: DungeonWorldIn, rand: Random, settings: LevelSettings, pos: MutableBlockPos) {
        val theme = settings.theme

        val walls = theme.primary.wall
        val stair = theme.primary.stairs

        val cursor = MutableBlockPos()
        val start = MutableBlockPos()
        val end = MutableBlockPos()

        start.setPos(pos)
        end.setPos(pos)
        end.move(Direction.UP, 4)
        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, walls)

        for (dir in HORIZONTALS) {
            cursor.setPos(end)
            cursor.move(dir)
            stair.setOrientation(dir, true)
            stair.place(worldIn, rand, cursor, fillAir = true, replaceSolid = false)
        }
    }

    override val size = 10
}