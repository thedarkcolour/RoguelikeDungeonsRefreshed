package thedarkcolour.roguelikedungeons.dungeon.level

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.dungeon.*
import thedarkcolour.roguelikedungeons.util.pickRandomly
import java.lang.Integer.max
import java.util.*
import kotlin.collections.ArrayList

class LevelGeneratorClassic(private val rand: Random, private val settings: LevelSettings) : ILevelGenerator {
    private var start = BlockPos.ZERO
    override val layout = LevelLayout()

    override fun generate(start: BlockPos) {
        this.start = start
        val nodes = ArrayList<Node>()
        val startNode = Node(rand.pickRandomly(HORIZONTALS), start)
        nodes.add(startNode)

        while (!isDone(nodes)) {
            update(nodes)
        }

        for (n in nodes) {
            n.cull()
        }

        var startDungeonNode: DungeonNode? = null

        for (node in nodes) {
            val dungeonNode = DungeonNode(node.getEntrances(), node.pos)

            if (node == startNode) {
                startDungeonNode = dungeonNode
            }
            layout.addNode(dungeonNode)
            layout.addTunnels(node.createTunnels())
        }

        layout.setStartEnd(rand, startDungeonNode!!)
    }

    private fun update(nodes: MutableList<Node>) {
        if (!full(nodes)) {
            for (node in nodes) {
                node.update(nodes)
            }
        }
    }

    private fun isDone(nodes: List<Node>): Boolean {
        var a = true

        for (node in nodes) {
            if (!node.isDone()) {
                a = false
                break
            }
        }

        return a || full(nodes)
    }

    private fun full(nodes: List<Node>): Boolean {
        return nodes.size >= max(settings.numRooms, MIN_ROOMS)
    }

    private inner class Node(val direction: Direction, val pos: BlockPos) {
        var tunnelers = ArrayList<Tunneler>()

        init {
            spawnTunnelers()
        }

        private fun spawnTunnelers() {
            if (start.distanceHorizontal(pos) > settings.range) {
                return
            }

            for (direction in HORIZONTALS) {
                if (direction == this.direction.opposite) {
                    continue
                }

                tunnelers.add(Tunneler(direction, start))
            }
        }

        fun update(nodes: MutableList<Node>) {
            for (tunneler in tunnelers) {
                tunneler.update(nodes)
            }
        }

        fun isDone(): Boolean {
            for (tunneler in tunnelers) {
                if (!tunneler.done) return false
            }

            return true
        }

        fun getEntrances(): Array<Direction> {
            val list = ArrayList<Direction>()
            list.add(direction.opposite)

            for (tunneler in tunnelers) {
                list.add(tunneler.direction)
            }

            return list.toTypedArray()
        }

        fun createTunnels(): List<DungeonTunnel> {
            val tunnels = ArrayList<DungeonTunnel>()
            for (tunneler in tunnelers) {
                tunnels.add(tunneler.createTunnel())
            }
            return tunnels
        }

        fun cull() {
            tunnelers.removeIf { tunneler ->
                !tunneler.done
            }
        }
    }

    private inner class Tunneler(val direction: Direction, val start: BlockPos) {
        private val end = start.toMutablePos()
        var done = false
        var extend = settings.scatter * 2

        fun update(nodes: MutableList<Node>) {
            if (done) return

            if (hasNearbyNode(nodes, end, settings.scatter)) {
                end.move(direction)
            } else {
                if (rand.nextInt(extend) == 0) {
                    spawnNode(nodes, this)
                    done = true
                } else {
                    end.move(direction)
                    extend--
                }
            }
        }

        fun hasNearbyNode(nodes: List<Node>, pos: BlockPos, min: Int): Boolean {
            for (node in nodes) {
                if (node.pos.distanceHorizontal(pos) < min) {
                    return true
                }
            }
            return false
        }

        fun spawnNode(nodes: MutableList<Node>, tunneler: Tunneler) {
            val toAdd = Node(tunneler.direction, tunneler.end.toImmutable())
            nodes.add(toAdd)
        }

        fun createTunnel(): DungeonTunnel {
            return DungeonTunnel(start, end)
        }
    }

    companion object {
        const val MIN_ROOMS = 6
    }
}