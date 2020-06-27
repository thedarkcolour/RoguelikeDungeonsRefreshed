package thedarkcolour.roguelikedungeons.dungeon.task

import thedarkcolour.roguelikedungeons.dungeon.IDungeon
import thedarkcolour.roguelikedungeons.dungeon.getDungeonRng
import thedarkcolour.roguelikedungeons.dungeon.settings.DungeonSettings
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

class TowerDungeonTask : IDungeonTask {
    override fun execute(worldIn: DungeonWorldIn, rand: Random, dungeon: IDungeon, settings: DungeonSettings) {
        val pos = dungeon.pos

        val tower = settings.towerSettings.tower
        val r = getDungeonRng(worldIn, pos)
        tower.get().generate(worldIn, r, settings.towerSettings.theme, pos)
    }
}
