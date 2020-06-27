package thedarkcolour.roguelikedungeons.dungeon

import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.dungeon.level.DungeonLevel
import thedarkcolour.roguelikedungeons.loot.treasure.TreasureChest

interface IDungeon {
    val pos: BlockPos
    val levels: MutableList<DungeonLevel>
    val chests: List<TreasureChest>
}