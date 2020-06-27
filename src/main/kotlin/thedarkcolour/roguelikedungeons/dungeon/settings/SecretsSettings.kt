package thedarkcolour.roguelikedungeons.dungeon.settings

import net.minecraft.util.ResourceLocation
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.RoguelikeDungeons
import thedarkcolour.roguelikedungeons.dungeon.SecretFactory

@Complete
@Suppress("ReplacePutWithAssignment")
class SecretsSettings : DungeonSettings() {
    override fun apply(settings: DungeonSettings) {
        for (i in 0 until 5) {
            val factory = SecretFactory()
            val level = settings.getDungeonLevel(i)

            level.secrets = factory
        }
    }

    override val id = ResourceLocation(RoguelikeDungeons.ID, "secrets")
}