package thedarkcolour.roguelikedungeons.dungeon.settings

import net.minecraft.util.ResourceLocation
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.RoguelikeDungeons
import thedarkcolour.roguelikedungeons.dungeon.level.LevelGeneratorType

@Complete
@Suppress("ReplacePutWithAssignment")
class LayoutSettings : DungeonSettings() {
    override val id = ResourceLocation(RoguelikeDungeons.ID, "layout")

    override fun apply(settings: DungeonSettings) {
        val numRooms = intArrayOf(10, 15, 15, 20, 15)
        val range    = intArrayOf(50, 50, 80, 70, 50)
        val scatter  = intArrayOf(15, 15, 17, 12, 15)

        val generator = arrayOf(
            LevelGeneratorType.CLASSIC,
            LevelGeneratorType.CLASSIC,
            LevelGeneratorType.MINIMUM_SPANNING_TREE,
            LevelGeneratorType.CLASSIC,
            LevelGeneratorType.CLASSIC,
        )

        for (i in 0 until 5) {
            val level = settings.getDungeonLevel(i)
            level.numRooms = numRooms[i]
            level.range = range[i]
            level.scatter = scatter[i]
            level.generator = generator[i]
        }
    }
}