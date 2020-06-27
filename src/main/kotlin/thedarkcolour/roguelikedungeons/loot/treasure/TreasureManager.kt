package thedarkcolour.roguelikedungeons.loot.treasure

import net.minecraft.item.ItemStack
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.loot.weight.IWeighted
import java.util.*
import kotlin.collections.ArrayList

@Complete
class TreasureManager {
    private val chests = ArrayList<TreasureChest>()

    fun add(toAdd: TreasureChest) {
        chests.add(toAdd)
    }

    fun addItemToAll(rand: Random, type: TreasureType, level: Int, item: IWeighted<ItemStack>, amount: Int) {
        addItemToAll(rand, getChests(type, level), item, amount)
    }

    fun addItemToAll(rand: Random, level: Int, item: IWeighted<ItemStack>, amount: Int) {
        addItemToAll(rand, getChests(level), item, amount)
    }

    fun addItemToAll(rand: Random, type: TreasureType, item: IWeighted<ItemStack>, amount: Int) {
        addItemToAll(rand, getChests(type), item, amount)
    }

    private fun addItemToAll(rand: Random, chests: List<TreasureChest>, item: IWeighted<ItemStack>, amount: Int) {
        for (chest in chests) {
            for (i in 0 until amount) {
                chest.setRandomEmptySlot(item.get(rand))
            }
        }
    }

    fun addItem(rand: Random, level: Int, item: IWeighted<ItemStack>, amount: Int) {
        addItem(rand, getChests(level), item, amount)
    }

    fun addItem(rand: Random, type: TreasureType, item: IWeighted<ItemStack>, amount: Int) {
        addItem(rand, getChests(type), item, amount)
    }

    fun addItem(rand: Random, type: TreasureType, level: Int, item: IWeighted<ItemStack>, amount: Int) {
        addItem(rand, getChests(type, level), item, amount)
    }

    private fun addItem(rand: Random, chests: List<TreasureChest>, item: IWeighted<ItemStack>, amount: Int) {
        if (chests.isEmpty()) return
        for (i in 0 until amount) {
            val chest = chests[rand.nextInt(chests.size)]
            chest.setRandomEmptySlot(item.get(rand))
        }
    }

    fun getChests(type: TreasureType, level: Int): List<TreasureChest> {
        val c = ArrayList<TreasureChest>()
        
        for (chest in chests) {
            if (chest.type == type && chest.level == level) c.add(chest)
        }
        
        return c
    }

    fun getChests(type: TreasureType): List<TreasureChest> {
        val c = ArrayList<TreasureChest>()

        for (chest in chests) {
            if (chest.type == type) c.add(chest)
        }

        return c
    }

    fun getChests(level: Int): List<TreasureChest> {
        val c = ArrayList<TreasureChest>()

        for (chest in chests) {
            if (chest.type == TreasureType.EMPTY) continue
            if (chest.level == level) c.add(chest)
        }

        return c
    }

    fun getChests(): List<TreasureChest> {
        val c = ArrayList<TreasureChest>()
        for (chest in chests) {
            if (chest.type != TreasureType.EMPTY) c.add(chest)
        }
        return c
    }
}