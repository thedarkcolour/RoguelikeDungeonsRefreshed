package thedarkcolour.roguelikedungeons.dungeon

import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.dungeon.level.DungeonLevel
import thedarkcolour.roguelikedungeons.dungeon.level.LevelSettings
import thedarkcolour.roguelikedungeons.dungeon.segment.ISegment
import thedarkcolour.roguelikedungeons.dungeon.shape.ShapeType
import thedarkcolour.roguelikedungeons.dungeon.theme.AIR_PLACER
import thedarkcolour.roguelikedungeons.dungeon.theme.Theme
import thedarkcolour.roguelikedungeons.dungeon.theme.placer.JumbleBlockPlacer
import java.util.*
import kotlin.collections.ArrayList

class DungeonTunnel(override val start: BlockPos, override val end: BlockPos) : ICollidable, Iterable<BlockPos> {
    val segments = ArrayList<ISegment>()
    // store iterator instead of giant list of block positions
    private val tunnel = ShapeType.SOLID_RECTANGLE.get(start, end)
    private lateinit var bounds: BoundingBox

    /**
     * Returns an iterator over the elements of this object.
     */
    override fun iterator(): Iterator<BlockPos> {
        return tunnel.iterator()
    }

    fun encase(worldIn: IWorld, rand: Random, theme: Theme) {
        val direction = start.directionTo(end)
        val start = start.toMutablePos()
        val end = end.toMutablePos()

        ShapeType.SOLID_RECTANGLE.fill(
            worldIn,
            rand,
            start.move(leftDirection(direction), 3).move(0, 3, 0),
            end.move(rightDirection(direction), 3).move(0, -3, 0),
            theme.primary.wall
        )
    }

    fun construct(worldIn: IWorld, rand: Random, settings: LevelSettings) {
        val air = AIR_PLACER

        val walls = settings.theme.primary.wall
        val floors = settings.theme.primary.floor
        val bridge = JumbleBlockPlacer(floors, air)

        val s = start.toMutablePos().move(Direction.NORTH).move(Direction.EAST)
        val e = end.toMutablePos().move(Direction.SOUTH).move(Direction.WEST).move(0, 2, 0)

        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, s, e, air)
        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, s.move(1, -1, -1), e.move(-1, 1, 1), walls, fillAir = false, replaceSolid = true)
        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, s.setPos(start).move(1, -1, -1), e.setPos(start).move(-1, 1, 1), floors, fillAir = false, replaceSolid = true)
        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, s, e, bridge, fillAir = true, replaceSolid = false)

        val direction = start.directionTo(end)
        val location = end.toMutablePos().move(direction, 1)

        val start = location.toMutablePos()
        val adjs = adjacentDirections(direction)
        start.move(adjs[0], 2)
        start.move(0, 2, 0)
        val end = location.toMutablePos()
        end.move(adjs[1], 2)
        end.move(0, -2, 0)

        ShapeType.SOLID_RECTANGLE.fill(worldIn, rand, start, end, walls, fillAir = false, replaceSolid = true)
    }

    fun getEnds(): Array<BlockPos> {
        return arrayOf(start, end)
    }

    fun generateSegments(worldIn: IWorld, rand: Random, level: DungeonLevel) {
        val settings = level.settings
        val segmentGen = settings.segments

        for (pos in this) {
            segments.addAll(segmentGen.generate(worldIn, rand, level, start.directionTo(end), pos))
        }
    }

    // cache the value because it will never change
    override fun getBounds(): BoundingBox {
        return if (::bounds.isInitialized) {
            bounds
        } else {
            val dir = start.directionTo(end)
            val start = start.toMutablePos()
            val end = start.toMutablePos()
            start.move(leftDirection(dir), 2)
            start.move(0, 3, 0)
            end.move(leftDirection(dir), 2)
            end.move(0, -1, 0)

            bounds = BoundingBox(start, end)
            bounds
        }
    }

    override fun collidesWith(other: ICollidable): Boolean {
        return getBounds().collidesWith(other)
    }
}