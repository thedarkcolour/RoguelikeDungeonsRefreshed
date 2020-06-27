package thedarkcolour.roguelikedungeons.dungeon.filter

import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.ICollidable
import thedarkcolour.roguelikedungeons.dungeon.SEA_LANTERN
import thedarkcolour.roguelikedungeons.dungeon.shape.ShapeType
import thedarkcolour.roguelikedungeons.dungeon.theme.Theme
import thedarkcolour.roguelikedungeons.dungeon.theme.placer.SimpleBlockPlacer
import java.util.*

@Complete
class WireframeFilter : IFilter {
    override fun apply(worldIn: IWorld, rand: Random, theme: Theme, collidable: ICollidable) {
        val start = collidable.start.add(0, 100, 0)
        val end = collidable.end.add(0, 100, 0)

        val shape = ShapeType.WIREFRAME_RECTANGLE.get(start, end)
        val block =
            SimpleBlockPlacer(SEA_LANTERN)

        shape.fill(worldIn, rand, block)
    }
}