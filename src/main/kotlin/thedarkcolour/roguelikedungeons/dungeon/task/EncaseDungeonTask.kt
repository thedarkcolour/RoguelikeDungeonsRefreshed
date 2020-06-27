package thedarkcolour.roguelikedungeons.dungeon.task

import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.config.Config
import thedarkcolour.roguelikedungeons.dungeon.IDungeon
import thedarkcolour.roguelikedungeons.dungeon.filter.FilterType
import thedarkcolour.roguelikedungeons.dungeon.settings.DungeonSettings
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

@Complete
class EncaseDungeonTask : IDungeonTask {
    override fun execute(worldIn: DungeonWorldIn, rand: Random, dungeon: IDungeon, settings: DungeonSettings) {
        val levels = dungeon.levels

        if (Config.encase) {
            for (level in levels) {
                level.filter(worldIn, rand, FilterType.ENCASE.get())
            }
        }
    }
}