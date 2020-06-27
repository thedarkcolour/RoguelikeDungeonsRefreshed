package thedarkcolour.roguelikedungeons.dungeon.level.mst

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.dungeon.distanceHorizontal
import thedarkcolour.roguelikedungeons.dungeon.shape.ShapeType
import thedarkcolour.roguelikedungeons.dungeon.theme.placer.IBlockPlacer
import thedarkcolour.roguelikedungeons.dungeon.toMutablePos
import java.util.*
import kotlin.collections.ArrayList

class MinimumSpanningTree(rand: Random, size: Int, edgeLength: Int, pos: BlockPos = BlockPos.ZERO) {
    val points = ArrayList<MSTPoint>()
    val edges = ArrayList<Edge<MSTPoint>>()

    init {
        val offset = size / 2 * edgeLength

        for (i in 0 until size) {
            val temp = pos.toMutablePos()
            temp.move(Direction.NORTH, offset)
            temp.move(Direction.WEST, offset)
            temp.move(Direction.SOUTH, edgeLength * i)

            for (j in 0 until size) {
                points.add(MSTPoint(temp.toImmutable(), rand))
                temp.move(Direction.EAST, edgeLength)
            }
        }

        val edges = ArrayList<Edge<MSTPoint>>()
        for (p in points) {
            for (o in points) {
                if (p == o) continue
                edges.add(Edge(p, o, p.distance(o)))
            }
        }

        edges.sort()

        for (e in edges) {
            val start = e.start
            val end = e.end

            if (find(start) == find(end)) continue
            union(start, end)
            edges.add(e)
        }
    }

    private fun union(a: MSTPoint, b: MSTPoint) {
        val root1 = find(a)
        val root2 = find(b)
        if (root1 == root2) return

        if (root1.rank > root2.rank) {
            root2.parent = root1
        } else {
            root1.parent = root2
            if (root1.rank == root2.rank) {
                ++root2.rank
            }
        }
    }

    private fun find(p: MSTPoint): MSTPoint {
        if (p.parent == p) return p
        p.parent = find(p.parent)
        return p.parent
    }

    fun generate(worldIn: IWorld, rand: Random, block: IBlockPlacer, pos: BlockPos) {
        for (e in edges) {
            val start = e.start.pos.toMutablePos()
            start.move(pos.x, pos.y, pos.z)
            val end = e.end.pos.toMutablePos()
            end.move(pos.x, pos.y, pos.z)

            ShapeType.HOLLOW_RECTANGLE.fill(worldIn, rand, start, end, block)
        }
    }

    fun getGraph(): Graph<BlockPos> {
        val layout = Graph<BlockPos>()
        for (e in edges) {
            val start = e.start.pos
            val end = e.end.pos
            layout.addEdge(Edge(start, end, start.distanceHorizontal(end)))
        }
        return layout
    }
}