package thedarkcolour.roguelikedungeons.loot.item

import net.minecraft.item.ItemStack
import thedarkcolour.roguelikedungeons.loot.weight.IWeighted

fun interface IItemLoot : IWeighted<ItemStack> {
    override val weight: Int
        get() = 0
}