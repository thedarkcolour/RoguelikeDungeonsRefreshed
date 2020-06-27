package thedarkcolour.roguelikedungeons.dungeon.task

import thedarkcolour.roguelikedungeons.dungeon.IDungeon
import thedarkcolour.roguelikedungeons.dungeon.settings.DungeonSettings
import thedarkcolour.roguelikedungeons.loot.book.StatisticsBook
import thedarkcolour.roguelikedungeons.loot.treasure.TreasureType
import thedarkcolour.roguelikedungeons.loot.weight.WeightedEntry
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

class LootDungeonTask : IDungeonTask {
    override fun execute(worldIn: DungeonWorldIn, rand: Random, dungeon: IDungeon, settings: DungeonSettings) {
        val treasure = worldIn.treasure
        settings.processLoot(rand, treasure)
        treasure.addItem(rand, TreasureType.STARTER, WeightedEntry(StatisticsBook.create(worldIn), 0), 1)
    }
}
