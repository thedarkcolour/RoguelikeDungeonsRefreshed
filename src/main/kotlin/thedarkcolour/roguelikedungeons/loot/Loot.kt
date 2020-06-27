package thedarkcolour.roguelikedungeons.loot

import net.minecraft.block.Blocks
import net.minecraft.item.ItemStack
import thedarkcolour.roguelikedungeons.loot.item.IItemLoot
import thedarkcolour.roguelikedungeons.loot.item.JunkItemLoot
import thedarkcolour.roguelikedungeons.loot.item.OreItemLoot
import thedarkcolour.roguelikedungeons.loot.weight.IWeighted
import thedarkcolour.roguelikedungeons.loot.weight.WeightedLoot
import thedarkcolour.roguelikedungeons.loot.weight.WeightedPool
import thedarkcolour.roguelikedungeons.util.pickRandomly

private val COMMON = arrayListOf(LootType.TOOLS, LootType.ARMOR, LootType.WEAPONS)
private val BLOCKS = WeightedPool<ItemStack>().also { pool ->
    pool.add(WeightedLoot(10, Blocks.COBBLESTONE, 8, 32))
    pool.add(WeightedLoot(5, Blocks.STONE_BRICKS, 8, 32))
    pool.add(WeightedLoot(1, Blocks.MOSSY_STONE_BRICKS, 8, 32))
    pool.add(WeightedLoot(1, Blocks.CRACKED_STONE_BRICKS, 8, 32))
    pool.add(WeightedLoot(1, Blocks.POLISHED_ANDESITE, 8, 32))
    pool.add(WeightedLoot(1, Blocks.POLISHED_GRANITE, 8, 32))
    pool.add(WeightedLoot(1, Blocks.POLISHED_DIORITE, 8, 32))
}

fun getLootProvider(type: LootType, level: Int): IWeighted<ItemStack> {
    return when (type) {
        LootType.WEAPONS -> IItemLoot { rand ->
            getRandomWeapon(rand, level, true)
        }
        LootType.ARMOR -> IItemLoot { rand ->
            getRandomArmor(rand, level, rand.pickRandomly(ARMOR_SLOTS), true)
        }
        LootType.BLOCKS -> IItemLoot(BLOCKS::get)
        LootType.JUNK -> JunkItemLoot(level)
        LootType.ORE -> OreItemLoot(level)
        LootType.TOOLS -> TODO()
        LootType.POTION -> TODO()
        LootType.FOOD -> TODO()
        LootType.ENCHANTED_BOOK -> TODO()
        LootType.ENCHANT_BONUS -> TODO()
        LootType.SUPPLY -> TODO()
        LootType.MUSIC -> TODO()
        LootType.SMITHY -> TODO()
        LootType.SPECIAL -> TODO()
        LootType.REWARD -> TODO()
        LootType.BREWING -> TODO()
    }
}