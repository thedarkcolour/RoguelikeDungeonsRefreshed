package thedarkcolour.roguelikedungeons.util

import it.unimi.dsi.fastutil.ints.IntOpenHashSet
import it.unimi.dsi.fastutil.ints.IntSet
import java.util.*

fun intSetOf(vararg ints: Int): IntSet {
    val set = IntOpenHashSet()

    for (i in ints) {
        set.add(i)
    }

    return set
}

fun rgb(r: Int, g: Int, b: Int): Int {
    return ((r and 255) shl 16) or ((g and 255) shl 8) or ((b and 255))
}

//
// RNG
//

fun <T> Random.pickRandomly(array: Array<T>): T {
    return array[nextInt(array.size)]
}

fun <T> Random.pickRandomly(array: List<T>): T {
    return array[nextInt(array.size)]
}

fun Random.chance(chance: Int): Boolean {
    return nextInt(chance) == 0
}

inline fun <reified T : Enum<T>> Random.nextEnum(): T {
    return pickRandomly(T::class.java.enumConstants)
}

fun Random.nextRGB(): Int {
    return rgb(nextInt(256), nextInt(256), nextInt(256))
}