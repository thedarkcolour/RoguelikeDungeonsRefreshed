package thedarkcolour.roguelikedungeons.structure

import net.minecraft.util.SharedSeedRandom
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.ChunkPos
import net.minecraft.util.math.MutableBoundingBox
import net.minecraft.world.ISeedReader
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeManager
import net.minecraft.world.biome.provider.BiomeProvider
import net.minecraft.world.gen.ChunkGenerator
import net.minecraft.world.gen.feature.NoFeatureConfig
import net.minecraft.world.gen.feature.structure.Structure
import net.minecraft.world.gen.feature.structure.Structure.IStartFactory
import net.minecraft.world.gen.feature.structure.StructureManager
import net.minecraft.world.gen.feature.structure.StructureStart
import net.minecraft.world.gen.feature.template.TemplateManager
import net.minecraft.world.gen.settings.StructureSeparationSettings
import thedarkcolour.roguelikedungeons.config.Config
import thedarkcolour.roguelikedungeons.dungeon.DungeonGenerator
import thedarkcolour.roguelikedungeons.dungeon.settings.getSettings
import java.util.*

class DungeonStructure : Structure<NoFeatureConfig>(NoFeatureConfig.field_236558_a_) {
    override fun func_236392_a_(
        settings: StructureSeparationSettings,
        seed: Long,
        rand: SharedSeedRandom,
        chunkX: Int,
        chunkZ: Int,
    ): ChunkPos {
        val frequency = Config.spawnFrequency
        val max = (32 * frequency / 10).coerceAtLeast(8)
        val min = (8 * frequency / 10).coerceAtLeast(2)
        val i1 = if (chunkX < 0) chunkX - (max - 1) else chunkX
        val j1 = if (chunkZ < 0) chunkZ - (max - 1) else chunkZ
        var k1 = i1 / max
        var l1 = j1 / max

        rand.setLargeFeatureSeedWithSalt(seed, k1, l1, 10387312)
        k1 *= max
        l1 *= max
        k1 += rand.nextInt(max - min)
        l1 += rand.nextInt(max - min)

        return ChunkPos(k1, l1)
    }
/*
    override fun getStartPositionForPosition(
        chunkGenerator: ChunkGenerator,
        rand: Random,
        x: Int,
        z: Int,
        spacingOffsetsX: Int,
        spacingOffsetsZ: Int
    ): ChunkPos {
        val frequency = Config.spawnFrequency
        val max = (32 * frequency / 10).coerceAtLeast(8)
        val min = (8 * frequency / 10).coerceAtLeast(2)
        val k = x + max * spacingOffsetsX
        val l = z + max * spacingOffsetsZ
        val i1 = if (k < 0) k - (max - 1) else k
        val j1 = if (l < 0) l - (max - 1) else l
        var k1 = i1 / max
        var l1 = j1 / max

        (rand as SharedSeedRandom)
            .setLargeFeatureSeedWithSalt(chunkGenerator.seed, k1, l1, 10387312)
        k1 *= max
        l1 *= max
        k1 += rand.nextInt(max - min)
        l1 += rand.nextInt(max - min)

        return ChunkPos(k1, l1)
    }
*/
    override fun func_230363_a_(generator: ChunkGenerator, biomeProvider: BiomeProvider, seed: Long, rand: SharedSeedRandom, chunkX: Int, chunkZ: Int, biome: Biome, chunkPos: ChunkPos, config: NoFeatureConfig): Boolean {
        val pos = func_236392_a_(generator.func_235957_b_().func_236197_a_(this), seed, rand, chunkX, chunkZ)

        return if (chunkX == pos.x && chunkZ == pos.z && biome.hasStructure(this)) {
            val spawnChance = Config.spawnChance
            val rand1 = Random(Objects.hash(chunkX, chunkZ, 31).toLong())

            rand1.nextFloat() < spawnChance
        } else false
    }
/*
    override fun shouldStartAt(
        manager: BiomeManager,
        generator: ChunkGenerator,
        rand: Random,
        chunkX: Int,
        chunkZ: Int,
        biome: Biome
    ): Boolean {
        val pos = getStartPositionForPosition(generator, rand, chunkX, chunkZ, 0, 0)

        return if (chunkX == pos.x && chunkZ == pos.z && biome.hasStructure(this)) {
            val spawnChance = Config.spawnChance
            val rand1 = Random(Objects.hash(chunkX, chunkZ, 31).toLong())

            rand1.nextFloat() < spawnChance
        } else false
    }
*/
    override fun getStartFactory(): IStartFactory<NoFeatureConfig> {
        return IStartFactory(::Start)
    }

    override fun getStructureName(): String {
        return "RoguelikeDungeon"
    }

    class Start(
        structure: Structure<NoFeatureConfig>,
        chunkX: Int,
        chunkZ: Int,
        bounds: MutableBoundingBox,
        refs: Int,
        seed: Long
    ) : StructureStart<NoFeatureConfig>(structure, chunkX, chunkZ, bounds, refs, seed) {
        override fun func_230364_a_(p_230364_1_: ChunkGenerator, p_230364_2_: TemplateManager, p_230364_3_: Int, p_230364_4_: Int, p_230364_5_: Biome, p_230364_6_: NoFeatureConfig) {

        }

        /**
         * currently only defined for Villages, returns true if Village has more than 2 non-road components
         */
        override fun isValid(): Boolean {
            // hack avoids using components
            return true
        }

        override fun func_230366_a_(
            world: ISeedReader,
            manager: StructureManager,
            generator: ChunkGenerator,
            rand: Random,
            bounds: MutableBoundingBox,
            chunkPos: ChunkPos,
        ) {
            val pos = BlockPos(chunkPos.x * 16 + 4, 50, chunkPos.z * 16 + 4)
            val worldIn = DungeonWorldIn(world)

            val settings = getSettings(worldIn, this.rand, pos)?.applyInheritance() ?: return
            val dungeon = DungeonGenerator(worldIn, settings, pos)

            dungeon.generate()

            recalculateStructureSize()
        }

        /*
        private fun getNearbyPosition(rand: Random, pos: BlockPos): BlockPos {
            val distance = 40 + rand.nextInt(100 - 40)
            val angle = rand.nextDouble() * 2 * PI

            val xOffset = cos(angle) * distance
            val zOffset = sin(angle) * distance

            return pos.add(xOffset, 0.0, zOffset)
        }

        private fun isValidPosition(worldIn: DungeonWorld, rand: Random, column: BlockPos) {}

        private fun generate(worldIn: DungeonWorld) {
            val dungeon = structure as DungeonStructure

            val levels = Array(5) { i ->
                val levelSettings = dungeon.getLevelSettings(i)
                val level = DungeonLevel(worldIn, rand, levelSettings, start)

                level
            }
        }
        */
    }
}