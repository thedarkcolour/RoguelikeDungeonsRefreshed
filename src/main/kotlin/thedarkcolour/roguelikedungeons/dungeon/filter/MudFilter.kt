package thedarkcolour.roguelikedungeons.dungeon.filter

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.*
import thedarkcolour.roguelikedungeons.dungeon.shape.ShapeType
import thedarkcolour.roguelikedungeons.dungeon.theme.Theme
import thedarkcolour.roguelikedungeons.dungeon.theme.placer.JumbleBlockPlacer
import java.util.*

@Complete
class MudFilter : IFilter {
    override fun apply(worldIn: IWorld, rand: Random, theme: Theme, collidable: ICollidable) {
        for (pos in ShapeType.SOLID_RECTANGLE.get(collidable)) {
            if (rand.nextInt(40) != 0) continue
            if (!isValidLocation(worldIn, rand, pos)) continue

            generate(worldIn, rand, pos, rand.nextInt(3) + 2)
        }
    }

    private fun isValidLocation(worldIn: IWorld, rand: Random, pos: BlockPos): Boolean {
        if (!worldIn.getBlockState(pos).isSolid) return false

        val cursor = MutableBlockPos(pos.x, pos.y + 1, pos.z)
        if (!worldIn.isAirBlock(cursor)) return false

        cursor.move(0, 2, 0)
        if (!worldIn.isAirBlock(cursor)) return false

        cursor.move(0, 1, 1)

        for (direction in Direction.values()) {
            cursor.move(direction)
            if (!worldIn.getBlockState(pos).isSolid) return false
            cursor.move(direction.opposite)
        }

        return true
    }

    // recursion never occurs because of low initial count
    private fun generate(worldIn: IWorld, rand: Random, pos: BlockPos, counter: Int) {
        if (counter <= 0) return

        val cursor = pos.toMutablePos()
        for (direction in HORIZONTALS) {
            if (rand.nextBoolean()) continue
            cursor.move(direction)
            generate(worldIn, rand, cursor, counter - 1)
            cursor.move(direction.opposite)
        }

        if (!isValidLocation(worldIn, rand, pos)) return

        val wet = JumbleBlockPlacer(
            CLAY,
            SOUL_SAND,
            MYCELIUM
        )
        //val dry = JumbleBlockPlacer(PODZOL, DIRT, COARSE_DIRT) // apparently unused

        when (counter) {
            5, 4 -> worldIn.setBlockState(pos, DIRT, 2)
            3 -> if (rand.nextBoolean()) {
                worldIn.setBlockState(pos, COARSE_DIRT, 2)
            }
            2 -> wet.place(worldIn, rand, pos)
            1 -> if (rand.nextBoolean()) {
                wet.place(worldIn, rand, pos)
            }
            else -> worldIn.setBlockState(pos, WATER, 2)
        }

        if (rand.nextInt(6) != 0) return

        val plants = JumbleBlockPlacer(
            POTTED_BROWN_MUSHROOM,
            POTTED_RED_MUSHROOM
        )
        plants.place(worldIn, rand, pos.up())
    }
}