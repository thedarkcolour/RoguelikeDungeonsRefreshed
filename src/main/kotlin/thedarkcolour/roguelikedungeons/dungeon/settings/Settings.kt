package thedarkcolour.roguelikedungeons.dungeon.settings

import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.loot.weight.WeightedEntry
import thedarkcolour.roguelikedungeons.loot.weight.WeightedPool
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

// core settings
val ROOMS_SETTINGS = RoomsSettings()
val SECRETS_SETTINGS = SecretsSettings()
val SEGMENTS_SETTINGS = SegmentsSettings()
val LAYOUT_SETTINGS = LayoutSettings()
val THEME_SETTINGS = ThemeSettings()
val LOOT_PROCESSING_SETTINGS = LootProcessingSettings()

// contains the core settings for other dungeons
val BASE_SETTINGS = BaseSettings()

// themed settings for certain biomes
val JUNGLE_SETTINGS = JungleSettings()

private val BUILTIN = arrayOf(
    JUNGLE_SETTINGS
)

fun getSettings(worldIn: DungeonWorldIn, rand: Random, pos: BlockPos): DungeonSettings? {
    // if (Config.random) return RandomSettings()

    val randomizer = WeightedPool<DungeonSettings>()

    for (setting in BUILTIN) {
        if (setting.isValid(worldIn, pos)) {
            randomizer.add(WeightedEntry(setting, 1))
        }
    }

    return if (!randomizer.isEmpty()) {
        randomizer.get(rand)
    } else null
}