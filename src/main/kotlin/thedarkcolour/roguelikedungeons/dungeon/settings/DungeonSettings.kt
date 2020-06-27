package thedarkcolour.roguelikedungeons.dungeon.settings

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.biome.Biome
import thedarkcolour.roguelikedungeons.dungeon.level.LevelSettings
import thedarkcolour.roguelikedungeons.dungeon.theme.Themes
import thedarkcolour.roguelikedungeons.dungeon.tower.TowerSettings
import thedarkcolour.roguelikedungeons.dungeon.tower.TowerType
import thedarkcolour.roguelikedungeons.loot.rules.LootProcessor
import thedarkcolour.roguelikedungeons.loot.treasure.TreasureManager
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*
import java.util.function.IntFunction
import kotlin.collections.ArrayList

abstract class DungeonSettings {
    abstract val id: ResourceLocation
    private val parents = ArrayList<DungeonSettings>()
    private val levels = Int2ObjectOpenHashMap<LevelSettings>()
    protected var lootProcessor = LootProcessor()
    val towerSettings = TowerSettings(TowerType.ROGUE, Themes.STONE)

    /**
     * Apply the functionality of this class to the given [DungeonSettings].
     *
     * DO NOT ACCESS MEMBERS OF THE RECEIVER.
     */
    abstract fun apply(settings: DungeonSettings)

    /**
     * Only called in the constructor to add parent settings.
     *
     * Parents are not stored in hierarchical order.
     */
    fun inherit(settings: DungeonSettings) {
        parents.add(settings)

        for (parent in settings.parents) {
            inherit(parent)
        }
    }

    fun isValid(worldIn: DungeonWorldIn, pos: BlockPos): Boolean {
        return isValidBiome(worldIn.getBiome(pos))
    }

    open fun isValidBiome(biome: Biome): Boolean {
        return false
    }

    /**
     * Gets the level settigns at the current level or creates it if it doesn't exist
     */
    fun getDungeonLevel(i: Int): LevelSettings {
        return levels.computeIfAbsent(i, IntFunction { LevelSettings() })
    }

    fun getLevelSettings(i: Int): LevelSettings {
        return levels[i]
    }

    fun getLootSettings(): LootProcessor {
        return lootProcessor
    }

    fun applyInheritance(): DungeonSettings {
        val blank = BlankSettings()

        for (parent in parents) {
            parent.apply(blank)
        }

        return blank
    }

    fun processLoot(rand: Random, treasure: TreasureManager) {
        lootProcessor.process(rand, treasure)
    }
}