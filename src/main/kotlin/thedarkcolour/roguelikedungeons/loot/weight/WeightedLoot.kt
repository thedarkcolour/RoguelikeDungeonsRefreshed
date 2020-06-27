package thedarkcolour.roguelikedungeons.loot.weight

import net.minecraft.item.ItemStack
import net.minecraft.util.IItemProvider
import thedarkcolour.roguelikedungeons.loot.enchantItem
import thedarkcolour.roguelikedungeons.util.checkRange
import java.util.*

class WeightedLoot(
    override val weight: Int,
    private val item: IItemProvider,
    private val min: Int = 1,
    private val max: Int = 1,
    private val enchantLevel: Int = 0,
) : Comparable<WeightedLoot>, IWeighted<ItemStack> {

    init {
        checkRange(enchantLevel, 0..30)
    }

    override fun get(rand: Random): ItemStack {
        val item = ItemStack(item, getStackSize(rand))

        if (enchantLevel != 0) {
            enchantItem(rand, item, enchantLevel)
        }

        return item
    }

    private fun getStackSize(rand: Random): Int {
        return if (max == 1) {
            1
        } else rand.nextInt(max - min) + min
    }

    /**
     * Compares this object with the specified object for order. Returns zero if this object is equal
     * to the specified [other] object, a negative number if it's less than [other], or a positive number
     * if it's greater than [other].
     */
    override fun compareTo(other: WeightedLoot): Int {
        return other.weight.compareTo(weight)
    }
}