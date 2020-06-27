package thedarkcolour.roguelikedungeons.dungeon.theme.placer

import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.Complete
import java.util.*

@Complete
class CheckeredBlockPlacer(
    private val dark: IBlockPlacer,
    private val light: IBlockPlacer,
    private val offset: BlockPos = BlockPos.ZERO
) : IBlockPlacer {

    override fun place(worldIn: IWorld, rand: Random, pos: BlockPos, fillAir: Boolean, replaceSolid: Boolean): Boolean {
        val x = pos.x - offset.x
        val y = pos.y - offset.y
        val z = pos.z - offset.z

        if (x % 2 == 0) {
            return if (z % 2 == 0) {
                if (y % 2 == 0) {
                    dark.place(worldIn, rand, pos, fillAir, replaceSolid)
                } else {
                    light.place(worldIn, rand, pos, fillAir, replaceSolid)
                }
            } else {
                if (y % 2 == 0) {
                    light.place(worldIn, rand, pos, fillAir, replaceSolid)
                } else {
                    dark.place(worldIn, rand, pos, fillAir, replaceSolid)
                }
            }
        } else {
            return if (z % 2 == 0) {
                if (y % 2 == 0) {
                    light.place(worldIn, rand, pos, fillAir, replaceSolid)
                } else {
                    dark.place(worldIn, rand, pos, fillAir, replaceSolid)
                }
            } else {
                if (y % 2 == 0) {
                    dark.place(worldIn, rand, pos, fillAir, replaceSolid)
                } else {
                    light.place(worldIn, rand, pos, fillAir, replaceSolid)
                }
            }
        }
    }
}