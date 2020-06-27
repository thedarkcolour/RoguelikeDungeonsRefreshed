package thedarkcolour.roguelikedungeons.dungeon.segment

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.adjacentDirections
import thedarkcolour.roguelikedungeons.dungeon.level.DungeonLevel
import thedarkcolour.roguelikedungeons.dungeon.theme.AIR_PLACER
import thedarkcolour.roguelikedungeons.dungeon.theme.Theme
import thedarkcolour.roguelikedungeons.dungeon.toMutablePos
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

@Complete
class ArchSegment : ISegment {
    override fun generateWall(
            worldIn: DungeonWorldIn,
            rand: Random,
            level: DungeonLevel,
            direction: Direction,
            theme: Theme,
            pos: BlockPos
    ) {
        val stair = theme.secondary.stairs
        stair.setOrientation(direction.opposite, true)
        val air = AIR_PLACER

        val cursor = pos.toMutablePos()
        air.place(worldIn, cursor.move(direction, 2))
        air.place(worldIn, cursor.move(0, 1, 0))
        stair.place(worldIn, cursor.move(0, 1, 0))

        for (adj in adjacentDirections(direction)) {
            cursor.setPos(pos)
            cursor.move(adj, 1)
            theme.secondary.pillar.place(worldIn, rand, cursor.move(direction, 2))
            theme.secondary.pillar.place(worldIn, rand, cursor.move(0, 1, 0))
            theme.primary.wall.place(worldIn, rand, cursor.move(0, 1, 0))
            stair.place(worldIn, cursor.move(direction.opposite, 1))
        }
    }
}