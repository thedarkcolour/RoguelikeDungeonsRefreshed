package thedarkcolour.roguelikedungeons.dungeon.theme.placer

import net.minecraft.block.BlockState

abstract class MultiBlockPlacer(protected vararg val blocks: IBlockPlacer) :
    IBlockPlacer {
    constructor(vararg blocks: BlockState) : this(*blocks.map(::SimpleBlockPlacer).toTypedArray())
}