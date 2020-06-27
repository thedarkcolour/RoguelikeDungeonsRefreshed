package thedarkcolour.roguelikedungeons.dungeon

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*
import kotlin.math.abs
import kotlin.math.sqrt

typealias MutableBlockPos = BlockPos.Mutable

fun BlockPos.distanceHorizontal(other: BlockPos): Double {
    val a = abs(x - other.x).toDouble()
    val b = abs(z - other.z).toDouble()

    return sqrt(a * a + b * b)
}

fun BlockPos.toMutablePos(): BlockPos.Mutable {
    // toMutable
    return MutableBlockPos(x, y, z)
}

fun BlockPos.directionTo(other: BlockPos): Direction {
    val diffX = other.x - x
    val diffY = other.y - y
    val diffZ = other.z - z

    return if (abs(diffY) > abs(diffX) && abs(diffY) > abs(diffZ)) {
        Direction.UP
    } else if (abs(diffX) < abs(diffZ)) {
        if (diffZ < 0) {
            Direction.NORTH
        } else {
            Direction.SOUTH
        }
    } else {
        if (diffX < 0) {
            Direction.WEST
        } else {
            Direction.EAST
        }
    }
}

/**
 * Arranges two `BlockPos` so that they create a positive cube.
 * Used in fill functions.
 */
fun correctBounds(start: BlockPos, end: BlockPos): Pair<BlockPos, BlockPos> {
    var temp: Int
    val a = start.toMutablePos()
    val b = end.toMutablePos()

    if (b.x < a.x) {
        temp = b.x
        b.x = a.x
        a.x = temp
    }

    if (b.y < a.y) {
        temp = b.y
        b.y = a.y
        a.y = temp
    }

    if (b.z < a.z) {
        temp = b.z
        b.z = a.z
        a.z = temp
    }

    return a.toImmutable() to b.toImmutable()
}

fun getDungeonRng(world: DungeonWorldIn, pos: BlockPos): Random {
    return Random(Objects.hash(world.seed, pos).toLong())
}

fun getDungeonLevel(pos: BlockPos): Int {
    val y = pos.y

    return when {
        y < 15 -> 4
        y < 25 -> 3
        y < 35 -> 2
        y < 45 -> 1
        else -> 0
    }
}