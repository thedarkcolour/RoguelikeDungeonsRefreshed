package thedarkcolour.roguelikedungeons.loot.item

import net.minecraft.item.ItemStack
import thedarkcolour.roguelikedungeons.loot.Quality
import thedarkcolour.roguelikedungeons.loot.getRandomItem
import java.util.*

class SpecialItemLoot(level: Int) : IItemLoot {
    private val quality = Quality.values()[level]

    override fun get(rand: Random): ItemStack {
        return getRandomItem(rand, quality)
    }
}