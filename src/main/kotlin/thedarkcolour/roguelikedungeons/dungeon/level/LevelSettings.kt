package thedarkcolour.roguelikedungeons.dungeon.level

import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.dungeon.DungeonFactory
import thedarkcolour.roguelikedungeons.dungeon.SecretFactory
import thedarkcolour.roguelikedungeons.dungeon.filter.FilterType
import thedarkcolour.roguelikedungeons.dungeon.getDungeonLevel
import thedarkcolour.roguelikedungeons.dungeon.room.DungeonRoomType
import thedarkcolour.roguelikedungeons.dungeon.segment.SegmentType
import thedarkcolour.roguelikedungeons.dungeon.segment.gen.SegmentGenerator
import thedarkcolour.roguelikedungeons.dungeon.spawner.SpawnerSettings
import thedarkcolour.roguelikedungeons.dungeon.theme.Themes
import java.util.*

// todo move assignments into secondary constructor
class LevelSettings {
    private var levelDifficulty = -1
    var numRooms = NUM_ROOMS
    var range = LEVEL_RANGE
    var scatter = SCATTER
    var rooms = DungeonFactory(DungeonRoomType.CORNER)
    var secrets = SecretFactory()
    var theme = Themes.OAK
    var segments = SegmentGenerator(SegmentType.ARCH)
    val spawners = SpawnerSettings()
    var generator = LevelGeneratorType.CLASSIC
    val filters = arrayListOf<FilterType>()

    fun createLevelGenerator(rand: Random): ILevelGenerator {
        return generator.get(rand, this)
    }

    fun getDifficulty(pos: BlockPos): Int {
        return if (levelDifficulty == -1) {
            getDungeonLevel(pos)
        } else levelDifficulty
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LevelSettings

        if (rooms != other.rooms) return false
        if (secrets != other.secrets) return false
        if (generator != other.generator) return false

        return true
    }

    override fun hashCode(): Int {
        var result = rooms.hashCode()
        result = 31 * result + secrets.hashCode()
        result = 31 * result + generator.hashCode()
        return result
    }

    companion object {
        private const val NUM_ROOMS = 12
        private const val LEVEL_RANGE = 80
        private const val SCATTER = 12
    }
}