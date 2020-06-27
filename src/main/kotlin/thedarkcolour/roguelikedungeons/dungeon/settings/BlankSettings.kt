package thedarkcolour.roguelikedungeons.dungeon.settings

import net.minecraft.util.ResourceLocation
import thedarkcolour.roguelikedungeons.RoguelikeDungeons

class BlankSettings : DungeonSettings() {
    override val id = ResourceLocation(RoguelikeDungeons.ID, "blank")

    /**
     * Apply the functionality of this class to the given [DungeonSettings].
     *
     * DO NOT ACCESS MEMBERS OF THE RECEIVER.
     */
    override fun apply(settings: DungeonSettings) {}
}