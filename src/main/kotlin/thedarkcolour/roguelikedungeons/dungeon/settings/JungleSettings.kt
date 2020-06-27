package thedarkcolour.roguelikedungeons.dungeon.settings

import net.minecraft.util.ResourceLocation
import thedarkcolour.roguelikedungeons.RoguelikeDungeons

class JungleSettings : DungeonSettings() {
    override val id = ResourceLocation(RoguelikeDungeons.ID, "jungle")

    init {
        inherit(BASE_SETTINGS)
    }

    override fun apply(settings: DungeonSettings) {

    }
}