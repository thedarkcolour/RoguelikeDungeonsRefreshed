package thedarkcolour.roguelikedungeons.loot.weight

import thedarkcolour.roguelikedungeons.Complete
import java.util.*

@Complete
interface IWeighted<T> {
    val weight: Int

    fun get(rand: Random): T
}