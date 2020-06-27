package thedarkcolour.roguelikedungeons.dungeon.task

import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.IDungeon
import thedarkcolour.roguelikedungeons.dungeon.settings.DungeonSettings
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

@Complete
class TunnelsDungeonTask : IDungeonTask {
    override fun execute(worldIn: DungeonWorldIn, rand: Random, dungeon: IDungeon, settings: DungeonSettings) {
        val levels = dungeon.levels

        for (level in levels) {
            for (tunnel in level.layout.tunnels) {
                tunnel.construct(worldIn, rand, level.settings)
            }
        }
    }
}
