package thedarkcolour.roguelikedungeons.structure

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.particles.IParticleData
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.Direction
import net.minecraft.util.SoundCategory
import net.minecraft.util.SoundEvent
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.*
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeManager
import net.minecraft.world.border.WorldBorder
import net.minecraft.world.chunk.AbstractChunkProvider
import net.minecraft.world.chunk.ChunkStatus
import net.minecraft.world.chunk.IChunk
import net.minecraft.world.gen.Heightmap
import net.minecraft.world.lighting.WorldLightManager
import net.minecraft.world.storage.IWorldInfo
import thedarkcolour.roguelikedungeons.loot.treasure.TreasureChest
import thedarkcolour.roguelikedungeons.loot.treasure.TreasureManager
import java.util.*
import java.util.function.Predicate

/**
 * Allows access of the loot of the current dungeon being generated.
 *
 * Wraps the [IWorld] passed to it and delegates
 * every function in `IWorld` to it.
 */
class DungeonWorldIn(private val world: ISeedReader) : ISeedReader {
    val stats = Object2IntOpenHashMap<Block>()
    val treasure = TreasureManager()

    fun addChest(chest: TreasureChest) {
        treasure.add(chest)
    }

    override fun getSkylightSubtracted(): Int {
        return world.skylightSubtracted
    }

    override fun getPendingBlockTicks(): ITickList<Block> {
        return world.pendingBlockTicks
    }

    // removeBlockState?
    override fun func_241212_a_(p0: BlockPos, p1: Boolean, p2: Entity?, p3: Int): Boolean {
        return world.func_241212_a_(p0, p1, p2, p3)
    }

    override fun getTileEntity(pos: BlockPos): TileEntity? {
        return world.getTileEntity(pos)
    }

    override fun removeBlock(pos: BlockPos, isMoving: Boolean): Boolean {
        return world.removeBlock(pos, isMoving)
    }

    override fun getLightingProvider(): WorldLightManager {
        return world.lightingProvider
    }

    // getDimensionType
    override fun func_230315_m_(): DimensionType {
        return world.func_230315_m_()
    }

    override fun getPendingFluidTicks(): ITickList<Fluid> {
        return world.pendingFluidTicks
    }

    override fun getHeight(heightmapType: Heightmap.Type, x: Int, z: Int): Int {
        return world.height
    }

    override fun getWorldBorder(): WorldBorder {
        return world.worldBorder
    }

    override fun hasBlockState(pos: BlockPos, predicate: Predicate<BlockState>): Boolean {
        return world.hasBlockState(pos, predicate)
    }

    override fun getDifficultyForLocation(pos: BlockPos): DifficultyInstance {
        return world.getDifficultyForLocation(pos)
    }

    override fun <T : Entity?> getEntitiesWithinAABB(
        entityClass: Class<out T>,
        bounds: AxisAlignedBB,
        filter: Predicate<in T>?
    ): MutableList<T> {
        return world.getEntitiesWithinAABB(entityClass, bounds, filter)
    }

    override fun getChunkProvider(): AbstractChunkProvider {
        return world.chunkProvider
    }

    override fun getGeneratorStoredBiome(x: Int, y: Int, z: Int): Biome {
        return world.getGeneratorStoredBiome(x, y, z)
    }

    /**
     * Plays a sound. On the server, the sound is broadcast to all nearby *except* the given player. On the
     * client, the sound only plays if the given player is the client player. Thus, this method is intended to be called
     * from code running on both sides. The client plays it locally and the server plays it for everyone else.
     */
    override fun playSound(
        player: PlayerEntity?,
        pos: BlockPos,
        soundIn: SoundEvent,
        category: SoundCategory,
        volume: Float,
        pitch: Float
    ) {
        return world.playSound(player, pos, soundIn, category, volume, pitch)
    }

    override fun getSeaLevel(): Int {
        return world.seaLevel
    }

    /**
     * Returns the world's WorldInfo object
     */
    override fun getWorldInfo(): IWorldInfo {
        return world.worldInfo
    }

    override fun getBiomeAccess(): BiomeManager {
        return world.biomeAccess
    }

    override fun getBlockState(pos: BlockPos): BlockState {
        return world.getBlockState(pos)
    }

    /**
     * Sets a block state into this world.Flags are as follows:
     * 1 will cause a block update.
     * 2 will send the change to clients.
     * 4 will prevent the block from being re-rendered.
     * 8 will force any re-renders to run on the main thread instead
     * 16 will prevent neighbor reactions (e.g. fences connecting, observers pulsing).
     * 32 will prevent neighbor reactions from spawning drops.
     * 64 will signify the block is being moved.
     * Flags can be OR-ed
     */
    override fun setBlockState(pos: BlockPos, newState: BlockState, flags: Int): Boolean {
        return world.setBlockState(pos, newState, flags)
    }

    /**
     * Gets all entities within the specified AABB excluding the one passed into it.
     */
    override fun getEntitiesInAABBexcluding(
        entityIn: Entity?,
        boundingBox: AxisAlignedBB,
        predicate: Predicate<in Entity>?
    ): MutableList<Entity> {
        return world.getEntitiesInAABBexcluding(entityIn, boundingBox, predicate)
    }

    // setBlockState with flags and a redstone related thing
    override fun func_241211_a_(p0: BlockPos, p1: BlockState, p2: Int, p3: Int): Boolean {
        TODO("not implemented")
    }

    /**
     * gets the random world seed
     */
    override fun getSeed(): Long {
        return world.seed
    }

    override fun getPlayers(): MutableList<out PlayerEntity> {
        return world.players
    }

    override fun getWorld(): World {
        return world.world
    }

    override fun getChunk(x: Int, z: Int, requiredStatus: ChunkStatus, nonnull: Boolean): IChunk? {
        return world.getChunk(x, z, requiredStatus, nonnull)
    }

    override fun addParticle(
        particleData: IParticleData,
        x: Double,
        y: Double,
        z: Double,
        xSpeed: Double,
        ySpeed: Double,
        zSpeed: Double
    ) {
        world.addParticle(particleData, x, y, z, xSpeed, ySpeed, zSpeed)
    }

    override fun breakBlock(pos: BlockPos, dropItems: Boolean, entityLootParam: Entity?): Boolean {
        return world.breakBlock(pos, dropItems, entityLootParam)
    }

    override fun func_230487_a_(p0: Direction, p1: Boolean): Float {
        return world.func_230487_a_(p0, p1)
    }

    override fun playEvent(player: PlayerEntity?, type: Int, pos: BlockPos, data: Int) {
        return world.playEvent(player, type, pos, data)
    }

    override fun getRandom(): Random {
        return world.random
    }

    override fun isRemote(): Boolean {
        return world.isRemote
    }

    // they fucking did it
    // they got rid of the interface
    override fun getFluidState(pos: BlockPos): FluidState {
        return world.getFluidState(pos)
    }
}