package thedarkcolour.roguelikedungeons.loot.book

import net.minecraft.item.ItemStack
import net.minecraftforge.fml.ModList
import thedarkcolour.roguelikedungeons.RoguelikeDungeons

object StarterBook : AbstractBook() {
    private val version by lazy {
        ModList.get().mods.first { info ->
            info.modId == RoguelikeDungeons.ID
        }.version
    }

    fun create(): ItemStack {
        return create(listOf(
                """
                    Roguelike Dungeons v$version
                    
                    Credits
                    
                    Author: TheDarkColour, Greymerk
                    
                    Bits: Drainedsoul
                    
                    Ideas: Eniko @enichan
                    """.trimIndent()
        ), "TheDarkColour", "Roguelike Dungeons: Refreshed")
    }
}