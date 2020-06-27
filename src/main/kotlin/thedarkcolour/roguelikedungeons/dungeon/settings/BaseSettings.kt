package thedarkcolour.roguelikedungeons.dungeon.settings

import net.minecraft.util.ResourceLocation
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.RoguelikeDungeons

@Complete
class BaseSettings : DungeonSettings() {
    override val id = ResourceLocation(RoguelikeDungeons.ID, "base")

    override fun apply(settings: DungeonSettings) {
        inherit(ROOMS_SETTINGS)
        inherit(SECRETS_SETTINGS)
        inherit(SEGMENTS_SETTINGS)
        inherit(LAYOUT_SETTINGS)
        inherit(THEME_SETTINGS)
        inherit(LOOT_PROCESSING_SETTINGS)
    }
}