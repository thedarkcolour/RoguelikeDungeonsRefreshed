package thedarkcolour.roguelikedungeons.dungeon

import net.minecraft.util.Direction
import thedarkcolour.roguelikedungeons.util.checkFailed
import thedarkcolour.roguelikedungeons.util.checkHorizontal

val HORIZONTALS = arrayOf(Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST)

// adjacent directions (left, right)
private val NORTH_ADJ = arrayOf(Direction.WEST, Direction.EAST)
private val SOUTH_ADJ = arrayOf(Direction.EAST, Direction.WEST)
private val EAST_ADJ = arrayOf(Direction.NORTH, Direction.SOUTH)
private val WEST_ADJ = arrayOf(Direction.SOUTH, Direction.NORTH)

fun leftDirection(direction: Direction): Direction {
    checkHorizontal(direction)

    return when (direction) {
        Direction.NORTH -> Direction.WEST
        Direction.EAST -> Direction.NORTH
        Direction.SOUTH -> Direction.EAST
        Direction.WEST -> Direction.SOUTH
        else -> checkFailed()
    }
}

fun rightDirection(direction: Direction): Direction {
    checkHorizontal(direction)

    return when (direction) {
        Direction.NORTH -> Direction.EAST
        Direction.SOUTH -> Direction.WEST
        Direction.EAST -> Direction.SOUTH
        Direction.WEST -> Direction.NORTH
        else -> checkFailed()
    }
}

fun adjacentDirections(direction: Direction): Array<Direction> {
    checkHorizontal(direction)

    return when (direction) {
        Direction.NORTH -> NORTH_ADJ
        Direction.SOUTH -> SOUTH_ADJ
        Direction.EAST -> EAST_ADJ
        Direction.WEST -> WEST_ADJ
        else -> checkFailed()
    }
}

fun vertical(up: Boolean): Direction {
    return if (up) Direction.UP else Direction.DOWN
}