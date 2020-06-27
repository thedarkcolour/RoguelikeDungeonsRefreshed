package thedarkcolour.roguelikedungeons.dungeon.room

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.dungeon.*
import thedarkcolour.roguelikedungeons.dungeon.level.LevelSettings
import thedarkcolour.roguelikedungeons.dungeon.shape.ShapeType
import thedarkcolour.roguelikedungeons.dungeon.spawner.generateRandomSpawner
import thedarkcolour.roguelikedungeons.dungeon.theme.AIR_PLACER
import thedarkcolour.roguelikedungeons.loot.treasure.TreasureType
import thedarkcolour.roguelikedungeons.loot.treasure.generateChests
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*
import kotlin.collections.ArrayList

class BrickRoom : IDungeonRoom {
    override fun generate(
            worldIn: DungeonWorldIn,
            rand: Random,
            settings: LevelSettings,
            entrances: Array<Direction>,
            pos: BlockPos
    ): Boolean {
        val x = pos.x
        val y = pos.y
        val z = pos.z

        val primary = settings.theme.primary
        val stairs = primary.stairs
        val blocks = primary.wall
        val pillar = primary.pillar
        val floor = primary.floor
        val air = AIR_PLACER

        // fill air
        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, BlockPos(x - 3, y, z - 3), BlockPos(x - 3, y + 3, z - 3), air)
        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, BlockPos(x - 1, y + 4, z - 1), BlockPos(x - 3, y + 3, z - 3), air)

        // shell
        ShapeType.HOLLOW_RECTANGLE.fill(worldIn, rand, BlockPos(x - 4, y - 1, z - 4), BlockPos(x + 4, y + 4, z + 4), blocks, fillAir = false, replaceSolid = true)
        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, BlockPos(x - 4, y - 1, z - 4), BlockPos(x + 4, y - 1, z + 4), floor, fillAir = false, replaceSolid = true)

        val start = MutableBlockPos()
        val end = MutableBlockPos()
        val cursor = MutableBlockPos(x, y, z)

        air.place(worldIn, cursor.move(0, 5, 0))
        blocks.place(worldIn, rand, cursor.move(0, 1, 0))

        val coordinates = ArrayList<BlockPos>()

        for (dir in HORIZONTALS) {
            // top
            cursor.setPos(x, y + 5, z)
            cursor.move(dir, 1)
            stairs.setOrientation(dir.opposite, true)
            stairs.place(worldIn, rand, cursor, fillAir = false, replaceSolid = true)
            cursor.move(leftDirection(dir), 1)
            blocks.place(worldIn, rand, cursor, fillAir = false, replaceSolid = true)

            cursor.setPos(x, y, z)
            cursor.move(dir, 2)
            air.place(worldIn, cursor.move(0, 4, 0))
            blocks.place(worldIn, rand, cursor, fillAir = false, replaceSolid = true)

            // pillar
            cursor.setPos(x, y, z)
            cursor.move(dir, 3)

            start.setPos(cursor.move(leftDirection(dir), 3))
            end.setPos(cursor.move(0, 2, 0))

            ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, pillar, fillAir = true, replaceSolid = true)
            blocks.place(worldIn, rand, cursor.move(0, 1, 0))

            // pillar stairs
            for (adj in adjacentDirections(dir)) {
                cursor.setPos(x, y + 3, z)
                cursor.move(dir, 3)
                cursor.move(adj, 2)
                stairs.setOrientation(adj.opposite, true)
                stairs.place(worldIn, rand, cursor)
            }

            // layer above pillars
            cursor.setPos(x, y + 4, z)
            cursor.move(dir, 2)
            blocks.place(worldIn, rand, cursor.move(leftDirection(dir), 2))

            for (adj in adjacentDirections(dir)) {
                cursor.setPos(x, y + 4, z)
                cursor.move(dir, 2)
                cursor.move(adj, 1)
                stairs.setOrientation(adj.opposite, true)
                stairs.place(worldIn, rand, cursor, fillAir = false, replaceSolid = true)
            }

            cursor.setPos(x, y + 5, z)
            cursor.move(dir, 1)
            cursor.move(leftDirection(dir), 1)
            blocks.place(worldIn, rand, cursor, fillAir = false, replaceSolid = true)

            for (adj in adjacentDirections(dir)) {
                cursor.setPos(x, y, z)
                cursor.move(dir, 3)
                cursor.move(adj, 2)

                coordinates.add(cursor.toImmutable())
            }
        }

        val types = arrayListOf(TreasureType.ARMOR, TreasureType.WEAPONS, TreasureType.TOOLS)
        generateChests(worldIn, rand, 1, coordinates, types, getDungeonLevel(pos))
        generateRandomSpawner(worldIn, rand, settings, BlockPos(x, y, z))

        return true
    }

    override val size = 6
}