package thedarkcolour.roguelikedungeons.dungeon.level

import thedarkcolour.roguelikedungeons.dungeon.DungeonNode
import thedarkcolour.roguelikedungeons.dungeon.DungeonTunnel
import thedarkcolour.roguelikedungeons.dungeon.ICollidable
import thedarkcolour.roguelikedungeons.dungeon.distanceHorizontal
import thedarkcolour.roguelikedungeons.dungeon.room.IDungeonRoom
import java.util.*
import kotlin.collections.ArrayList

class LevelLayout {
    val nodes = ArrayList<DungeonNode>()
    val tunnels = ArrayList<DungeonTunnel>()
    var start: DungeonNode? = null
    var end: DungeonNode? = null

    fun setStartEnd(rand: Random, start: DungeonNode) {
        this.start = start

        var attempts = 0
        do {
            end = nodes[rand.nextInt(nodes.size)]
            ++attempts
        } while (end == this.start || end!!.pos.distanceHorizontal(start.pos) > 16 + attempts * 2)
    }

    fun getBestFit(room: IDungeonRoom): DungeonNode? {
        for (node in nodes) {
            if (node == start || node == end) continue
            if (node.room != null) continue
            if (overlaps(node, room.size)) continue

            return node
        }

        for (node in nodes) {
            if (node == start || node == end) continue
            if (node.room != null) continue

            return node
        }

        return null
    }

    private fun overlaps(node: DungeonNode, size: Int): Boolean {
        for (tunnel in tunnels) {
            if (connects(node, tunnel)) continue

            if (node.getBounds(size).collidesWith(tunnel)) {
                return true
            }
        }

        for (n in nodes) {
            if (node == n) continue

            if (node.getBounds(size).collidesWith(n)) return true
        }

        return false
    }

    private fun connects(node: DungeonNode, tunnel: DungeonTunnel): Boolean {
        val pos = node.pos

        return tunnel.start == pos || tunnel.end == pos
    }

    fun hasEmptyRooms(): Boolean {
        for (node in nodes) {
            if (node == start || node == end) continue

            if (node.room == null) return true
        }

        return false
    }

    fun getCollisions(): List<ICollidable> {
        val boxes = arrayListOf<ICollidable>()
        boxes.addAll(nodes)
        boxes.addAll(tunnels)
        return boxes
    }

    fun addNode(dungeonNode: DungeonNode) {
        nodes.add(dungeonNode)
    }

    fun addTunnels(toAdd: List<DungeonTunnel>) {
        tunnels.addAll(toAdd)
    }
}