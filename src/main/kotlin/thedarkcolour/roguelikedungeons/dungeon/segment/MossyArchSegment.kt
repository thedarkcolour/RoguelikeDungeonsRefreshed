package thedarkcolour.roguelikedungeons.dungeon.segment

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.dungeon.adjacentDirections
import thedarkcolour.roguelikedungeons.dungeon.generateVinesArea
import thedarkcolour.roguelikedungeons.dungeon.level.DungeonLevel
import thedarkcolour.roguelikedungeons.dungeon.shape.ShapeType
import thedarkcolour.roguelikedungeons.dungeon.theme.*
import thedarkcolour.roguelikedungeons.dungeon.toMutablePos
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

class MossyArchSegment : ISegment {
    private var spawnHoleSet = false

    override fun generateWall(worldIn: DungeonWorldIn, rand: Random, level: DungeonLevel, direction: Direction, theme: Theme, pos: BlockPos) {
        val stair = theme.primary.stairs
        stair.setOrientation(direction.opposite, true)

        val air = AIR_PLACER

        level.settings.secrets.generateRoom(worldIn, rand, level.settings, direction, pos)

        val cursor = pos.toMutablePos()
        cursor.move(direction, 2)
        air.place(worldIn, cursor)
        cursor.move(Direction.UP, 1)
        air.place(worldIn, cursor)
        cursor.move(Direction.UP, 1)
        stair.place(worldIn, cursor)

        for (orth in adjacentDirections(direction)) {
            cursor.setPos(pos)
            cursor.move(orth, 1)
            cursor.move(direction, 2)
            theme.secondary.pillar.place(worldIn, rand, cursor)
            cursor.move(Direction.UP, 1)
            theme.secondary.pillar.place(worldIn, rand, cursor)
            cursor.move(Direction.UP, 1)
            theme.secondary.wall.place(worldIn, rand, cursor)
            cursor.move(direction.opposite, 1)
            stair.place(worldIn, cursor)
        }

        cursor.setPos(pos)
        cursor.move(direction, 2)
        cursor.move(Direction.DOWN, 1)
        WATER_PLACER.place(worldIn, cursor)

        cursor.setPos(pos)
        cursor.move(Direction.UP, 3)
        cursor.move(direction, 1)
        VINE_PLACER.place(worldIn, cursor)

        if (!spawnHoleSet) {
            ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, pos.add(0, 2, 0), pos.add(0, 5, 0), air)
            generateVinesArea(worldIn, rand, pos.add(0, 3, 0), pos.add(0, 5, 0))
            if (!worldIn.isAirBlock(pos.add(0, 6, 0))) {
                WATER_PLACER.place(worldIn, pos.add(0, 7, 0))
            }
            spawnHoleSet = true
        }
    }
}