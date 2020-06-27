package thedarkcolour.roguelikedungeons.dungeon.settings

import net.minecraft.util.ResourceLocation
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.RoguelikeDungeons
import thedarkcolour.roguelikedungeons.dungeon.segment.SegmentType
import thedarkcolour.roguelikedungeons.dungeon.segment.gen.SegmentGenerator

@Complete
@Suppress("ReplacePutWithAssignment")
class SegmentsSettings : DungeonSettings() {
    override val id = ResourceLocation(RoguelikeDungeons.ID, "segments")

    override fun apply(settings: DungeonSettings) {
        for (i in 0 until 5) {
            val segments: SegmentGenerator

            when (i) {
                0 -> {
                    segments = SegmentGenerator(SegmentType.ARCH)
                    segments.add(SegmentType.DOOR, 8)
                    segments.add(SegmentType.LAMP, 2)
                    segments.add(SegmentType.FLOWERS, 2)
                    segments.add(SegmentType.FIREPLACE, 2)
                    segments.add(SegmentType.WHEAT, 1)
                }
                1 -> {
                    segments = SegmentGenerator(SegmentType.ARCH)
                    segments.add(SegmentType.INSET, 2)
                    segments.add(SegmentType.SHELF, 1)
                    segments.add(SegmentType.FIREPLACE, 1)
                    segments.add(SegmentType.WALL, 6)
                    segments.add(SegmentType.BOOKS, 1)
                    segments.add(SegmentType.CHEST, 1)
                    segments.add(SegmentType.SPAWNER, 1)
                }
                2 -> {
                    segments = SegmentGenerator(SegmentType.ARCH)
                    segments.add(SegmentType.SHELF, 4)
                    segments.add(SegmentType.INSET, 4)
                    segments.add(SegmentType.WALL, 4)
                    segments.add(SegmentType.SKULL, 1)
                    segments.add(SegmentType.TOMB, 2)
                    segments.add(SegmentType.CELL, 1)
                    segments.add(SegmentType.CHEST, 1)
                    segments.add(SegmentType.SPAWNER, 1)
                    segments.add(SegmentType.FIREPLACE, 1)
                }
                3 -> {
                    segments = SegmentGenerator(SegmentType.MOSSY_ARCH)
                    segments.add(SegmentType.SHELF, 3)
                    segments.add(SegmentType.INSET, 3)
                    segments.add(SegmentType.SILVERFISH, 1)
                    segments.add(SegmentType.ARROW, 2)
                    segments.add(SegmentType.SKULL, 1)
                    segments.add(SegmentType.MUSHROOM, 2)
                    segments.add(SegmentType.CHEST, 1)
                    segments.add(SegmentType.CELL, 2)
                    segments.add(SegmentType.SPAWNER, 1)
                    segments.add(SegmentType.TOMB, 1)
                }
                4 -> {
                    segments = SegmentGenerator(SegmentType.NETHER_ARCH)
                    segments.add(SegmentType.SPAWNER, 10)
                    segments.add(SegmentType.NETHER_LAVA, 3)
                    segments.add(SegmentType.NETHER_STRIPE, 3)
                    segments.add(SegmentType.NETHER_WART, 3)
                    segments.add(SegmentType.SKULL, 3)
                    segments.add(SegmentType.CHEST, 1)
                }
                else -> throw IllegalStateException("For-loop didn't work properly")
            }

            val level = settings.getDungeonLevel(i)
            level.segments = segments
        }
    }
}
