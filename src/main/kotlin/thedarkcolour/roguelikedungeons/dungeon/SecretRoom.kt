package thedarkcolour.roguelikedungeons.dungeon

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.level.LevelSettings
import thedarkcolour.roguelikedungeons.dungeon.room.DungeonRoomType
import thedarkcolour.roguelikedungeons.dungeon.room.IDungeonRoom
import thedarkcolour.roguelikedungeons.dungeon.shape.ShapeType
import thedarkcolour.roguelikedungeons.dungeon.theme.AIR_PLACER
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

@Complete
data class SecretRoom(val type: DungeonRoomType, var count: Int) {
    private var prototype = type.get()

    fun add(count: Int) {
        this.count = count
    }

    fun generateRoom(worldIn: DungeonWorldIn, rand: Random, settings: LevelSettings, direction: Direction, pos: BlockPos): IDungeonRoom? {
        if (!isValid(worldIn, direction, pos)) return null

        val size = prototype.size

        val start = pos.toMutablePos()
        val end = pos.toMutablePos()
        start.move(leftDirection(direction))
        start.move(0, -1, 0)
        start.move(direction, 2)
        end.move(rightDirection(direction))
        end.move(direction, size + 5)
        end.move(0, 2, 0)

        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, settings.theme.primary.wall, fillAir = false, replaceSolid = true)

        start.setPos(pos)
        end.setPos(pos)
        end.move(direction, size + 5)
        end.move(0, 1, 0)

        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, AIR_PLACER, fillAir = false, replaceSolid = true)

        end.move(0, -1, 0)
        prototype.generate(worldIn, rand, settings, arrayOf(direction), end)
        count -= 1

        val generated = prototype
        prototype = type.get()

        return generated
    }

    fun isValid(worldIn: IWorld, direction: Direction, pos: BlockPos): Boolean {
        if (count <= 0) return false
        val cursor = pos.toMutablePos()
        cursor.move(direction, prototype.size + 5)

        return prototype.isValidLocation(worldIn, direction, cursor)
    }
}