package thedarkcolour.roguelikedungeons.dungeon.task

import thedarkcolour.roguelikedungeons.dungeon.IDungeon
import thedarkcolour.roguelikedungeons.dungeon.settings.DungeonSettings
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

interface IDungeonTask {
    fun execute(worldIn: DungeonWorldIn, rand: Random, dungeon: IDungeon, settings: DungeonSettings)
}