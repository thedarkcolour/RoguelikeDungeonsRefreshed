package thedarkcolour.roguelikedungeons.dungeon

import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.dungeon.level.DungeonLevel
import thedarkcolour.roguelikedungeons.dungeon.settings.DungeonSettings
import thedarkcolour.roguelikedungeons.dungeon.task.DungeonStage
import thedarkcolour.roguelikedungeons.dungeon.task.DungeonTaskRegistry
import thedarkcolour.roguelikedungeons.loot.treasure.TreasureChest
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn

class DungeonGenerator(val worldIn: DungeonWorldIn, val settings: DungeonSettings, override val pos: BlockPos) : IDungeon {
    override val levels = ArrayList<DungeonLevel>(5)

    fun generate() {
        val rand = getDungeonRng(worldIn, pos)

        for (i in 0 until 5) {
            val settings = settings.getLevelSettings(i)
            val level = DungeonLevel(worldIn, rand, settings, pos)
            levels.add(level)
        }

        for (stage in DungeonStage.values()) {
            val task = DungeonTaskRegistry.getTask(stage)

            task.execute(worldIn, rand, this, settings)
        }
    }
    override val chests: List<TreasureChest>
        get() = worldIn.treasure.getChests()
}