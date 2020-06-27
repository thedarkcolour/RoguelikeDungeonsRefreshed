package thedarkcolour.roguelikedungeons.enemy.potion

import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.CompoundNBT
import net.minecraft.potion.*
import thedarkcolour.roguelikedungeons.loot.setItemName
import thedarkcolour.roguelikedungeons.util.nextEnum
import java.util.*

//
// Potions
//

fun getRandomPotion(rand: Random): ItemStack {
    return getPotion(rand, rand.nextEnum())
}

fun getPotion(
    rand: Random,
    effect: PotionType,
    type: BottleType = BottleType.NORMAL
): ItemStack {
    return getPotion(effect, type, rand.nextBoolean(), rand.nextBoolean())
}

fun getPotion(effect: PotionType, type: BottleType, strong: Boolean, long: Boolean): ItemStack {
    val potion = when (type) {
        BottleType.SPLASH -> ItemStack(Items.SPLASH_POTION)
        BottleType.LINGERING -> ItemStack(Items.LINGERING_POTION)
        else -> ItemStack(Items.POTION)
    }

    return PotionUtils.addPotionToItemStack(potion, getPotion(effect, strong, long))
}

/**
 * Picks a random variant of the [effect] and returns its vanilla counterpart.
 *
 * @param effect The type of effect
 * @param strong Whether the effect is strong
 * @param long Whether the effect is long
 *
 * @return The corresponding vanilla variant of [effect]
 */
fun getPotion(effect: PotionType, strong: Boolean, long: Boolean): net.minecraft.potion.Potion {
    return when (effect) {
        PotionType.HEALING -> if (strong) Potions.STRONG_HEALING else Potions.HEALING
        PotionType.HARMING -> if (strong) Potions.STRONG_HARMING else Potions.HARMING
        PotionType.REGEN -> if (long) Potions.LONG_REGENERATION else if (strong) Potions.STRONG_REGENERATION else Potions.REGENERATION
        PotionType.POISON -> if (long) Potions.LONG_POISON else if (strong) Potions.STRONG_POISON else Potions.POISON
        PotionType.STRENGTH -> if (long) Potions.LONG_STRENGTH else if (strong) Potions.STRONG_STRENGTH else Potions.STRENGTH
        PotionType.WEAKNESS -> if (long) Potions.LONG_WEAKNESS else Potions.WEAKNESS
        PotionType.SWIFTNESS -> if (long) Potions.LONG_SWIFTNESS else if (strong) Potions.STRONG_SWIFTNESS else Potions.SWIFTNESS
        PotionType.FIRE_RESISTANCE -> if (long) Potions.LONG_FIRE_RESISTANCE else Potions.FIRE_RESISTANCE
        else -> Potions.AWKWARD
    }
}

fun addPotionEffect(item: ItemStack, effect: Effect, amp: Int, duration: Int) {
    addPotionEffect(item, EffectInstance(effect, duration, amp))
}

fun addPotionEffect(item: ItemStack, effect: EffectInstance) {
    val tag = item.orCreateTag
    val list = tag.getList("CustomPotionEffects", 9)

    list.add(effect.write(CompoundNBT()))
    tag.put("CustomPotionEffects", list)
}

//
// Tipped Arrows
//

fun getTippedArrow(effect: PotionType, count: Int = 1): ItemStack {
    return getTippedArrow(
        getPotion(effect, strong = false, long = false),
        count
    )
}

/**
 * Creates a stack of tipped arrows with a certain potion effect.
 *
 * @param effect The `Effect` of the arrow
 * @param count The number of arrows to give
 *
 * @return A new `ItemStack` of [count] tipped arrows with the specified [effect]
 */
fun getTippedArrow(effect: net.minecraft.potion.Potion, count: Int = 1): ItemStack {
    return PotionUtils.addPotionToItemStack(ItemStack(Items.TIPPED_ARROW, count), effect)
}

/**
 * Creates a tipped arrow with a randomly chosen harmful potion effect.
 *
 * @param rand The RNG that picks the effect
 * @param count The number of arrows in the returned stack
 *
 * @return A new `ItemStack` of [count] tipped arrows with a harmful potion effect.
 */
fun getHarmfulArrow(rand: Random, count: Int = 1): ItemStack {
    return when (rand.nextInt(4)) {
        0 -> getTippedArrow(PotionType.HARMING, count)
        1 -> getTippedArrow(PotionType.POISON, count)
        2 -> getTippedArrow(PotionType.SLOWNESS, count)
        3 -> getTippedArrow(PotionType.WEAKNESS, count)
        else -> ItemStack(Items.ARROW)
    }
}

//
// Potion Presets
//

fun getPotionPreset(preset: PotionPreset, rand: Random): ItemStack {
    val potion = ItemStack(Items.POTION)

    when (preset) {
        PotionPreset.TEQUILA -> {
            addPotionEffect(potion, Effects.STRENGTH, 3, 30 + rand.nextInt(60))
            addPotionEffect(potion, Effects.MINING_FATIGUE, 1, 30 + rand.nextInt(60))
            setItemName(potion, "Tequila")
        }
    }

    return potion
}