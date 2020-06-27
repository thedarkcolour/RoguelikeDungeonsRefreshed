package thedarkcolour.roguelikedungeons.dungeon.theme

import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.theme.palette.BlockPalette

@Complete
data class Theme(
    val primary: BlockPalette,
    val secondary: BlockPalette
)