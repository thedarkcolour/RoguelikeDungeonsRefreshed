package thedarkcolour.roguelikedungeons.loot

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.loot.weight.IWeighted
import thedarkcolour.roguelikedungeons.loot.weight.WeightedEntry
import thedarkcolour.roguelikedungeons.loot.weight.WeightedPool
import thedarkcolour.roguelikedungeons.util.checkRange
import java.util.*

@Complete
enum class Quality {
    WOOD, STONE, IRON, GOLD, DIAMOND;

    companion object {
        private val ARMOR_QUALITIES = Int2ObjectOpenHashMap<IWeighted<Quality>>()
        private val WEAPON_QUALITIES = Int2ObjectOpenHashMap<IWeighted<Quality>>()
        private val TOOL_QUALITIES = Int2ObjectOpenHashMap<IWeighted<Quality>>()

        // @formatter:off
        init {
            for (i in 0..4) {
                val armor = WeightedPool<Quality>()
                val weapon = WeightedPool<Quality>()
                val tool = WeightedPool<Quality>()

                when (i) {
                    0 -> {
                        armor.add(WeightedEntry(WOOD, 250))
                        armor.add(WeightedEntry(STONE, 50))
                        armor.add(WeightedEntry(IRON, 20))
                        armor.add(WeightedEntry(GOLD, 3))
                        armor.add(WeightedEntry(DIAMOND, 1))

                        weapon.add(WeightedEntry(WOOD, 200))
                        weapon.add(WeightedEntry(STONE, 50))
                        weapon.add(WeightedEntry(IRON, 10))
                        weapon.add(WeightedEntry(GOLD, 3))
                        weapon.add(WeightedEntry(DIAMOND, 1))

                        tool.add(WeightedEntry(WOOD, 10))
                        tool.add(WeightedEntry(STONE, 20))
                        tool.add(WeightedEntry(IRON, 10))
                        tool.add(WeightedEntry(GOLD, 3))
                        tool.add(WeightedEntry(DIAMOND, 1))
                    }
                    1 -> {
                        armor.add(WeightedEntry(WOOD, 150))
                        armor.add(WeightedEntry(STONE, 30))
                        armor.add(WeightedEntry(IRON, 10))
                        armor.add(WeightedEntry(GOLD, 3))
                        armor.add(WeightedEntry(DIAMOND, 1))

                        weapon.add(WeightedEntry(WOOD, 100))
                        weapon.add(WeightedEntry(STONE, 30))
                        weapon.add(WeightedEntry(IRON, 10))
                        weapon.add(WeightedEntry(GOLD, 3))
                        weapon.add(WeightedEntry(DIAMOND, 1))

                        tool.add(WeightedEntry(WOOD, 2))
                        tool.add(WeightedEntry(STONE, 10))
                        tool.add(WeightedEntry(IRON, 10))
                        tool.add(WeightedEntry(GOLD, 3))
                        tool.add(WeightedEntry(DIAMOND, 1))
                    }
                    2 -> {
                        armor.add(WeightedEntry(WOOD, 50))
                        armor.add(WeightedEntry(STONE, 30))
                        armor.add(WeightedEntry(IRON, 20))
                        armor.add(WeightedEntry(GOLD, 3))
                        armor.add(WeightedEntry(DIAMOND, 1))

                        weapon.add(WeightedEntry(WOOD, 50))
                        weapon.add(WeightedEntry(STONE, 20))
                        weapon.add(WeightedEntry(IRON, 10))
                        weapon.add(WeightedEntry(GOLD, 3))
                        weapon.add(WeightedEntry(DIAMOND, 1))

                        tool.add(WeightedEntry(WOOD, 1))
                        tool.add(WeightedEntry(STONE, 5))
                        tool.add(WeightedEntry(IRON, 10))
                        tool.add(WeightedEntry(GOLD, 5))
                        tool.add(WeightedEntry(DIAMOND, 3))
                    }
                    3 -> {
                        armor.add(WeightedEntry(WOOD, 20))
                        armor.add(WeightedEntry(STONE, 10))
                        armor.add(WeightedEntry(IRON, 10))
                        armor.add(WeightedEntry(GOLD, 5))
                        armor.add(WeightedEntry(DIAMOND, 3))

                        weapon.add(WeightedEntry(WOOD, 1))
                        weapon.add(WeightedEntry(STONE, 3))
                        weapon.add(WeightedEntry(IRON, 5))
                        weapon.add(WeightedEntry(GOLD, 3))
                        weapon.add(WeightedEntry(DIAMOND, 1))

                        tool.add(WeightedEntry(WOOD, 1))
                        tool.add(WeightedEntry(STONE, 3))
                        tool.add(WeightedEntry(IRON, 10))
                        tool.add(WeightedEntry(GOLD, 5))
                        tool.add(WeightedEntry(DIAMOND, 5))
                    }
                    4 -> {
                        armor.add(WeightedEntry(WOOD, 2))
                        armor.add(WeightedEntry(STONE, 3))
                        armor.add(WeightedEntry(IRON, 10))
                        armor.add(WeightedEntry(GOLD, 3))
                        armor.add(WeightedEntry(DIAMOND, 3))

                        weapon.add(WeightedEntry(WOOD, 1))
                        weapon.add(WeightedEntry(STONE, 2))
                        weapon.add(WeightedEntry(IRON, 15))
                        weapon.add(WeightedEntry(GOLD, 5))
                        weapon.add(WeightedEntry(DIAMOND, 3))

                        tool.add(WeightedEntry(WOOD, 1))
                        tool.add(WeightedEntry(STONE, 2))
                        tool.add(WeightedEntry(IRON, 10))
                        tool.add(WeightedEntry(GOLD, 3))
                        tool.add(WeightedEntry(DIAMOND, 5))
                    }
                }

                ARMOR_QUALITIES[i] = armor
                WEAPON_QUALITIES[i] = weapon
                TOOL_QUALITIES[i] = tool
            }
        }
        // @formatter:on

        fun getArmorQuality(rand: Random, level: Int): Quality {
            checkRange(level, 0..4)

            return ARMOR_QUALITIES[level].get(rand)!!
        }

        fun getWeaponQuality(rand: Random, level: Int): Quality {
            checkRange(level, 0..4)

            return WEAPON_QUALITIES[level].get(rand)!!
        }

        fun getToolQuality(rand: Random, level: Int): Quality {
            checkRange(level, 0..4)

            return TOOL_QUALITIES[level].get(rand)!!
        }
    }
}