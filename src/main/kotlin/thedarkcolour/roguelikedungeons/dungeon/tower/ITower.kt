package thedarkcolour.roguelikedungeons.dungeon.tower

import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.dungeon.theme.Theme
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

interface ITower {
    fun generate(worldIn: DungeonWorldIn, rand: Random, theme: Theme, pos: BlockPos)
}