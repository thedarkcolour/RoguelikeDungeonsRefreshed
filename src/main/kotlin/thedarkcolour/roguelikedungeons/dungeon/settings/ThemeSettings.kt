package thedarkcolour.roguelikedungeons.dungeon.settings

import net.minecraft.util.ResourceLocation
import thedarkcolour.roguelikedungeons.RoguelikeDungeons
import thedarkcolour.roguelikedungeons.dungeon.theme.Themes

@Suppress("ReplacePutWithAssignment")
class ThemeSettings : DungeonSettings() {
    override val id = ResourceLocation(RoguelikeDungeons.ID, "theme")

    override fun apply(settings: DungeonSettings) {
        // todo towers

        val themes = arrayOf(Themes.OAK, Themes.SPRUCE, Themes.CRYPT, Themes.MOSSY, Themes.HELL)

        for (i in 0 until 5) {
            val level = settings.getDungeonLevel(i)
            level.theme = themes[i]
        }
    }
}