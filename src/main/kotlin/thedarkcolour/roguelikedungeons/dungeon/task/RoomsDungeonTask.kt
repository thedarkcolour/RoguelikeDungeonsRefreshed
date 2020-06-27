package thedarkcolour.roguelikedungeons.dungeon.task

import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.IDungeon
import thedarkcolour.roguelikedungeons.dungeon.settings.DungeonSettings
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

@Complete
class RoomsDungeonTask : IDungeonTask {
    override fun execute(worldIn: DungeonWorldIn, rand: Random, dungeon: IDungeon, settings: DungeonSettings) {
        val levels = dungeon.levels

        for (level in levels) {
            val layout = level.layout
            val nodes = layout.nodes
            val start = layout.start!!
            val end = layout.end!!

            for (node in nodes) {
                if (node == start || node == end) continue

                val room = node.room!!
                room.generate(worldIn, rand, level.settings, node.entrances, node.pos)
            }
        }
    }
}
