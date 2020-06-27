package thedarkcolour.roguelikedungeons.dungeon.segment

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.dungeon.level.DungeonLevel
import thedarkcolour.roguelikedungeons.dungeon.theme.Theme
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

class MushroomSegment : ISegment {
    override fun generateWall(
        worldIn: DungeonWorldIn,
        rand: Random,
        level: DungeonLevel,
        direction: Direction,
        theme: Theme,
        pos: BlockPos
    ) {
        TODO("not implemented")
    }
}