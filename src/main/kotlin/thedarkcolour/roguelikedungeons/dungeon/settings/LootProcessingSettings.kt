package thedarkcolour.roguelikedungeons.dungeon.settings

import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.util.ResourceLocation
import thedarkcolour.roguelikedungeons.RoguelikeDungeons
import thedarkcolour.roguelikedungeons.loot.LootProvider
import thedarkcolour.roguelikedungeons.loot.LootType
import thedarkcolour.roguelikedungeons.loot.book.StarterBook
import thedarkcolour.roguelikedungeons.loot.getDyedEquipment
import thedarkcolour.roguelikedungeons.loot.item.EnchantedBookItemLoot
import thedarkcolour.roguelikedungeons.loot.item.SpecialItemLoot
import thedarkcolour.roguelikedungeons.loot.treasure.TreasureType

class LootProcessingSettings : DungeonSettings() {
    override val id = ResourceLocation(RoguelikeDungeons.ID, "loot_processing")

    override fun apply(settings: DungeonSettings) {
        val loot = LootProvider()
        lootProcessor.addRule(TreasureType.STARTER, StarterBook.create(), 0, true, 1)
        lootProcessor.addRule(TreasureType.STARTER, loot.get(LootType.WEAPONS, 0), 0, true, 2)
        lootProcessor.addRule(TreasureType.STARTER, loot.get(LootType.FOOD, 0), 0, true, 2)
        lootProcessor.addRule(TreasureType.STARTER, loot.get(LootType.TOOLS, 0), 0, true, 2)
        lootProcessor.addRule(TreasureType.STARTER, loot.get(LootType.SUPPLY, 0), 0, true, 2)
        lootProcessor.addRule(TreasureType.STARTER, 0, true, 2) { rand ->
            getDyedEquipment(rand, EquipmentSlotType.LEGS)
        }
        for (i in 0..4) {
            lootProcessor.addRule(TreasureType.ARMOR, loot.get(LootType.POTION, i), i, true, 1)
            lootProcessor.addRule(TreasureType.ARMOR, loot.get(LootType.ARMOR, i), i, true, 1)
            lootProcessor.addRule(TreasureType.ARMOR, loot.get(LootType.FOOD, i), i, true, 1)
            lootProcessor.addRule(TreasureType.WEAPONS, loot.get(LootType.POTION, i), i, true, 1)
            lootProcessor.addRule(TreasureType.WEAPONS, loot.get(LootType.WEAPONS, i), i, true, 1)
            lootProcessor.addRule(TreasureType.WEAPONS, loot.get(LootType.FOOD, i), i, true, 1)
            lootProcessor.addRule(TreasureType.BLOCKS, loot.get(LootType.BLOCKS, i), i, true, 6)
            lootProcessor.addRule(TreasureType.WEAPONS, loot.get(LootType.FOOD, i), i, true, 1)
            lootProcessor.addRule(TreasureType.ENCHANTING, loot.get(LootType.ENCHANT_BONUS, i), i, true, 2)
            lootProcessor.addRule(TreasureType.ENCHANTING, loot.get(LootType.ENCHANTED_BOOK, i), i, true, 1)
            lootProcessor.addRule(TreasureType.FOOD, loot.get(LootType.FOOD, i), i, true, 8)
            lootProcessor.addRule(TreasureType.ORE, loot.get(LootType.ORE, i), i, true, 5)
            lootProcessor.addRule(TreasureType.POTIONS, loot.get(LootType.POTION, i), i, true, 6)
            lootProcessor.addRule(TreasureType.BREWING, loot.get(LootType.BREWING, i), i, true, 8)
            lootProcessor.addRule(TreasureType.TOOLS, loot.get(LootType.ORE, i), i, true, 1)
            lootProcessor.addRule(TreasureType.TOOLS, loot.get(LootType.TOOLS, i), i, true, 1)
            lootProcessor.addRule(TreasureType.TOOLS, loot.get(LootType.BLOCKS, i), i, true, 1)
            lootProcessor.addRule(TreasureType.SUPPLIES, loot.get(LootType.SUPPLY, i), i, true, 6)
            lootProcessor.addRule(TreasureType.SMITH, loot.get(LootType.ORE, i), i, true, 6)
            lootProcessor.addRule(TreasureType.SMITH, loot.get(LootType.SMITHY, i), i, true, 1)
            lootProcessor.addRule(TreasureType.MUSIC, loot.get(LootType.MUSIC, i), i, true, 1)
            lootProcessor.addRule(TreasureType.REWARD, loot.get(LootType.REWARD, i), i, true, 1)
            lootProcessor.addRule(null, loot.get(LootType.JUNK, i), i, true, 6)
            lootProcessor.addRule(null, SpecialItemLoot(i), i, false, 3)
            lootProcessor.addRule(null, EnchantedBookItemLoot(i), i, false, i * 2 + 5)
        }
    }
}