package thedarkcolour.roguelikedungeons.loot.item

import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import thedarkcolour.roguelikedungeons.loot.weight.WeightedLoot
import thedarkcolour.roguelikedungeons.loot.weight.WeightedPool
import java.util.*

class OreItemLoot(val level: Int) : IItemLoot {
    private val loot: WeightedPool<ItemStack>

    init {
        val randomizer = WeightedPool<ItemStack>()

        when (level) {
            4 -> {
                randomizer.add(WeightedLoot(1, Items.DIAMOND, 0, 1, 1))
                randomizer.add(WeightedLoot(2, Items.EMERALD, 0, 1, 1))
                randomizer.add(WeightedLoot(3, Items.GOLD_INGOT, 0, 2, 5))
                randomizer.add(WeightedLoot(2, Items.GOLD_NUGGET, 0, 2, 8))
                randomizer.add(WeightedLoot(5, Items.IRON_INGOT, 0, 2, 5))
            }
            3 -> {
                randomizer.add(WeightedLoot(1, Items.DIAMOND, 0, 1, 1))
                randomizer.add(WeightedLoot(2, Items.EMERALD, 0, 1, 1))
                randomizer.add(WeightedLoot(3, Items.GOLD_INGOT, 0, 1, 5))
                randomizer.add(WeightedLoot(5, Items.GOLD_NUGGET, 0, 2, 6))
                randomizer.add(WeightedLoot(10, Items.IRON_INGOT, 0, 1, 4))
                randomizer.add(WeightedLoot(3, Items.COAL, 0, 4, 15))
            }
            2 -> {
                randomizer.add(WeightedLoot(1, Items.DIAMOND, 0, 1, 1))
                randomizer.add(WeightedLoot(3, Items.GOLD_INGOT, 0, 1, 4))
                randomizer.add(WeightedLoot(5, Items.GOLD_NUGGET, 0, 1, 5))
                randomizer.add(WeightedLoot(10, Items.IRON_INGOT, 0, 1, 3))
                randomizer.add(WeightedLoot(10, Items.COAL, 0, 3, 7))
            }
            1 -> {
                randomizer.add(WeightedLoot(1, Items.DIAMOND, 0, 1, 1))
                randomizer.add(WeightedLoot(5, Items.GOLD_INGOT, 0, 1, 3))
                randomizer.add(WeightedLoot(10, Items.GOLD_NUGGET, 0, 1, 4))
                randomizer.add(WeightedLoot(5, Items.IRON_INGOT, 0, 1, 2))
                randomizer.add(WeightedLoot(20, Items.IRON_NUGGET, 0, 1, 5))
                randomizer.add(WeightedLoot(20, Items.COAL, 0, 2, 5))
                randomizer.add(WeightedLoot(10, Items.LEATHER, 0, 3, 9))
            }
            0 -> {
                randomizer.add(WeightedLoot(1, Items.DIAMOND, 0, 1, 1))
                randomizer.add(WeightedLoot(3, Items.GOLD_INGOT, 0, 1, 1))
                randomizer.add(WeightedLoot(15, Items.GOLD_NUGGET, 0, 1, 2))
                randomizer.add(WeightedLoot(10, Items.IRON_INGOT, 0, 1, 1))
                randomizer.add(WeightedLoot(30, Items.IRON_NUGGET, 0, 1, 5))
                randomizer.add(WeightedLoot(40, Items.COAL, 0, 1, 3))
                randomizer.add(WeightedLoot(15, Items.LEATHER, 0, 1, 7))
            }
            else -> randomizer.add(WeightedLoot(1, Items.COAL))
        }
        loot = randomizer
    }

    override fun get(rand: Random): ItemStack {
        return loot.get(rand)
    }
}