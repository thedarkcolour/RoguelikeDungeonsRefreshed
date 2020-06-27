package thedarkcolour.roguelikedungeons.dungeon.filter

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.COBWEB
import thedarkcolour.roguelikedungeons.dungeon.ICollidable
import thedarkcolour.roguelikedungeons.dungeon.shape.ShapeType
import thedarkcolour.roguelikedungeons.dungeon.theme.Theme
import thedarkcolour.roguelikedungeons.dungeon.toMutablePos
import thedarkcolour.roguelikedungeons.util.nextEnum
import java.util.*

@Complete
class CobwebFilter : IFilter {
    override fun apply(worldIn: IWorld, rand: Random, theme: Theme, collidable: ICollidable) {
        for (pos in ShapeType.SOLID_RECTANGLE.get(collidable)) {
            if (
                rand.nextInt(60) != 0 ||
                !worldIn.isAirBlock(pos) ||
                !isValidLocation(worldIn, pos)
            ) continue

            generate(worldIn, rand, pos, rand.nextInt(2) + 2)
        }
    }

    private fun isValidLocation(worldIn: IWorld, pos: BlockPos): Boolean {
        val cursor = pos.toMutablePos()

        for (direction in Direction.values()) {
            cursor.move(direction)
            if (!worldIn.isAirBlock(cursor)) {
                return true
            }

            cursor.move(direction.opposite)
        }

        return false
    }

    private fun generate(worldIn: IWorld, rand: Random, pos: BlockPos, count: Int) {
        if (!worldIn.isAirBlock(pos) || count <= 0) return

        worldIn.setBlockState(pos, COBWEB, 2)

        val cursor = pos.toMutablePos()

        for (i in 0..2) {
            val dir = rand.nextEnum<Direction>()
            cursor.move(dir)
            generate(worldIn, rand, pos, count)
        }
    }
}