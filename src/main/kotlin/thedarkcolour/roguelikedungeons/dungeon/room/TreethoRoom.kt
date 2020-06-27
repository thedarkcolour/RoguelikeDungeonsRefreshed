package thedarkcolour.roguelikedungeons.dungeon.room

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.dungeon.*
import thedarkcolour.roguelikedungeons.dungeon.level.LevelSettings
import thedarkcolour.roguelikedungeons.dungeon.level.mst.MinimumSpanningTree
import thedarkcolour.roguelikedungeons.dungeon.shape.ShapeType
import thedarkcolour.roguelikedungeons.dungeon.theme.*
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

class TreethoRoom : IDungeonRoom {
    override fun generate(worldIn: DungeonWorldIn, rand: Random, settings: LevelSettings, entrances: Array<Direction>, pos: BlockPos): Boolean {
        val theme = settings.theme
        val wall = theme.primary.wall
        val dir = entrances[0]


        val cursor = pos.toMutablePos()
        val start = pos.toMutablePos()
        val end = pos.toMutablePos()

        start.move(-11, -1, -11)
        end.move(11, 8, 11)

        ShapeType.HOLLOW_RECTANGLE.fill(worldIn, rand, start, end, wall, fillAir = false, replaceSolid = true)

        val birchSlab = FLIPPED_BIRCH_SLAB_PLACER
        val pumpkin = PUMPKIN_STEM_PLACER

        start.move(-9, 8, -9)
        end.move(9, 8, 9)
        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, birchSlab)
        start.move(Direction.UP)
        end.move(Direction.UP)
        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, pumpkin, fillAir = true, replaceSolid = true)

        cursor.move(0, 8, 0)
        ceiling(worldIn, rand, settings, cursor)

        cursor.setPos(pos)
        treeFarm(worldIn, rand, cursor, dir)

        for (o in adjacentDirections(dir)) {
            cursor.setPos(pos)
            cursor.move(o, 5)
            treeFarm(worldIn, rand, cursor, dir)
        }

        return true
    }

    private fun treeFarm(editor: IWorld, rand: Random, origin: BlockPos, dir: Direction) {
        val cursor = origin.toMutablePos()
        val start = origin.toMutablePos()
        val end = origin.toMutablePos()

        val slab = SANDSTONE_SLAB_PLACER
        val light = JACK_O_LANTERN_PLACER
        val sapling = BIRCH_SAPLING_PLACER
        val glass = YELLOW_STAINED_GLASS_PLACER
        val dirt = DIRT_PLACER
        
        start.move(leftDirection(dir))
        end.move(rightDirection(dir))
        start.move(dir.opposite, 7)
        end.move(dir, 7)
        ShapeType.SOLID_RECTANGLE.fill(editor, rand, start, end, slab, fillAir = true, replaceSolid = true)
        cursor.move(dir.opposite, 6)
        
        for (i in 0..12) {
            if (i % 2 == 0) {
                val p = cursor.toMutablePos()
                
                if (i % 4 == 0) {
                    sapling.place(editor, p)
                    p.move(Direction.DOWN)
                    dirt.place(editor, p)
                } else {
                    glass.place(editor, p)
                    p.move(Direction.DOWN)
                    light.place(editor, p)
                }
            }
            cursor.move(dir)
        }
    }

    private fun ceiling(editor: IWorld, rand: Random, settings: LevelSettings, origin: MutableBlockPos) {
        val fill = SPRUCE_PLANKS_PLACER
        val tree = MinimumSpanningTree(rand, 7, 3)
        tree.generate(editor, rand, fill, origin)

        for (dir in HORIZONTALS) {
            val start = origin.toMutablePos()
            start.move(dir, 9)
            val end = start.toMutablePos()
            start.move(leftDirection(dir), 9)
            end.move(rightDirection(dir), 9)
            ShapeType.SOLID_RECTANGLE.fill(editor, rand, start, end, fill, fillAir = true, replaceSolid = true)
            val cursor = origin.toMutablePos()
            cursor.move(Direction.DOWN)
            cursor.move(dir, 10)
            cursor.move(leftDirection(dir), 10)

            for (i in 0..4) {
                pillar(editor, rand, settings, cursor)
                cursor.move(rightDirection(dir), 4)
            }
        }
    }

    private fun pillar(worldIn: IWorld, rand: Random, settings: LevelSettings, origin: MutableBlockPos) {
        val primary = settings.theme.primary
        val pillar = primary.pillar
        val stair = primary.stairs
        val cursor = origin.toMutablePos()
        addPillar(worldIn, rand, cursor, pillar)

        for (dir in HORIZONTALS) {
            cursor.setPos(origin)
            cursor.move(dir)

            if (worldIn.isAirBlock(cursor)) {
                stair.setOrientation(dir, true).place(worldIn, cursor)
            }
        }
    }

    override val size: Int
        get() = 12
}