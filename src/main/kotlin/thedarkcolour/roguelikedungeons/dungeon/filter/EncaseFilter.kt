package thedarkcolour.roguelikedungeons.dungeon.filter

import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.ICollidable
import thedarkcolour.roguelikedungeons.dungeon.shape.ShapeType
import thedarkcolour.roguelikedungeons.dungeon.theme.Theme
import java.util.*

@Complete
class EncaseFilter : IFilter {
    override fun apply(worldIn: IWorld, rand: Random, theme: Theme, collidable: ICollidable) {
        ShapeType.SOLID_RECTANGLE.get(collidable).fill(worldIn, rand, theme.primary.wall)
    }
}