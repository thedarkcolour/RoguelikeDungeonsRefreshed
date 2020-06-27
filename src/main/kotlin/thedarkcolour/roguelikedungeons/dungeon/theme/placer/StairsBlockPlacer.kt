package thedarkcolour.roguelikedungeons.dungeon.theme.placer

import net.minecraft.block.BlockState
import net.minecraft.block.StairsBlock
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.state.properties.Half
import net.minecraft.util.Direction
import thedarkcolour.roguelikedungeons.util.checkArgument

class StairsBlockPlacer(state: BlockState) : SimpleBlockPlacer(state) {
    init {
        checkArgument(state.block is StairsBlock, "Stairs block placer created with non-stairs block '${state.block.registryName}'")
    }

    fun setOrientation(direction: Direction, flip: Boolean): StairsBlockPlacer {
        var stair = state

        stair = stair.with(BlockStateProperties.HORIZONTAL_FACING, direction)
        stair = stair.with(BlockStateProperties.HALF, if (flip) Half.TOP else Half.BOTTOM)

        state = stair

        return this
    }
}