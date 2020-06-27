package thedarkcolour.roguelikedungeons.dungeon.level.mst

import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.dungeon.HORIZONTALS
import thedarkcolour.roguelikedungeons.dungeon.MutableBlockPos
import thedarkcolour.roguelikedungeons.dungeon.distanceHorizontal
import thedarkcolour.roguelikedungeons.dungeon.toMutablePos
import thedarkcolour.roguelikedungeons.util.pickRandomly
import java.util.*
import kotlin.math.floor

class MSTPoint(val adjusted: BlockPos, rand: Random) {
    val pos: MutableBlockPos = adjusted.toMutablePos().move(rand.pickRandomly(HORIZONTALS))
    var rank = 0
    var parent = this

    fun distance(other: MSTPoint): Double {
        return adjusted.distanceHorizontal(other.adjusted)
    }

    fun multiply(multiplier: Double) {
        val x = floor(pos.x.toDouble() * multiplier)
        val y = pos.y.toDouble()
        val z = floor(pos.z.toDouble() * multiplier)

        pos.setPos(x, y, z)
    }
}