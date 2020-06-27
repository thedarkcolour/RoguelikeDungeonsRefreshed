package thedarkcolour.roguelikedungeons.dungeon.task

import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.IDungeon
import thedarkcolour.roguelikedungeons.dungeon.getDungeonRng
import thedarkcolour.roguelikedungeons.dungeon.room.DungeonRoomType
import thedarkcolour.roguelikedungeons.dungeon.settings.DungeonSettings
import thedarkcolour.roguelikedungeons.dungeon.toMutablePos
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

@Complete
class LayoutDungeonTask : IDungeonTask {
    override fun execute(worldIn: DungeonWorldIn, random: Random, dungeon: IDungeon, settings: DungeonSettings) {
        val levels = dungeon.levels
        val start = dungeon.pos.toMutablePos()
        var rand = random

        for (level in levels) {
            val generator = level.settings.createLevelGenerator(random)

            level.generate(generator, start)

            val layout = generator.layout
            rand = getDungeonRng(worldIn, start)
            start.setPos(layout.end!!.pos)
            start.move(0, -10, 0)
        }

        for (level in levels) {
            val layout = level.layout
            val rooms = level.settings.rooms
            var count = 0

            while (layout.hasEmptyRooms()) {
                val room = if (count < level.settings.numRooms) {
                    rooms.getRoom(rand)
                } else DungeonRoomType.CORNER.get()

                val node = layout.getBestFit(room)

                node?.room = room

                ++count
            }
        }
    }
}