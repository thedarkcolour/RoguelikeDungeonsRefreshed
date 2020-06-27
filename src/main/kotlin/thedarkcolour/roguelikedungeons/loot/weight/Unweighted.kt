package thedarkcolour.roguelikedungeons.loot.weight

import thedarkcolour.roguelikedungeons.Complete
import java.util.*

@Complete
class Unweighted<T>(private val value: T) : IWeighted<T> {
    override fun get(rand: Random) = value
    override val weight = 0
}