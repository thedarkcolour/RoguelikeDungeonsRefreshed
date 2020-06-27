package thedarkcolour.roguelikedungeons.loot.weight

import thedarkcolour.roguelikedungeons.Complete
import java.util.*

@Complete
data class WeightedPool<T>(override val weight: Int = 0, private var weightSum: Int = 0) : IWeighted<T> {
    private val items = arrayListOf<IWeighted<T>>()

    override fun get(rand: Random): T {
        if (weightSum == 0 || items.isEmpty()) {
            throw IllegalStateException("Weighted loot pool is empty")
        }

        var roll = rand.nextInt(weightSum)

        for (item in items) {
            roll -= item.weight

            if (roll < 0) {
                return item.get(rand)
            }
        }

        throw IllegalStateException("Failed to generate a pooled result")
    }

    fun isEmpty(): Boolean {
        return items.isEmpty()
    }

    fun add(item: IWeighted<T>) {
        weightSum += item.weight
        items.add(item)
    }

    fun add(item: T, weight: Int) {
        add(WeightedEntry(item, weight))
    }

    fun addAll(iterable: Iterable<IWeighted<T>>) {
        for (item in iterable) {
            add(item)
        }
    }

    fun addAll(array: Array<out IWeighted<T>>) {
        for (item in array) {
            add(item)
        }
    }

    fun addAll(other: WeightedPool<T>) {
        for (item in other.items) {
            add(item)
        }
    }

    companion object {
        fun <T> combine(a: WeightedPool<T>, b: WeightedPool<T>): WeightedPool<T> {
            val result = WeightedPool<T>(0, 0)

            result.addAll(a)
            result.addAll(b)

            return result
        }

        fun <T> clone(other: WeightedPool<T>): WeightedPool<T> {
            val result =
                WeightedPool<T>(other.weight)
            result.addAll(other)
            result.weightSum = other.weightSum

            return result
        }
    }
}