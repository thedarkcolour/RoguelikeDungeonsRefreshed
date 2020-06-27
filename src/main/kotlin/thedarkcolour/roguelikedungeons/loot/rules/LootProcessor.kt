package thedarkcolour.roguelikedungeons.loot.rules

import net.minecraft.item.ItemStack
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.loot.item.IItemLoot
import thedarkcolour.roguelikedungeons.loot.treasure.TreasureManager
import thedarkcolour.roguelikedungeons.loot.treasure.TreasureType
import thedarkcolour.roguelikedungeons.loot.weight.IWeighted
import thedarkcolour.roguelikedungeons.loot.weight.Unweighted
import java.util.*
import kotlin.collections.ArrayList

/**
 * Processes loot and modifies it according to certain rules.
 */
@Complete
class LootProcessor {
    private val rules = ArrayList<LootRule>()

    fun addRule(type: TreasureType?, item: IWeighted<ItemStack>, level: Int, toEach: Boolean, amount: Int) {
        rules.add(LootRule(type, item, level, toEach, amount))
    }

    fun addRule(type: TreasureType?, level: Int, toEach: Boolean, amount: Int, item: IItemLoot) {
        rules.add(LootRule(type, item, level, toEach, amount))
    }

    fun addRule(type: TreasureType?, item: ItemStack, level: Int, toEach: Boolean, amount: Int) {
        rules.add(LootRule(type, Unweighted(item), level, toEach, amount))
    }

    fun addRule(rule: LootRule) {
        rules.add(rule)
    }

    fun add(other: LootProcessor) {
        rules += other.rules
    }

    fun process(rand: Random, treasure: TreasureManager) {
        for (rule in rules) {
            rule.process(rand, treasure)
        }
    }
}