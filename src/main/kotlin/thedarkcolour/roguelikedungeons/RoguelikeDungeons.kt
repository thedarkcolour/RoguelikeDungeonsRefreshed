package thedarkcolour.roguelikedungeons

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.monster.MonsterEntity
import net.minecraft.entity.monster.SlimeEntity
import net.minecraft.potion.Effects
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.feature.IFeatureConfig
import net.minecraft.world.gen.feature.structure.Structure
import net.minecraftforge.common.BiomeDictionary
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.eventbus.api.EventPriority
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.FORGE_BUS
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.roguelikedungeons.enemy.ModifiableEnemy
import thedarkcolour.roguelikedungeons.enemy.profile.EnemyProfileType
import thedarkcolour.roguelikedungeons.structure.DungeonStructure

@Mod(RoguelikeDungeons.ID)
object RoguelikeDungeons {
    const val ID = "roguelikedungeons"

    private val DUNGEON: DungeonStructure

    init {
        MOD_BUS.addListener(EventPriority.LOWEST, ::onLoadComplete)
        MOD_BUS.addListener(::onFeatureRegistry)
        FORGE_BUS.addListener(::onEntityJoinWorld)

        DUNGEON = DungeonStructure()
        DUNGEON.setRegistryName("roguelikedungeons:roguelike_dungeon")
    }

    private fun onEntityJoinWorld(event: EntityJoinWorldEvent) {
        val worldIn = event.world

        if (!worldIn.isRemote) {
            val entity = event.entity

            if ((entity is MonsterEntity || entity is SlimeEntity) && entity is LivingEntity) {
                val effects = entity.activePotionEffects

                for (effect in effects) {
                    if (effect.potion == Effects.MINING_FATIGUE) {
                        val level = effect.amplifier
                        val enemy = ModifiableEnemy(entity)
                        EnemyProfileType.equip(worldIn, worldIn.rand, level, enemy)

                        if (!entity.isAlive) {
                            event.isCanceled = true
                        }
                        return
                    }
                }
            }
        }
    }

    private fun onFeatureRegistry(event: RegistryEvent.Register<Structure<*>>) {
        event.registry.register(DUNGEON)
    }

    private fun onLoadComplete(event: FMLLoadCompleteEvent) {
        val invalidBiomes = HashSet<Biome>()
        invalidBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.RIVER))
        invalidBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.BEACH))
        invalidBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.MUSHROOM))
        invalidBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.OCEAN))

        for (biome in ForgeRegistries.BIOMES) {
            if (!invalidBiomes.contains(biome)) {
                // placement
                biome.func_235063_a_(DUNGEON.func_236391_a_(IFeatureConfig.NO_FEATURE_CONFIG))
            }
        }
    }
}