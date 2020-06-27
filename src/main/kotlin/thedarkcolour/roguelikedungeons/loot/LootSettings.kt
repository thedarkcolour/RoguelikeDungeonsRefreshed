package thedarkcolour.roguelikedungeons.loot

import net.minecraft.item.ItemStack
import thedarkcolour.kotlinforforge.kotlin.enumMapOf
import thedarkcolour.roguelikedungeons.loot.weight.IWeighted
import java.util.*

class LootSettings(level: Int) {
    val loot = enumMapOf<LootType, IWeighted<ItemStack>>()

    init {
        for (type in LootType.values()) {
            loot[type] = getLootProvider(type, level)
        }
    }

    fun get(type: LootType, rand: Random): ItemStack {
        val provider = loot[type]
        return provider!!.get(rand)
    }

    fun get(type: LootType): IWeighted<ItemStack>? {
        return loot[type]
    }
}