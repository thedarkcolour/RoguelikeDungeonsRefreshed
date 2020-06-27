package thedarkcolour.roguelikedungeons.loot.treasure

import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.dungeon.HORIZONTALS
import thedarkcolour.roguelikedungeons.dungeon.MutableBlockPos
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import thedarkcolour.roguelikedungeons.util.pickRandomly
import java.util.*
import kotlin.collections.ArrayList

fun generateChests(
    worldIn: DungeonWorldIn,
    rand: Random,
    numChests: Int,
    spaces: MutableList<BlockPos>,
    types: List<TreasureType>,
    level: Int
): List<TreasureChest> {
    val chests = ArrayList<TreasureChest>()

    spaces.shuffle(rand)

    var count = 0

    for (pos in spaces) {
        if (count == numChests) {
            return chests
        }

        if (isValidChestSpace(worldIn, pos)) {
            val chest = generateChest(worldIn, rand, pos, rand.pickRandomly(types), level)
            chests.add(chest)
            ++count
        }
    }

    return chests
}

fun isValidChestSpace(worldIn: IWorld, pos: BlockPos): Boolean {
    if (!worldIn.isAirBlock(pos)) {
        return false
    }

    val cursor = MutableBlockPos(pos.x, pos.y - 1, pos.z)

    if (!worldIn.getBlockState(cursor).isSolid) {
        return false
    }

    for (direction in HORIZONTALS) {
        cursor.setPos(pos)
        cursor.move(direction)

        if (worldIn.getBlockState(pos).block == Blocks.CHEST) {
            return false
        }
    }

    return true
}

fun generateChest(
    worldIn: DungeonWorldIn,
    rand: Random,
    pos: BlockPos,
    type: TreasureType,
    level: Int,
    trapped: Boolean = false
): TreasureChest {
    val chest = TreasureChest(type)
    return chest.place(worldIn, rand, pos, level, trapped)
}