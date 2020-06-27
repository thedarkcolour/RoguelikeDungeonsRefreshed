package thedarkcolour.roguelikedungeons.dungeon.level.mst

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.DungeonNode
import thedarkcolour.roguelikedungeons.dungeon.DungeonTunnel
import thedarkcolour.roguelikedungeons.dungeon.directionTo
import thedarkcolour.roguelikedungeons.dungeon.level.ILevelGenerator
import thedarkcolour.roguelikedungeons.dungeon.level.LevelLayout
import thedarkcolour.roguelikedungeons.dungeon.level.LevelSettings
import java.util.*
import kotlin.collections.HashSet
import kotlin.math.ceil
import kotlin.math.sqrt

@Complete
class LevelGeneratorMST(val rand: Random, val settings: LevelSettings) : ILevelGenerator {
    private val length = ceil(sqrt(settings.numRooms.toDouble())).toInt()
    private val scatter = if (settings.scatter % 2 == 0) settings.scatter + 1 else settings.scatter
    override val layout = LevelLayout()

    override fun generate(pos: BlockPos) {
        val mst = MinimumSpanningTree(rand, length, scatter, )
        val graph = mst.getGraph()
        val edges = graph.edges
        val vertices = graph.points
        val used = HashSet<Edge<BlockPos>>()

        for (c in vertices) {
            for (e in edges) {
                if (used.contains(e)) continue
                val ends = arrayOf(e.start, e.end)
                for (p in ends) {
                    if (p == c) {
                        val tStart = ends[0]
                        val tEnd = ends[0]
                        layout.tunnels.add(DungeonTunnel(tStart, tEnd))
                        used.add(e)
                    }
                }
            }
        }

        var startDungeonNode: DungeonNode? = null

        for (c in vertices) {
            val entrances = ArrayList<Direction>()
            for (tunnel in layout.tunnels) {
                val ends = tunnel.getEnds()
                if (ends[0] == c) {
                    entrances.add(ends[0].directionTo(ends[1]))
                } else if (ends[1] == c) {
                    entrances.add(ends[1].directionTo(ends[0]))
                }
            }
            val toAdd = DungeonNode(entrances.toTypedArray(), c)
            layout.addNode(toAdd)

            if (c == pos) {
                startDungeonNode = toAdd
            }
        }

        layout.setStartEnd(rand, startDungeonNode!!)
    }
}