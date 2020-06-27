package thedarkcolour.roguelikedungeons.loot

import net.minecraft.enchantment.Enchantments.*
import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.ItemStack
import net.minecraft.item.Items.*
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import thedarkcolour.roguelikedungeons.enemy.ModifiableEnemy
import java.util.*

/** todo switch to a builder pattern
 * An equipment profile for novelty items. Meant to represent certain
 * players, I think it's an easter egg sort of thing.
 *
 * I'll have all the original ones and I'll add more for people who donate to me.
 *
 * This is a fun interface. Enjoy yourself.
 */
fun interface Novelty {
    fun addItem(worldIn: World, rand: Random, level: Int, enemy: ModifiableEnemy)

    /**
     * Novelty items for certain players.
     */
    @Suppress("UNUSED_ANONYMOUS_PARAMETER")
    companion object {
        val CORONA_CULT_LEADER = Novelty { worldIn, rand, level, enemy ->
            val item = ItemStack(TRIDENT)
            addEnchantments(item, CHANNELING, 1, VANISHING_CURSE, 1, FIRE_ASPECT, 2, IMPALING, 1, KNOCKBACK, 2, LOOTING, 3, LOYALTY, 1, MENDING, 1, SHARPNESS, 5)
            setItemName(item, "Timeless Corona Cult Staff")
            setItemLore(item, "This staff was the weapon of the Corona Cult Leader which, after years being lost and forgotten, found its way into your hands.", TextFormatting.ITALIC)
            enemy.setItem(EquipmentSlotType.CHEST, item)
        }
        val DJROUNDTABLE = Novelty { worldIn, rand, level, enemy ->
            val item = ItemStack(IRON_LEGGINGS)
            addEnchantments(item, FIRE_PROTECTION, 4, UNBREAKING, 3, PROJECTILE_PROTECTION, 2)
            setItemName(item, "Crest of RoundTable")
            setItemLore(item, "A relic lost to the days of the crusades")
            enemy.setItem(EquipmentSlotType.LEGS, item)
        }
        val GREYMERK = Novelty { worldIn, rand, level, enemy ->
            val item = ItemStack(IRON_AXE)
            addEnchantments(item, SHARPNESS, 3, KNOCKBACK, 1, UNBREAKING, 2)
            setItemLore(item, "Greymerk's Hatchet", TextFormatting.DARK_PURPLE)
            setItemLore(item, "Pointlessly sharp", TextFormatting.DARK_GREEN)
            enemy.setItem(EquipmentSlotType.MAINHAND, item)
        }
        val NEBRIS_CROWN = Novelty { worldIn, rand, level, enemy ->
            val item = ItemStack(GOLDEN_HELMET)
            addEnchantments(item, PROTECTION, 4, UNBREAKING, 3)
            setItemName(item, "Nebrian Crown of Justice")
            setItemLore(item, "Adorned with precious gemstones")
            enemy.setItem(EquipmentSlotType.HEAD, item)
        }
        val MAN_PANTS = Novelty { worldIn, rand, level, enemy ->
            val item = ItemStack(LEATHER_LEGGINGS)
            addEnchantments(item, FIRE_PROTECTION, 4, UNBREAKING, 3)
            setItemName(item, "Man Pants", null)
            setItemLore(item, "Yessss, Manpants!", TextFormatting.DARK_GREEN)
            dyeArmor(item, 0xfa8072)
            enemy.setItem(EquipmentSlotType.LEGS, item)
        }
        val ASHLEA = Novelty { worldIn, rand, level, enemy ->
            val item = ItemStack(COOKIE)
            addEnchantments(item, SHARPNESS, 2, KNOCKBACK, 1)
            setItemLore(item, "Ashlea's Oatmeal Cookie", TextFormatting.DARK_PURPLE)
            setItemLore(item, "Perfect for elevensies", TextFormatting.DARK_GREEN)
            enemy.setItem(EquipmentSlotType.MAINHAND, item)
        }
        val RLEAHY = Novelty { worldIn, rand, level, enemy ->
            val item = ItemStack(BREAD)
            setItemName(item, "Rleahian battle sub", TextFormatting.DARK_PURPLE)
            setItemLore(item, "With extra pastrami", TextFormatting.DARK_GREEN)
            addEnchantments(item, SHARPNESS, 2, KNOCKBACK, 1, FIRE_ASPECT, 2)
            enemy.setItem(EquipmentSlotType.MAINHAND, item)
        }
        val VALANDRAH = Novelty { worldIn, rand, level, enemy ->
            val item = ItemStack(IRON_SWORD)
            setItemName(item, "Valandrah's Kiss")
            setItemLore(item, "\"Feel the kiss of my blade\"")
            addEnchantments(item, SHARPNESS, 4, FIRE_ASPECT, 1, KNOCKBACK, 1, UNBREAKING, 2)
            enemy.setWeapon(item)
        }
    }
}