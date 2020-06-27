@file:Suppress("HasPlatformType")

package thedarkcolour.roguelikedungeons.dungeon

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.VineBlock
import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.dungeon.shape.ShapeType
import thedarkcolour.roguelikedungeons.dungeon.theme.AIR_PLACER
import thedarkcolour.roguelikedungeons.dungeon.theme.placer.IBlockPlacer
import thedarkcolour.roguelikedungeons.dungeon.theme.placer.StairsBlockPlacer
import java.util.*

val COBWEB = Blocks.COBWEB.defaultState
val VINE = Blocks.VINE.defaultState
val CLAY = Blocks.CLAY.defaultState
val SOUL_SAND = Blocks.SOUL_SAND.defaultState
val MYCELIUM = Blocks.MYCELIUM.defaultState
val PODZOL = Blocks.PODZOL.defaultState
val DIRT = Blocks.DIRT.defaultState
val COARSE_DIRT = Blocks.COARSE_DIRT.defaultState
val WATER = Blocks.WATER.defaultState
val LAVA = Blocks.LAVA.defaultState
val POTTED_BROWN_MUSHROOM = Blocks.POTTED_BROWN_MUSHROOM.defaultState
val POTTED_RED_MUSHROOM = Blocks.POTTED_RED_MUSHROOM.defaultState
val SEA_LANTERN = Blocks.SEA_LANTERN.defaultState
val IRON_BLOCK = Blocks.IRON_BLOCK.defaultState
val GOLD_BLOCK = Blocks.GOLD_BLOCK.defaultState
val DIAMOND_BLOCK = Blocks.DIAMOND_BLOCK.defaultState
val EMERALD_BLOCK = Blocks.EMERALD_BLOCK.defaultState
val LAPIS_BLOCK = Blocks.LAPIS_BLOCK.defaultState
val STONE_BRICKS = Blocks.STONE_BRICKS.defaultState
val BRICKS = Blocks.BRICKS.defaultState
val GLOWSTONE = Blocks.GLOWSTONE.defaultState
val STONE_BRICK_STAIRS = Blocks.STONE_BRICK_STAIRS.defaultState
val BRICK_STAIRS = Blocks.BRICK_STAIRS.defaultState
val QUARTZ_STAIRS = Blocks.QUARTZ_STAIRS.defaultState
val CRACKED_STONE_BRICKS = Blocks.CRACKED_STONE_BRICKS.defaultState
val COBBLESTONE = Blocks.COBBLESTONE.defaultState
val MOSSY_COBBLESTONE = Blocks.MOSSY_COBBLESTONE.defaultState
val GRAVEL = Blocks.GRAVEL.defaultState
val SPRUCE_PLANKS = Blocks.SPRUCE_PLANKS.defaultState
val SPRUCE_STAIRS = Blocks.SPRUCE_STAIRS.defaultState
val ACACIA_STAIRS = Blocks.ACACIA_STAIRS.defaultState
val VERTICAL_SPRUCE_LOG = Blocks.SPRUCE_LOG.defaultState

fun generateVinesArea(worldIn: IWorld, rand: Random, start: BlockPos, end: BlockPos) {
    for (cursor in ShapeType.SOLID_RECTANGLE.get(start, end)) {
        generateVines(worldIn, cursor)
    }
}

fun generateVines(worldIn: IWorld, pos: BlockPos) {
    if (!worldIn.isAirBlock(pos)) return
    val cursor = pos.toMutablePos()

    for (direction in HORIZONTALS) {
        cursor.move(direction)

        if (VINE.isValidPosition(worldIn, pos)) {
            worldIn.setBlockState(pos, getVineState(direction), 2)
        }
    }
}

fun addPillar(worldIn: IWorld, rand: Random, pos: BlockPos, block: IBlockPlacer) {
    val cursor = pos.toMutablePos()

    while (!worldIn.getBlockState(cursor).isOpaqueCube(worldIn, cursor) && cursor.y > 1) {
        block.place(worldIn, rand, cursor)
        cursor.move(Direction.DOWN)
    }
}

fun addSpiralStairsStep(worldIn: IWorld, rand: Random, pos: BlockPos, stairs: StairsBlockPlacer, fill: IBlockPlacer) {
    val air = AIR_PLACER

    val cursor = pos.toMutablePos()
    val start = BlockPos(pos.x - 1, pos.y, pos.z - 1)
    val end = BlockPos(pos.x + 1, pos.y, pos.z + 1)

    ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, air)
    fill.place(worldIn, rand, pos)

    val direction = HORIZONTALS[pos.y % 4]
    cursor.move(direction)
    stairs.setOrientation(leftDirection(direction), false).place(worldIn, pos)
    cursor.move(rightDirection(direction))
    stairs.setOrientation(rightDirection(direction), true).place(worldIn, pos)
    cursor.move(direction.opposite)
    stairs.setOrientation(direction.opposite, true).place(worldIn, pos)
}

private fun getVineState(direction: Direction): BlockState {
    return VINE
        .with(VineBlock.NORTH, direction == Direction.NORTH)
        .with(VineBlock.EAST, direction == Direction.EAST)
        .with(VineBlock.SOUTH, direction == Direction.SOUTH)
        .with(VineBlock.WEST, direction == Direction.WEST)
}