package thedarkcolour.roguelikedungeons.loot.item

import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import thedarkcolour.roguelikedungeons.enemy.potion.*
import thedarkcolour.roguelikedungeons.loot.getRandomShield
import thedarkcolour.roguelikedungeons.util.chance
import thedarkcolour.roguelikedungeons.util.nextEnum
import java.util.*

class JunkItemLoot(val level: Int) : IItemLoot {
    override fun get(rand: Random): ItemStack {
        return if (level > 0 && rand.chance(200)) {
            if (level > 2 && rand.chance(10)) {
                ItemStack(Items.DIAMOND_HORSE_ARMOR)
            } else if (level > 1 && rand.chance(5)) {
                ItemStack(Items.GOLDEN_HORSE_ARMOR)
            } else if (rand.chance(3)) {
                ItemStack(Items.IRON_HORSE_ARMOR)
            } else ItemStack(Items.SADDLE)
        } else if (rand.chance(100)) {
            getRandomPotion(rand)
        } else if (level > 1 && rand.chance(100)) {
            ItemStack(Items.GHAST_TEAR)
        } else if (level < 3 && rand.chance(80)) {
            ItemStack(Items.BOOK)
        } else if (rand.chance(80)) {
            getRandomShield(rand)
        } else if (level > 1 && rand.chance(60)) {
            getTippedArrow(rand.nextEnum<PotionType>(), 4 + rand.nextInt(level) * 2)
        } else if (level > 1 && rand.chance(50)) {
            when (rand.nextInt(6)) {
                0 -> ItemStack(Items.GUNPOWDER, 1 + rand.nextInt(3))
                1 -> ItemStack(Items.BLAZE_POWDER, 1 + rand.nextInt(3))
                2 -> ItemStack(Items.GOLD_NUGGET, 1 + rand.nextInt(3))
                3 -> ItemStack(Items.REDSTONE, 1 + rand.nextInt(3))
                4 -> ItemStack(Items.GLOWSTONE_DUST, 1 + rand.nextInt(3))
                5 -> ItemStack(Items.BONE_MEAL, 1 + rand.nextInt(3))
                else -> throw IllegalStateException("Invalid RNG")
            }
        } else if (rand.chance(60)) {
            getPotionPreset(PotionPreset.LAUDANUM, rand)
        } else if (rand.chance(30)) {
            ItemStack(Items.TORCH, 6 + rand.nextInt(20))
        } else if (level > 0 && rand.chance(8)) {
            when (rand.nextInt(8)) {
                0 -> ItemStack(Items.SLIME_BALL)
                1 -> ItemStack(Items.SNOWBALL)
                2 -> ItemStack(Items.MUSHROOM_STEW)
                3 -> ItemStack(Items.CLAY_BALL)
                4 -> ItemStack(Items.FLINT)
                5 -> ItemStack(Items.FEATHER)
                6 -> ItemStack(Items.GLASS_BOTTLE)
                7 -> ItemStack(Items.LEATHER)
                else -> throw IllegalStateException("Invalid RNG")
            }
        } else {
            when (rand.nextInt(7)) {
                0 -> ItemStack(Items.BONE)
                1 -> ItemStack(Items.ROTTEN_FLESH)
                2 -> ItemStack(Items.SPIDER_EYE)
                3 -> ItemStack(Items.PAPER)
                4 -> ItemStack(Items.STRING)
                else -> ItemStack(Items.STICK)
            }
        }
    }
}