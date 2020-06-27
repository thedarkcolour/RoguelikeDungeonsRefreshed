package thedarkcolour.roguelikedungeons.loot

import net.minecraft.enchantment.Enchantments.*
import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.BannerItem
import net.minecraft.item.DyeColor
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.CompoundNBT
import net.minecraft.nbt.ListNBT
import net.minecraft.nbt.StringNBT
import net.minecraft.tileentity.BannerPattern
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.StringTextComponent
import net.minecraft.util.text.TextFormatting
import thedarkcolour.roguelikedungeons.util.*
import java.util.*

/**
 * Generates a random piece of gear or a random weapon.
 *
 * @param rand The RNG for the loot generation
 * @param slot The slot the loot can be equipped to
 * @param level The level of the item (higher level, higher quality)
 * @param enchanted Whether the loot should be enchanted
 *
 * @return A random weapon or a random picks of armor
 */
fun getEquipment(rand: Random, slot: EquipmentSlotType, level: Int, enchanted: Boolean): ItemStack {
    return if (slot == EquipmentSlotType.MAINHAND) {
        getRandomWeapon(rand, level, enchanted)
    } else getRandomArmor(rand, level, slot, enchanted)
}

fun getRandomItem(rand: Random, quality: Quality): ItemStack {
    return when (rand.nextInt(10)) {
        0 -> getSpecialSword(rand, quality)
        1 -> getSpecialBow(rand, quality)
        2 -> getSpecialHelmet(rand, quality)
        3 -> getSpecialChestplate(rand, quality)
        4 -> getSpecialLeggings(rand, quality)
        5 -> getSpecialBoots(rand, quality)
        6 -> getSpecialPickaxe(rand, quality)
        7 -> getSpecialAxe(rand, quality)
        8 -> getSpecialShovel(rand, quality)
        9 -> getSpecialHoe(rand, quality)
        else -> throw IllegalStateException("Invalid RNG")
    }
}

//
// Armor & Shields
//

/** Head, Chest, Legs, Feet */
val ARMOR_SLOTS = arrayOf(EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET)

/**
 * Generates a random piece of dyed leather armor.
 */
fun getDyedEquipment(
        rand: Random,
        slot: EquipmentSlotType,
        color: Int = rand.nextRGB()
): ItemStack {
    checkArmorSlot(slot)

    val stack = ItemStack(when (slot) {
        EquipmentSlotType.HEAD -> Items.LEATHER_HELMET
        EquipmentSlotType.CHEST -> Items.LEATHER_CHESTPLATE
        EquipmentSlotType.LEGS -> Items.LEATHER_LEGGINGS
        EquipmentSlotType.FEET -> Items.LEATHER_BOOTS
        else -> checkFailed()
    })

    dyeArmor(stack, color)

    return stack
}

fun dyeArmor(item: ItemStack, color: Int): ItemStack {
    val display = item.getOrCreateChildTag("display")
    display.putInt("color", color)

    return item
}

fun getRandomArmor(rand: Random, level: Int, slot: EquipmentSlotType, enchanted: Boolean): ItemStack {
    return getRandomArmor(rand, level, slot, if (enchanted) getEnchantmentLevel(rand, level) else 0)
}

fun getRandomArmor(rand: Random, level: Int, slot: EquipmentSlotType, enchantmentLevel: Int): ItemStack {
    checkArmorSlot(slot)

    if (enchantmentLevel > 0 && rand.chance(20 + (level * 10))) {
        val quality = Quality.getArmorQuality(rand, level)

        return when (slot) {
            EquipmentSlotType.HEAD -> getSpecialHelmet(rand, quality)
            EquipmentSlotType.CHEST -> getSpecialChestplate(rand, quality)
            EquipmentSlotType.LEGS -> getSpecialLeggings(rand, quality)
            EquipmentSlotType.FEET -> getSpecialBoots(rand, quality)
            else -> checkFailed()
        }
    }
    val item = getRandomArmor(rand, slot, Quality.getArmorQuality(rand, level))

    if (enchantmentLevel > 0) enchantItem(rand, item, enchantmentLevel)

    return item
}

fun getSpecialHelmet(rand: Random, quality: Quality): ItemStack {
    val item: ItemStack

    var canonical = ""

    when (quality) {
        Quality.WOOD -> {
            item = ItemStack(Items.LEATHER_HELMET)
            dyeArmor(item, rand.nextRGB())
            canonical = "Bonnet"
        }
        Quality.STONE -> {
            item = ItemStack(Items.CHAINMAIL_HELMET)
            canonical = "Coif"
        }
        Quality.IRON, Quality.GOLD -> {
            item = ItemStack(Items.IRON_HELMET)
            canonical = "Sallet"
        }
        Quality.DIAMOND -> {
            item = ItemStack(Items.DIAMOND_HELMET)
            canonical = "Helm"
        }
    }


    val suffix: String

    suffix = when {
        rand.nextInt(20) == 0 -> {
            addEnchantments(item, PROTECTION, getProtection(rand, quality), RESPIRATION, 3, AQUA_AFFINITY, 1)
            "of Diving"
        }
        rand.nextInt(3) == 0 -> {
            addEnchantments(item, PROJECTILE_PROTECTION, getProtection(rand, quality))
            "of Deflection"
        }
        else -> {
            addEnchantments(item, PROTECTION, getProtection(rand, quality))
            "of Defense"
        }
    }

    addEnchantments(item, UNBREAKING, getUnbreaking(rand, quality))

    if (rand.nextInt(10) == 0) {
        item.addEnchantment(MENDING, 1)
    }

    val name = getSpecialArmorPrefix(quality) + " " + canonical + " " + suffix
    setItemName(item, name)

    return item
}

fun getSpecialChestplate(rand: Random, quality: Quality): ItemStack {
    val item: ItemStack
    var canonical: String

    when (quality) {
        Quality.WOOD -> {
            item = ItemStack(Items.LEATHER_CHESTPLATE)
            dyeArmor(item, rand.nextRGB())
            canonical = "Tunic"
        }
        Quality.STONE -> {
            item = ItemStack(Items.CHAINMAIL_CHESTPLATE)
            canonical = "Hauberk"
        }
        Quality.IRON, Quality.GOLD -> {
            item = ItemStack(Items.IRON_CHESTPLATE)
            canonical = "Cuirass"
        }
        Quality.DIAMOND -> {
            item = ItemStack(Items.DIAMOND_CHESTPLATE)
            canonical = "Plate"
        }
        else -> {
            item = ItemStack(Items.LEATHER_CHESTPLATE)
            canonical = "Tunic"
        }
    }

    val suffix: String

    suffix = if (rand.nextInt(10) == 0) {
        item.addEnchantment(FIRE_PROTECTION, getProtection(rand, quality))
        "of Flamewarding"
    } else if (rand.nextInt(10) == 0) {
        item.addEnchantment(BLAST_PROTECTION, getProtection(rand, quality))
        "of Integrity"
    } else if (rand.nextInt(3) == 0) {
        item.addEnchantment(PROJECTILE_PROTECTION, getProtection(rand, quality))
        "of Deflection"
    } else {
        item.addEnchantment(PROTECTION, getProtection(rand, quality))
        "of Defense"
    }

    item.addEnchantment(UNBREAKING, getUnbreaking(rand, quality))

    if (rand.nextInt(10) == 0) {
        item.addEnchantment(MENDING, 1)
    }

    val name: String = getSpecialArmorPrefix(quality) + " " + canonical + " " + suffix
    setItemName(item, name)
    return item
}

fun getSpecialLeggings(rand: Random, quality: Quality): ItemStack {
    val item: ItemStack

    var canonical = ""

    when (quality) {
        Quality.WOOD -> {
            item = ItemStack(Items.LEATHER_LEGGINGS)
            dyeArmor(item, rand.nextRGB())
            canonical = "Pantaloons"
        }
        Quality.STONE -> {
            item = ItemStack(Items.CHAINMAIL_LEGGINGS)
            canonical = "Chausses"
        }
        Quality.IRON, Quality.GOLD -> {
            item = ItemStack(Items.IRON_LEGGINGS)
            canonical = "Leg-plates"
        }
        Quality.DIAMOND -> {
            item = ItemStack(Items.DIAMOND_LEGGINGS)
            canonical = "Leggings"
        }
    }

    var suffix: String

    suffix = when {
        rand.nextInt(10) == 0 -> {
            item.addEnchantment(FIRE_PROTECTION, getProtection(rand, quality))
            "of Warding"
        }
        rand.nextInt(10) == 0 -> {
            item.addEnchantment(BLAST_PROTECTION, getProtection(rand, quality))
            "of Integrity"
        }
        rand.nextInt(3) == 0 -> {
            item.addEnchantment(PROJECTILE_PROTECTION, getProtection(rand, quality))
            "of Deflection"
        }
        else -> {
            item.addEnchantment(PROTECTION, getProtection(rand, quality))
            "of Defense"
        }
    }

    item.addEnchantment(UNBREAKING, getUnbreaking(rand, quality))

    if (rand.nextInt(10) == 0) {
        item.addEnchantment(MENDING, 1)
    }

    val name: String = getSpecialArmorPrefix(quality) + " " + canonical + " " + suffix
    setItemName(item, name)
    return item
}

fun getSpecialBoots(rand: Random, quality: Quality): ItemStack {
    val item: ItemStack

    var canonical = ""

    when (quality) {
        Quality.WOOD -> {
            item = ItemStack(Items.LEATHER_BOOTS)
            dyeArmor(item, rand.nextRGB())
            canonical = "Shoes"
        }
        Quality.STONE -> {
            item = ItemStack(Items.CHAINMAIL_BOOTS)
            canonical = "Greaves"
        }
        Quality.IRON, Quality.GOLD -> {
            item = ItemStack(Items.IRON_BOOTS)
            canonical = "Sabatons"
        }
        Quality.DIAMOND -> {
            item = ItemStack(Items.DIAMOND_BOOTS)
            canonical = "Boots"
        }
        else -> {
            item = ItemStack(Items.LEATHER_BOOTS)
            canonical = "Shoes"
        }
    }

    var suffix: String

    suffix = if (rand.nextInt(10) == 0) {
        item.addEnchantment(BLAST_PROTECTION, getProtection(rand, quality))
        "of Warding"
    } else if (rand.nextInt(5) == 0) {
        item.addEnchantment(PROTECTION, getProtection(rand, quality))
        item.addEnchantment(FEATHER_FALLING, if (quality === Quality.DIAMOND) 4 else 1 + rand.nextInt(3))
        "of Lightness"
    } else if (rand.nextInt(3) == 0) {
        item.addEnchantment(PROJECTILE_PROTECTION, getProtection(rand, quality))
        "of Deflection"
    } else {
        item.addEnchantment(PROTECTION, getProtection(rand, quality))
        "of Defense"
    }

    item.addEnchantment(UNBREAKING, getUnbreaking(rand, quality))

    if (rand.nextInt(10) == 0) {
        item.addEnchantment(MENDING, 1)
    }

    val name = getSpecialArmorPrefix(quality) + " " + canonical + " " + suffix
    setItemName(item, name)
    return item
}

fun getSpecialArmorPrefix(quality: Quality): String {
    return when (quality) {
        Quality.WOOD -> "Surplus"
        Quality.STONE -> "Riveted"
        Quality.IRON -> "Gothic"
        Quality.GOLD -> "Jewelled"
        Quality.DIAMOND -> "Crystal"
    }
}

fun getRandomShield(rand: Random): ItemStack {
    val banner = getRandomBanner(rand)
    val shield = ItemStack(Items.SHIELD)

    val bannerNbt = banner.getChildTag("BlockEntityTag")
    val shieldNbt = if (bannerNbt == null) CompoundNBT() else bannerNbt.copy()
    shield.setTagInfo("BlockEntityTag", shieldNbt)
    shieldNbt.putInt("Base", (banner.item as BannerItem).color.id)

    return shield
}

val ALL_BANNERS = arrayOf(Items.WHITE_BANNER, Items.ORANGE_BANNER, Items.MAGENTA_BANNER, Items.LIGHT_BLUE_BANNER, Items.YELLOW_BANNER, Items.LIME_BANNER, Items.PINK_BANNER, Items.GRAY_BANNER, Items.LIGHT_GRAY_BANNER, Items.CYAN_BANNER, Items.PURPLE_BANNER, Items.BLUE_BANNER, Items.BROWN_BANNER, Items.GREEN_BANNER, Items.RED_BANNER, Items.BLACK_BANNER)

fun getRandomBanner(rand: Random): ItemStack {
    val banner = ItemStack(rand.pickRandomly(ALL_BANNERS))

    val nbt = banner.getOrCreateChildTag("BlockEntityTag")
    val list = if (nbt.contains("Patterns", 9)) {
        nbt.getList("Patterns", 10)
    } else {
        ListNBT()
    }

    for (i in 0.rangeTo(rand.nextInt(8))) {
        val pattern = rand.nextEnum<BannerPattern>()
        val color = rand.nextEnum<DyeColor>()

        val tag = CompoundNBT()
        tag.putString("Pattern", pattern.hashname)
        tag.putInt("Color", color.id)

        list.add(tag)
    }

    nbt.put("Patterns", list)

    return banner
}

fun getRandomArmor(rand: Random, slot: EquipmentSlotType, quality: Quality): ItemStack {
    checkArmorSlot(slot)

    // end early for leather
    if (quality == Quality.WOOD) {
        return getDyedEquipment(rand, slot)
    }

    return when (slot) {
        EquipmentSlotType.HEAD -> {
            when (quality) {
                Quality.DIAMOND -> ItemStack(Items.DIAMOND_HELMET)
                Quality.GOLD -> ItemStack(Items.GOLDEN_HELMET)
                Quality.IRON -> ItemStack(Items.IRON_HELMET)
                Quality.STONE -> ItemStack(Items.CHAINMAIL_HELMET)
                else -> getDyedEquipment(rand, slot)
            }
        }
        EquipmentSlotType.CHEST -> {
            return when (quality) {
                Quality.DIAMOND -> ItemStack(Items.DIAMOND_CHESTPLATE)
                Quality.GOLD -> ItemStack(Items.GOLDEN_CHESTPLATE)
                Quality.IRON -> ItemStack(Items.IRON_CHESTPLATE)
                Quality.STONE -> ItemStack(Items.CHAINMAIL_CHESTPLATE)
                else -> getDyedEquipment(rand, slot)
            }
        }
        EquipmentSlotType.LEGS -> {
            return when (quality) {
                Quality.DIAMOND -> ItemStack(Items.DIAMOND_LEGGINGS)
                Quality.GOLD -> ItemStack(Items.GOLDEN_LEGGINGS)
                Quality.IRON -> ItemStack(Items.IRON_LEGGINGS)
                Quality.STONE -> ItemStack(Items.CHAINMAIL_LEGGINGS)
                else -> getDyedEquipment(rand, slot)
            }
        }
        EquipmentSlotType.FEET -> {
            return when (quality) {
                Quality.DIAMOND -> ItemStack(Items.DIAMOND_BOOTS)
                Quality.GOLD -> ItemStack(Items.GOLDEN_BOOTS)
                Quality.IRON -> ItemStack(Items.IRON_BOOTS)
                Quality.STONE -> ItemStack(Items.CHAINMAIL_BOOTS)
                else -> getDyedEquipment(rand, slot)
            }
        }
        else -> checkFailed()
    }
}

fun getRandomWeapon(rand: Random, level: Int, enchanted: Boolean): ItemStack {
    return if (rand.nextInt(10) == 0) {
        getRandomBow(rand, level, enchanted)
    } else {
        getRandomSword(rand, level, enchanted)
    }
}

fun getRandomBow(rand: Random, level: Int, enchanted: Boolean): ItemStack {
    if (rand.nextInt(20 + (level * 10)) == 0) {
        return getSpecialBow(rand, Quality.getWeaponQuality(rand, level))
    }

    val bow = ItemStack(Items.BOW)

    if (enchanted && rand.nextInt(6 - level) == 0) {
        enchantItem(rand, bow, getEnchantmentLevel(rand, level))
    }

    return bow
}

fun getRandomTool(rand: Random, level: Int, enchanted: Boolean): ItemStack {
    if (enchanted && rand.nextInt(20 + (level * 10)) == 0) {
        when (rand.nextInt(4)) {
            0 -> return getSpecialPickaxe(rand, Quality.getToolQuality(rand, level))
            1 -> return getSpecialAxe(rand, Quality.getToolQuality(rand, level))
            2 -> return getSpecialShovel(rand, Quality.getToolQuality(rand, level))
            3 -> return getSpecialHoe(rand, Quality.getToolQuality(rand, level))
        }
    }

    val tool = pickTool(rand, level)

    if (enchanted && rand.chance(6 - level)) {
        enchantItem(rand, tool, getEnchantmentLevel(rand, level))
    }

    return tool
}

fun getSpecialPickaxe(rand: Random, quality: Quality): ItemStack {
    if (quality == Quality.DIAMOND) {
        val item = ItemStack(Items.DIAMOND_PICKAXE)
        addEnchantments(item, EFFICIENCY, 3 + rand.nextInt(3), UNBREAKING, getUnbreaking(rand, quality))

        if (rand.chance(10)) {
            addEnchantments(item, SILK_TOUCH, 1)
            setItemName(item, "Crystal Pick of Precision")
            return item
        }

        if (rand.chance(10)) {
            addEnchantments(item, FORTUNE, 2 + rand.nextInt(2))
            setItemName(item, "Crystal Pick of Prospecting")
            return item
        }

        if (rand.chance(5)) {
            addEnchantments(item, MENDING, 1)
        }
        setItemName(item, "Crystal Pick")

        return item
    } else {
        val item = ItemStack(Items.IRON_PICKAXE)
        addEnchantments(item, EFFICIENCY, 1 + rand.nextInt(2), UNBREAKING, getUnbreaking(rand, quality))

        if (rand.chance(10)) {
            addEnchantments(item, SILK_TOUCH, 1)
            setItemName(item, "Case Hardened Pick of Precision")
            return item
        }

        if (rand.chance(10)) {
            addEnchantments(item, FORTUNE, 1 + rand.nextInt(3))
            setItemName(item, "Case Hardened Pick of Precision")
            return item
        }

        if (rand.chance(5)) {
            addEnchantments(item, MENDING, 1)
        }
        setItemName(item, "Case Hardened Pick")

        return item
    }
}

fun getSpecialAxe(rand: Random, quality: Quality): ItemStack {
    return if (quality == Quality.DIAMOND) {
        val item = ItemStack(Items.DIAMOND_AXE)

        addEnchantments(item, EFFICIENCY, 3 + rand.nextInt(3), UNBREAKING, getUnbreaking(rand, quality))
        setItemName(item, "Crystal Head Axe")

        item
    } else {
        val item = ItemStack(Items.IRON_AXE)

        addEnchantments(item, EFFICIENCY, 1 + rand.nextInt(2), UNBREAKING, getUnbreaking(rand, quality))
        setItemName(item, "Woodland Hatchet")

        item
    }
}

fun getSpecialShovel(rand: Random, quality: Quality): ItemStack {
    return if (quality == Quality.DIAMOND) {
        val item = ItemStack(Items.DIAMOND_SHOVEL)

        addEnchantments(item, EFFICIENCY, 3 + rand.nextInt(3), UNBREAKING, getUnbreaking(rand, quality))
        setItemName(item, "Soul Spade")

        item
    } else {
        val item = ItemStack(Items.IRON_SHOVEL)

        addEnchantments(item, EFFICIENCY, 1 + rand.nextInt(2), UNBREAKING, getUnbreaking(rand, quality))
        setItemName(item, "Grave Spade")

        item
    }
}

// added it for the lols
// not sorry
fun getSpecialHoe(rand: Random, quality: Quality): ItemStack {
    return when (quality) {
        Quality.DIAMOND -> {
            val item = ItemStack(Items.DIAMOND_HOE)

            addEnchantments(item, SHARPNESS, 3 + rand.nextInt(3), UNBREAKING, getUnbreaking(rand, quality))
            if (rand.chance(3)) {
                addEnchantments(item, MENDING, 1)
            }
            setItemName(item, "Shiny Scythe")

            item
        }
        Quality.GOLD -> {
            val item = ItemStack(Items.GOLDEN_HOE)

            addEnchantments(item, SHARPNESS, 2 + rand.nextInt(3), UNBREAKING, getUnbreaking(rand, quality))
            if (rand.chance(2)) {
                addEnchantments(item, MENDING, 1)
            }
            if (rand.chance(6)) {
                addEnchantments(item, FIRE_ASPECT, 1 + rand.nextInt(2))
                setItemName(item, "Flaming Scythe of the Piglins")
                setItemLore(item, "Forged in the flames of the Nether", TextFormatting.GOLD)

                return item
            }
            setItemName(item, "Gilded Scythe")

            item
        }
        else -> {
            val item = ItemStack(Items.IRON_HOE)

            addEnchantments(item, SHARPNESS, 2 + rand.nextInt(2))
            if (rand.chance(5)) {
                addEnchantments(item, MENDING, 1)
            }
            setItemName(item, "Silver Scythe")

            item
        }
    }
}

fun pickTool(rand: Random, level: Int): ItemStack {
    return when (rand.nextInt(4)) {
        0 -> when (Quality.getToolQuality(rand, level)) {
            Quality.DIAMOND -> ItemStack(Items.DIAMOND_PICKAXE)
            Quality.GOLD -> ItemStack(Items.GOLDEN_PICKAXE)
            Quality.IRON -> ItemStack(Items.IRON_PICKAXE)
            Quality.STONE -> ItemStack(Items.STONE_PICKAXE)
            else -> ItemStack(Items.WOODEN_PICKAXE)
        }
        1 -> when (Quality.getToolQuality(rand, level)) {
            Quality.DIAMOND -> ItemStack(Items.DIAMOND_AXE)
            Quality.GOLD -> ItemStack(Items.GOLDEN_AXE)
            Quality.IRON -> ItemStack(Items.IRON_AXE)
            Quality.STONE -> ItemStack(Items.STONE_AXE)
            else -> ItemStack(Items.WOODEN_AXE)
        }
        2 -> when (Quality.getToolQuality(rand, level)) {
            Quality.DIAMOND -> ItemStack(Items.DIAMOND_SHOVEL)
            Quality.GOLD -> ItemStack(Items.GOLDEN_SHOVEL)
            Quality.IRON -> ItemStack(Items.IRON_SHOVEL)
            Quality.STONE -> ItemStack(Items.STONE_SHOVEL)
            else -> ItemStack(Items.WOODEN_SHOVEL)
        }
        3 -> when (Quality.getToolQuality(rand, level)) {
            Quality.DIAMOND -> ItemStack(Items.DIAMOND_HOE)
            Quality.GOLD -> ItemStack(Items.GOLDEN_HOE)
            Quality.IRON -> ItemStack(Items.IRON_HOE)
            Quality.STONE -> ItemStack(Items.STONE_HOE)
            else -> ItemStack(Items.WOODEN_HOE)
        }
        else -> throw IllegalStateException("Random number generator $rand is invalid")
    }
}

fun getRandomSword(rand: Random, level: Int, enchanted: Boolean): ItemStack {
    val sword: ItemStack

    if (enchanted && rand.chance(10 + (level * 10))) {
        return getSpecialSword(rand, Quality.getWeaponQuality(rand, level))
    }

    sword = pickSword(rand, level)

    if (enchanted && rand.chance(6 - level)) {
        enchantItem(rand, sword, getEnchantmentLevel(rand, level))
    }

    return sword
}

fun getSpecialSword(rand: Random, quality: Quality): ItemStack {
    return if (quality == Quality.DIAMOND) {
        val item = ItemStack(Items.DIAMOND_SWORD)
        item.addEnchantment(SHARPNESS, 3 + rand.nextInt(3))
        if (rand.nextInt(10) == 0) {
            addEnchantments(item, LOOTING, 2 + rand.nextInt(2), UNBREAKING, getUnbreaking(rand, quality))
            setItemName(item, "Eldritch Blade of Plundering")
            setItemLore(item, "The loot taker", TextFormatting.DARK_GREEN)
            return item
        } else if (rand.nextInt(10) == 0) {
            item.addEnchantment(FIRE_ASPECT, 2 + rand.nextInt(2))
            item.addEnchantment(UNBREAKING, getUnbreaking(rand, quality))
            setItemName(item, "Eldritch Blade of the Inferno")
            setItemLore(item, "From the fiery depths", TextFormatting.DARK_GREEN)
            return item
        }
        addEnchantments(item, UNBREAKING, if (quality == Quality.DIAMOND) 3 else 1 + rand.nextInt(2))
        addEnchantments(item, MENDING, 1)
        setItemName(item, "Eldritch Blade")
        setItemLore(item, "Rune Etched", TextFormatting.DARK_GREEN)
        item
    } else {
        val item = ItemStack(Items.IRON_SWORD)
        if (rand.nextBoolean()) {
            addEnchantments(item, SHARPNESS, 1)
        }
        addEnchantments(item, UNBREAKING, 3, MENDING, 1)
        setItemName(item, "Tempered Blade")
        setItemLore(item, "Highly Durable", TextFormatting.DARK_GREEN)
        item
    }
}

fun pickSword(rand: Random, level: Int): ItemStack {
    return when (Quality.getWeaponQuality(rand, level)) {
        Quality.DIAMOND -> ItemStack(Items.DIAMOND_SWORD)
        Quality.GOLD -> ItemStack(Items.GOLDEN_SWORD)
        Quality.IRON -> ItemStack(Items.IRON_SWORD)
        Quality.STONE -> ItemStack(Items.STONE_SWORD)
        else -> ItemStack(Items.WOODEN_SWORD)
    }
}

fun getSpecialBow(rand: Random, quality: Quality): ItemStack {
    val bow = ItemStack(Items.BOW)

    when (quality) {
        Quality.WOOD, Quality.STONE -> {
            bow.addEnchantment(POWER, 1 + rand.nextInt(3))
            bow.addEnchantment(UNBREAKING, 1)
            setItemName(bow, "Yew Longbow")
            setItemLore(bow, "Superior craftsmanship", TextFormatting.DARK_GREEN)
        }
        Quality.IRON -> {
            bow.addEnchantment(POWER, 1 + rand.nextInt(3))
            bow.addEnchantment(UNBREAKING, 1 + rand.nextInt(3))
            setItemName(bow, "Laminated Bow")
            setItemLore(bow, "Highly polished", TextFormatting.DARK_GREEN)
        }
        Quality.GOLD -> {
            bow.addEnchantment(POWER, 3 + rand.nextInt(3))
            bow.addEnchantment(UNBREAKING, 1 + rand.nextInt(3))
            if (rand.nextBoolean()) {
                bow.addEnchantment(INFINITY, 1)
            }
            setItemName(bow, "Recurve Bow")
            setItemLore(bow, "Beautifully crafted", TextFormatting.DARK_GREEN)
        }
        Quality.DIAMOND -> {
            bow.addEnchantment(POWER, 3 + rand.nextInt(3))
            bow.addEnchantment(FLAME, 1)
            bow.addEnchantment(INFINITY, 1)
            bow.addEnchantment(UNBREAKING, getUnbreaking(rand, quality))
            bow.addEnchantment(MENDING, 1)
            setItemName(bow, "Eldritch Bow")
            setItemLore(bow, "Warm to the touch", TextFormatting.DARK_GREEN)
        }
    }

    return bow
}

fun setItemName(stack: ItemStack, name: String, color: TextFormatting? = null) {
    stack.displayName = applyTextFormatting(name, color)
}

fun setItemLore(stack: ItemStack, loreText: String, color: TextFormatting? = null) {
    val text = applyTextFormatting(loreText, color)
    val tag = stack.orCreateTag

    if (!tag.contains("display", 10)) {
        tag.put("display", CompoundNBT())
    }

    val display = tag.getCompound("display")

    if (!display.contains("Lore", 9)) {
        display.put("Lore", ListNBT())
    }

    val lore = display.getList("Lore", 9)

    lore.add(StringNBT.of(ITextComponent.Serializer.toJson(text)))
}

fun applyTextFormatting(text: String, color: TextFormatting?): ITextComponent {
    val component = StringTextComponent(text)

    if (color != null) {
        component.applyTextStyle(color)
    }

    return component
}

//
// Potions
//