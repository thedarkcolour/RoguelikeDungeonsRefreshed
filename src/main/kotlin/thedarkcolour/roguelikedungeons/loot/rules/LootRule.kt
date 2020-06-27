package thedarkcolour.roguelikedungeons.loot.rules

import net.minecraft.item.ItemStack
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.loot.treasure.TreasureManager
import thedarkcolour.roguelikedungeons.loot.treasure.TreasureType
import thedarkcolour.roguelikedungeons.loot.weight.IWeighted
import java.util.*

@Complete
data class LootRule(
    val type: TreasureType?,
    val item: IWeighted<ItemStack>,
    val level: Int,
    val toEach: Boolean,
    val amount: Int,
) {
    fun process(rand: Random, treasure: TreasureManager) {
        if (type == null) {
            if (toEach) {
                treasure.addItemToAll(rand, level, item, amount)
            } else {
                treasure.addItem(rand, level, item, amount)
            }
        } else {
            if (toEach) {
                treasure.addItemToAll(rand, type, level, item, amount)
            } else {
                treasure.addItem(rand, type, level, item, amount)
            }
        }
    }
}