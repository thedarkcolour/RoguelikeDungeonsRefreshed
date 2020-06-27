package thedarkcolour.roguelikedungeons.dungeon

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.dungeon.level.LevelSettings
import thedarkcolour.roguelikedungeons.dungeon.room.DungeonRoomType
import thedarkcolour.roguelikedungeons.dungeon.room.IDungeonRoom
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

class SecretFactory {
    val secrets = hashMapOf<DungeonRoomType, SecretRoom>()

    fun addRoom(type: DungeonRoomType, count: Int = 1) {
        val secret: SecretRoom

        if (secrets.containsKey(type)) {
            secret = secrets[type]!!
            secret.add(count)
        } else {
            secret = SecretRoom(type, count)
            secrets[type] = secret
        }
    }

    fun generateRoom(worldIn: DungeonWorldIn, rand: Random, settings: LevelSettings, direction: Direction, pos: BlockPos): IDungeonRoom? {
        for (room in secrets.values) {
            val generated = room.generateRoom(worldIn, rand, settings, direction, pos)

            if (generated != null) {
                return generated
            }
        }

        return null
    }
}