package thedarkcolour.roguelikedungeons.dungeon.task

import net.minecraft.util.Direction
import thedarkcolour.roguelikedungeons.dungeon.*
import thedarkcolour.roguelikedungeons.dungeon.level.DungeonLevel
import thedarkcolour.roguelikedungeons.dungeon.level.LevelSettings
import thedarkcolour.roguelikedungeons.dungeon.room.DungeonLinker
import thedarkcolour.roguelikedungeons.dungeon.room.DungeonLinkerTop
import thedarkcolour.roguelikedungeons.dungeon.settings.DungeonSettings
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

class LinksDungeonTask : IDungeonTask {
    override fun execute(worldIn: DungeonWorldIn, rand: Random, dungeon: IDungeon, settings: DungeonSettings) {
        val levels = dungeon.levels

        var previous: DungeonLevel? = null
        for (level in levels) {
            val upper = previous?.layout?.end ?: continue
            val lower = level.layout.start!!

            generateLevelLink(worldIn, rand, level.settings, lower, upper)
            previous = level
        }
    }

    private fun generateLevelLink(worldIn: DungeonWorldIn, rand: Random, settings: LevelSettings, start: DungeonNode, end: DungeonNode) {
        val downstairs = DungeonLinker()
        downstairs.generate(worldIn, rand, settings, HORIZONTALS, start.pos)

        val upstairs = DungeonLinkerTop()
        upstairs.generate(worldIn, rand, settings, end.entrances, end.pos)

        val stair = settings.theme.primary.stairs
        val cursor = start.pos.toMutablePos()

        for (i in 0 until end.pos.y - start.pos.y) {
            addSpiralStairsStep(worldIn, rand, cursor, stair, settings.theme.primary.pillar)

            cursor.move(Direction.UP)
        }
    }
}
