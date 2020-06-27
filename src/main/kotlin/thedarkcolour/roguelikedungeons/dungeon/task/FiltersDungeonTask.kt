package thedarkcolour.roguelikedungeons.dungeon.task

import thedarkcolour.roguelikedungeons.dungeon.IDungeon
import thedarkcolour.roguelikedungeons.dungeon.settings.DungeonSettings
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

class FiltersDungeonTask : IDungeonTask {
    override fun execute(worldIn: DungeonWorldIn, rand: Random, dungeon: IDungeon, settings: DungeonSettings) {
        val levels = dungeon.levels

        for (level in levels) {
            level.applyFilters(worldIn, rand)
        }
    }
}
