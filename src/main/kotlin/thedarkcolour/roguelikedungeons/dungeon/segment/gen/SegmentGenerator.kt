package thedarkcolour.roguelikedungeons.dungeon.segment.gen

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.dungeon.addPillar
import thedarkcolour.roguelikedungeons.dungeon.adjacentDirections
import thedarkcolour.roguelikedungeons.dungeon.level.DungeonLevel
import thedarkcolour.roguelikedungeons.dungeon.segment.ISegment
import thedarkcolour.roguelikedungeons.dungeon.segment.SegmentType
import thedarkcolour.roguelikedungeons.dungeon.theme.Theme
import thedarkcolour.roguelikedungeons.loot.weight.WeightedPool
import thedarkcolour.roguelikedungeons.util.chance
import thedarkcolour.roguelikedungeons.util.nextEnum
import java.util.*
import kotlin.collections.ArrayList

class SegmentGenerator(val arch: SegmentType) : ISegmentGenerator {
    val segments = WeightedPool<SegmentType>()

    fun add(segmentType: SegmentType, weight: Int) {
        segments.add(segmentType, weight)
    }

    override fun generate(worldIn: IWorld, rand: Random, level: DungeonLevel, direction: Direction, pos: BlockPos): List<ISegment> {
        val segments = ArrayList<ISegment>()

        for (adj in adjacentDirections(direction)) {
            val segment = pickSegment(rand, direction, pos) ?: return segments

            segment.generate(worldIn, rand, level, adj, level.settings.theme, pos)

            segments.add(segment)
        }

        if (!level.hasNearbyNode(pos) && rand.chance(3)) {
            addSupport(worldIn, rand, level.settings.theme, pos.x, pos.y, pos.z)
        }

        return segments
    }

    private fun pickSegment(rand: Random, direction: Direction, pos: BlockPos): ISegment? {
        val x = pos.x
        val z = pos.z

        if ((direction == Direction.NORTH || direction == Direction.SOUTH) && z % 3 == 0) {
            if (z % 6 == 0) {
                return arch.get()
            }

            return if (segments.isEmpty()) {
                SegmentType.WALL.get()
            } else {
                segments.get(rand).get()
            }
        } else if ((direction == Direction.WEST || direction == Direction.EAST) && x % 3 == 0) {
            if (x % 6 == 0) {
                return arch.get()
            }

            return if (segments.isEmpty()) {
                SegmentType.WALL.get()
            } else {
                segments.get(rand).get()
            }
        } else {
            return null
        }
    }

    private fun addSupport(worldIn: IWorld, rand: Random, theme: Theme, x: Int, y: Int, z: Int) {
        val pos = BlockPos(x, y - 2, z)
        if (!worldIn.isAirBlock(pos)) return
        addPillar(worldIn, rand, pos, theme.primary.pillar)

        val stair = theme.primary.stairs
        stair.setOrientation(Direction.WEST, true)
        stair.place(worldIn, BlockPos(x - 1, y - 2, z))

        stair.setOrientation(Direction.EAST, true)
        stair.place(worldIn, BlockPos(x + 1, y - 2, z))

        stair.setOrientation(Direction.SOUTH, true)
        stair.place(worldIn, BlockPos(x, y - 2, z + 1))

        stair.setOrientation(Direction.NORTH, true)
        stair.place(worldIn, BlockPos(x, y - 2, z - 1))
    }

    companion object {
        @JvmStatic
        fun getRandom(rand: Random, count: Int): SegmentGenerator {
            val segments = SegmentGenerator(SegmentType.ARCH)

            for (i in 0 until count) {
                segments.add(rand.nextEnum(), 1)
            }

            return segments
        }
    }
}