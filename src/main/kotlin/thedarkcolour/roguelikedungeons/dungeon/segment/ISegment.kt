package thedarkcolour.roguelikedungeons.dungeon.segment

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.level.DungeonLevel
import thedarkcolour.roguelikedungeons.dungeon.theme.Theme
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

@Complete
interface ISegment {
    fun generateWall(worldIn: DungeonWorldIn, rand: Random, level: DungeonLevel, direction: Direction, theme: Theme, pos: BlockPos)

    // Default methods

    fun generate(worldIn: IWorld, rand: Random, level: DungeonLevel, direction: Direction, theme: Theme, pos: BlockPos) {
        if (level.hasNearbyNode(pos)) return
    }
}