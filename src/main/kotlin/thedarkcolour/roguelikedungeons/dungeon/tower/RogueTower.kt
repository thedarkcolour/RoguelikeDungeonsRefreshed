package thedarkcolour.roguelikedungeons.dungeon.tower

import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.dungeon.theme.TORCH_PLACER
import thedarkcolour.roguelikedungeons.dungeon.theme.Theme
import thedarkcolour.roguelikedungeons.dungeon.theme.placer.IBlockPlacer
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

class RogueTower : ITower {
    override fun generate(worldIn: DungeonWorldIn, rand: Random, theme: Theme, pos: BlockPos) {
        TODO("not implemented")
    }

    private fun addCrenellation(worldIn: IWorld, rand: Random, pos: BlockPos, block: IBlockPlacer) {
        block.place(worldIn, rand, pos)
        if (worldIn.isAirBlock(pos)) return

        TORCH_PLACER.place(worldIn, pos.add(0, 1, 0))
    }
}