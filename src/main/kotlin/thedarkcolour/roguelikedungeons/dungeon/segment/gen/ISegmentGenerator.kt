package thedarkcolour.roguelikedungeons.dungeon.segment.gen

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.dungeon.level.DungeonLevel
import thedarkcolour.roguelikedungeons.dungeon.segment.ISegment
import java.util.*

interface ISegmentGenerator {
    fun generate(worldIn: IWorld, rand: Random, level: DungeonLevel, direction: Direction, pos: BlockPos): List<ISegment>
}