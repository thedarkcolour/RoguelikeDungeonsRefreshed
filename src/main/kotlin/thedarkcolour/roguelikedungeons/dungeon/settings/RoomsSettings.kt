package thedarkcolour.roguelikedungeons.dungeon.settings

import net.minecraft.util.ResourceLocation
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.RoguelikeDungeons
import thedarkcolour.roguelikedungeons.dungeon.DungeonFactory
import thedarkcolour.roguelikedungeons.dungeon.room.DungeonRoomType

@Complete
@Suppress("ReplacePutWithAssignment")
class RoomsSettings : DungeonSettings() {
    override val id = ResourceLocation(RoguelikeDungeons.ID, "rooms")

    override fun apply(settings: DungeonSettings) {
        for (i in 0 until 5) {
            val factory = DungeonFactory(DungeonRoomType.CORNER)

            when (i) {
                0 -> {
                    factory.addSingle(DungeonRoomType.CAKE)
                    factory.addSingle(DungeonRoomType.FIRE)
                    factory.addRandom(DungeonRoomType.BRICK, 4)
                    factory.addRandom(DungeonRoomType.CORNER, 2)
                }
                1 -> {
                    factory.addSingle(DungeonRoomType.PIT)
                    factory.addRandom(DungeonRoomType.CORNER, 10)
                    factory.addRandom(DungeonRoomType.BRICK, 3)
                }
                2 -> {
                    factory.addSingle(DungeonRoomType.OSSUARY)
                    factory.addSingle(DungeonRoomType.CRYPT)
                    factory.addSingle(DungeonRoomType.CREEPER)
                    factory.addSingle(DungeonRoomType.FIRE)
                    factory.addSingle(DungeonRoomType.SPIDER)
                    factory.addSingle(DungeonRoomType.PRISON)
                    factory.addRandom(DungeonRoomType.CRYPT, 5)
                    factory.addRandom(DungeonRoomType.CORNER, 5)
                    factory.addRandom(DungeonRoomType.BRICK, 3)
                }
                3 -> {
                    factory.addSingle(DungeonRoomType.OSSUARY)
                    factory.addSingle(DungeonRoomType.ENDER)
                    factory.addSingle(DungeonRoomType.CRYPT)
                    factory.addRandom(DungeonRoomType.PRISON, 3)
                    factory.addRandom(DungeonRoomType.SLIME, 5)
                    factory.addRandom(DungeonRoomType.CREEPER, 1)
                    factory.addRandom(DungeonRoomType.SPIDER, 1)
                    factory.addRandom(DungeonRoomType.PIT, 1)
                }
                4 -> {
                    factory.addSingle(DungeonRoomType.OBSIDIAN)
                    factory.addSingle(DungeonRoomType.BLAZE)
                    factory.addSingle(DungeonRoomType.PRISON)
                    factory.addSingle(DungeonRoomType.DARK_HALL)
                    factory.addSingle(DungeonRoomType.NETHER_FORT)
                    factory.addSingle(DungeonRoomType.SLIME, 10)
                    factory.addSingle(DungeonRoomType.BLAZE, 3)
                    factory.addSingle(DungeonRoomType.NETHER, 3)
                    factory.addSingle(DungeonRoomType.SPIDER, 2)
                }
            }

            val level = settings.getDungeonLevel(i)
            level.rooms = factory
        }
    }
}