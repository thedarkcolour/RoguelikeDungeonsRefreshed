package thedarkcolour.roguelikedungeons.loot

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.item.EnchantedBookItem
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.world.Difficulty
import thedarkcolour.roguelikedungeons.util.chance
import java.util.*

fun canEnchant(difficulty: Difficulty?, rand: Random, level: Int): Boolean {
    return when (difficulty) {
        Difficulty.EASY -> rand.nextInt(6) == 0
        Difficulty.HARD -> rand.nextBoolean()
        null, Difficulty.NORMAL -> level >= 1 && rand.nextInt(4) == 0
        else -> false
    }
}

fun enchantItem(rand: Random, item: ItemStack, level: Int): ItemStack {
    var stack = item
    val enchantments = EnchantmentHelper.buildEnchantmentList(rand, stack, level, false)
    val isBook = stack.item == Items.BOOK

    if (isBook) {
        stack = ItemStack(Items.ENCHANTED_BOOK)

        if (enchantments.size > 1) {
            enchantments.removeAt(rand.nextInt(enchantments.size))
        }
    }

    for (enchantment in enchantments) {
        if (isBook) {
            EnchantedBookItem.addEnchantment(stack, enchantment)
        } else {
            stack.addEnchantment(enchantment.enchantment, enchantment.enchantmentLevel)
        }
    }

    return stack
}

fun getEnchantmentLevel(rand: Random, level: Int): Int {
    return when (level) {
        4 -> 30 + rand.nextInt(10)
        3 -> 30 + rand.nextInt(15)
        2 -> 30 + rand.nextInt(15)
        1 -> 30 + rand.nextInt(10)
        0 -> 30 + rand.nextInt(5)
        else -> 1
    }
}

fun getUnbreaking(rand: Random, quality: Quality): Int {
    return if (quality == Quality.DIAMOND) 3 else 1 + rand.nextInt(2)
}

fun getProtection(rand: Random, quality: Quality): Int {
    var value = 1

    when (quality) {
        Quality.WOOD -> if (rand.chance(3)) ++value
        Quality.STONE -> if (rand.nextBoolean()) ++value
        Quality.IRON, Quality.GOLD -> value += rand.nextInt(3)
        Quality.DIAMOND -> value += 2 + rand.nextInt(2)
    }

    return value
}

//
// addEnchantments overloads
//

fun addEnchantments(stack: ItemStack, enchantment1: Enchantment, level1: Int) {
    stack.addEnchantment(enchantment1, level1)
}

fun addEnchantments(stack: ItemStack, enchantment1: Enchantment, level1: Int, enchantment2: Enchantment, level2: Int) {
    stack.addEnchantment(enchantment1, level1)
    stack.addEnchantment(enchantment2, level2)
}

fun addEnchantments(stack: ItemStack, enchantment1: Enchantment, level1: Int, enchantment2: Enchantment, level2: Int, enchantment3: Enchantment, level3: Int) {
    stack.addEnchantment(enchantment1, level1)
    stack.addEnchantment(enchantment2, level2)
    stack.addEnchantment(enchantment3, level3)
}

fun addEnchantments(stack: ItemStack, enchantment1: Enchantment, level1: Int, enchantment2: Enchantment, level2: Int, enchantment3: Enchantment, level3: Int, enchantment4: Enchantment, level4: Int) {
    stack.addEnchantment(enchantment1, level1)
    stack.addEnchantment(enchantment2, level2)
    stack.addEnchantment(enchantment3, level3)
    stack.addEnchantment(enchantment4, level4)
}

fun addEnchantments(stack: ItemStack, enchantment1: Enchantment, level1: Int, enchantment2: Enchantment, level2: Int, enchantment3: Enchantment, level3: Int, enchantment4: Enchantment, level4: Int, enchantment5: Enchantment, level5: Int) {
    stack.addEnchantment(enchantment1, level1)
    stack.addEnchantment(enchantment2, level2)
    stack.addEnchantment(enchantment3, level3)
    stack.addEnchantment(enchantment4, level4)
    stack.addEnchantment(enchantment5, level5)
}

fun addEnchantments(stack: ItemStack, enchantment1: Enchantment, level1: Int, enchantment2: Enchantment, level2: Int, enchantment3: Enchantment, level3: Int, enchantment4: Enchantment, level4: Int, enchantment5: Enchantment, level5: Int, enchantment6: Enchantment, level6: Int) {
    stack.addEnchantment(enchantment1, level1)
    stack.addEnchantment(enchantment2, level2)
    stack.addEnchantment(enchantment3, level3)
    stack.addEnchantment(enchantment4, level4)
    stack.addEnchantment(enchantment5, level5)
    stack.addEnchantment(enchantment6, level6)
}

fun addEnchantments(stack: ItemStack, enchantment1: Enchantment, level1: Int, enchantment2: Enchantment, level2: Int, enchantment3: Enchantment, level3: Int, enchantment4: Enchantment, level4: Int, enchantment5: Enchantment, level5: Int, enchantment6: Enchantment, level6: Int, enchantment7: Enchantment, level7: Int) {
    stack.addEnchantment(enchantment1, level1)
    stack.addEnchantment(enchantment2, level2)
    stack.addEnchantment(enchantment3, level3)
    stack.addEnchantment(enchantment4, level4)
    stack.addEnchantment(enchantment5, level5)
    stack.addEnchantment(enchantment6, level6)
    stack.addEnchantment(enchantment7, level7)
}

fun addEnchantments(stack: ItemStack, enchantment1: Enchantment, level1: Int, enchantment2: Enchantment, level2: Int, enchantment3: Enchantment, level3: Int, enchantment4: Enchantment, level4: Int, enchantment5: Enchantment, level5: Int, enchantment6: Enchantment, level6: Int, enchantment7: Enchantment, level7: Int, enchantment8: Enchantment, level8: Int, enchantment9: Enchantment, level9: Int) {
    stack.addEnchantment(enchantment1, level1)
    stack.addEnchantment(enchantment2, level2)
    stack.addEnchantment(enchantment3, level3)
    stack.addEnchantment(enchantment4, level4)
    stack.addEnchantment(enchantment5, level5)
    stack.addEnchantment(enchantment6, level6)
    stack.addEnchantment(enchantment7, level7)
    stack.addEnchantment(enchantment8, level8)
    stack.addEnchantment(enchantment9, level9)
}