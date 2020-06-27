package thedarkcolour.roguelikedungeons.loot.weight

import thedarkcolour.roguelikedungeons.Complete
import java.util.*

@Complete
data class WeightedEntry<T>(val item: T, override val weight: Int) : IWeighted<T> {
    override fun get(rand: Random): T {
        return item
    }
}