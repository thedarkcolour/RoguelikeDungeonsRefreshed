package thedarkcolour.roguelikedungeons.dungeon.level

import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.filter.IFilter
import java.util.*

@Complete
class DungeonLevel(
    worldIn: IWorld,
    rand: Random,
    val settings: LevelSettings,
    start: BlockPos
) {
    private lateinit var generator: ILevelGenerator

    fun generate(generator: ILevelGenerator, start: BlockPos) {
        this.generator = generator
        generator.generate(start)
    }

    fun hasNearbyNode(pos: BlockPos): Boolean {
        for (node in generator.layout.nodes) {
            if (node.isNear(pos)) {
                return true
            }
        }

        return false
    }

    val layout: LevelLayout
        get() {
            return generator.layout
        }

    fun encase(worldIn: IWorld, rand: Random) {
        val layout = generator.layout
        val nodes = layout.nodes
        val tunnels = layout.tunnels
        val start = layout.start
        val end = layout.end

        for (node in nodes) {
            if (node == start || node == end) continue
            node.encase(worldIn, rand, settings.theme)
        }

        for (tunnel in tunnels) {
            tunnel.encase(worldIn, rand, settings.theme)
        }
    }

    fun applyFilters(worldIn: IWorld, rand: Random) {
        for (type in settings.filters) {
            val filter = type.get()
            filter(worldIn, rand, filter)
        }
    }

    fun filter(worldIn: IWorld, rand: Random, filter: IFilter) {
        for (box in generator.layout.getCollisions()) {
            filter.apply(worldIn, rand, settings.theme, box)
        }
    }
}