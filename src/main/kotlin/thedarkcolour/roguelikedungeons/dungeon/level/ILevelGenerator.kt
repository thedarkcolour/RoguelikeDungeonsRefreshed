package thedarkcolour.roguelikedungeons.dungeon.level

import net.minecraft.util.math.BlockPos

interface ILevelGenerator {
    fun generate(pos: BlockPos)

    val layout: LevelLayout
}