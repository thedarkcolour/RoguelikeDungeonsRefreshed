package thedarkcolour.roguelikedungeons.loot.item

import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import thedarkcolour.roguelikedungeons.loot.enchantItem
import thedarkcolour.roguelikedungeons.loot.getEnchantmentLevel
import java.util.*

class EnchantedBookItemLoot(val level: Int) : IItemLoot {
    override fun get(rand: Random): ItemStack {
        val book = ItemStack(Items.BOOK)
        val l = if (level != 0) level else getEnchantmentLevel(rand, level)
        return enchantItem(rand, book, l)
    }
}