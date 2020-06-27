package thedarkcolour.roguelikedungeons.dungeon.theme.palette

import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.theme.placer.IBlockPlacer
import thedarkcolour.roguelikedungeons.dungeon.theme.placer.StairsBlockPlacer

@Complete
data class BlockPalette(
    val floor: IBlockPlacer,
    val wall: IBlockPlacer,
    val stairs: StairsBlockPlacer,
    val pillar: IBlockPlacer,
    val door: Door,
    val lightBlock: IBlockPlacer,
    val fluid: IBlockPlacer,
)