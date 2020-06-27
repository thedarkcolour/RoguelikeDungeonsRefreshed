package thedarkcolour.roguelikedungeons.dungeon.theme.palette

import net.minecraft.block.BlockState
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.state.properties.DoorHingeSide
import net.minecraft.state.properties.Half
import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.Complete

@Complete
class Door(private val state: BlockState) {

    fun generate(worldIn: IWorld, pos: BlockPos, direction: Direction, open: Boolean = false) {
        val bottom = setProperties(state, false, direction, open)
        val top = setProperties(state, true, direction, open)
        worldIn.setBlockState(pos, bottom, 2)
        worldIn.setBlockState(pos, top, 2)
    }

    private fun setProperties(state: BlockState, top: Boolean, direction: Direction, open: Boolean): BlockState {
        var door = state
        door = door.with(BlockStateProperties.HALF, if (top) Half.TOP else Half.BOTTOM)
        door = door.with(BlockStateProperties.HORIZONTAL_FACING, direction)
        door = door.with(BlockStateProperties.OPEN, open)
        door = door.with(BlockStateProperties.DOOR_HINGE, DoorHingeSide.RIGHT)

        return door
    }
}