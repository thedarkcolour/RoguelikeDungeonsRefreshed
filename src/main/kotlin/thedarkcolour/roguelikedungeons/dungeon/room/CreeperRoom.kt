package thedarkcolour.roguelikedungeons.dungeon.room

import net.minecraft.entity.EntityType
import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.dungeon.level.LevelSettings
import thedarkcolour.roguelikedungeons.dungeon.shape.ShapeType
import thedarkcolour.roguelikedungeons.dungeon.spawner.generateSpawner
import thedarkcolour.roguelikedungeons.dungeon.theme.GRAVEL_PLACER
import thedarkcolour.roguelikedungeons.dungeon.theme.MOSSY_COBBLESTONE_PLACER
import thedarkcolour.roguelikedungeons.dungeon.theme.TNT_PLACER
import thedarkcolour.roguelikedungeons.dungeon.theme.placer.WeightedBlockPlacer
import thedarkcolour.roguelikedungeons.dungeon.toMutablePos
import thedarkcolour.roguelikedungeons.loot.treasure.TreasureType
import thedarkcolour.roguelikedungeons.loot.treasure.generateChest
import thedarkcolour.roguelikedungeons.loot.treasure.isValidChestSpace
import thedarkcolour.roguelikedungeons.loot.weight.WeightedEntry
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

class CreeperRoom : IDungeonRoom {
    override fun generate(worldIn: DungeonWorldIn, rand: Random, settings: LevelSettings, entrances: Array<Direction>, pos: BlockPos): Boolean {
        val theme = settings.theme

        val start = pos.toMutablePos()
        val end = pos.toMutablePos()

        val tnt = TNT_PLACER

        val mossy = WeightedBlockPlacer(arrayOf(WeightedEntry(theme.primary.wall, 3), WeightedEntry(MOSSY_COBBLESTONE_PLACER, 1)))
        val floor = WeightedBlockPlacer(arrayOf(WeightedEntry(theme.primary.floor, 1), WeightedEntry(MOSSY_COBBLESTONE_PLACER, 1), WeightedEntry(GRAVEL_PLACER, 3)))

        val subfloor = WeightedBlockPlacer(arrayOf(WeightedEntry(floor, 3), WeightedEntry(tnt, 1)))

        start.setPos(pos)
        end.setPos(pos)
        start.move(-4, -4, -4)
        end.move(4, 5, 4)
        ShapeType.HOLLOW_RECTANGLE.fill(worldIn, rand, start, end, mossy, fillAir = false, replaceSolid = true)

        start.setPos(pos)
        end.setPos(pos)
        start.move(-3, -1, -3)
        end.move(3, -1, 3)
        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, floor, fillAir = true, replaceSolid = true)

        start.setPos(pos)
        end.setPos(pos)
        start.move(-3, -3, -3)
        end.move(3, -2, 3)
        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, subfloor, fillAir = true, replaceSolid = true)

        start.setPos(pos)
        end.setPos(pos)
        start.move(-3, 0, -3)
        end.move(3, 0, 3)

        val chestSpaces = ShapeType.SOLID_RECTANGLE.get(start, end).coordinates
        chestSpaces.shuffle(rand)

        var counter = 0
        for (spot in chestSpaces) {
            if (isValidChestSpace(worldIn, spot)) {
                generateChest(worldIn, rand, spot, TreasureType.ORE, settings.getDifficulty(spot), true)

                val cursor = spot.toMutablePos()
                cursor.move(Direction.DOWN, 2)
                tnt.place(worldIn, cursor)
                ++counter
            }
            if (counter >= 2) break
        }

        generateSpawner(worldIn, rand, settings, pos, EntityType.CREEPER)

        return true
    }

    override val size = 7
}