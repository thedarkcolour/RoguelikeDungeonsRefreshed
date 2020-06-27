package thedarkcolour.roguelikedungeons.dungeon.filter

import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.dungeon.ICollidable
import thedarkcolour.roguelikedungeons.dungeon.generateVines
import thedarkcolour.roguelikedungeons.dungeon.shape.ShapeType
import thedarkcolour.roguelikedungeons.dungeon.theme.Theme
import java.util.*

class VineFilter : IFilter {
    override fun apply(worldIn: IWorld, rand: Random, theme: Theme, collidable: ICollidable) {
        for (pos in ShapeType.SOLID_RECTANGLE.get(collidable)) {
            if (rand.nextInt(10) == 0) {
                generateVines(worldIn, pos)
            }
        }
    }
}