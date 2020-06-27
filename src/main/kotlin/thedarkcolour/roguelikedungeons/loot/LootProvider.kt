package thedarkcolour.roguelikedungeons.loot

import net.minecraft.item.ItemStack
import thedarkcolour.roguelikedungeons.loot.weight.IWeighted
import thedarkcolour.roguelikedungeons.util.checkRange

class LootProvider {
    private val loot = Array(5, ::LootSettings)

    fun get(type: LootType, level: Int): IWeighted<ItemStack> {
        checkRange(level, 0..4)

        return loot[level].get(type)!!
    }
}