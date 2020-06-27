package thedarkcolour.roguelikedungeons.dungeon.filter

import net.minecraft.world.IWorld
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.ICollidable
import thedarkcolour.roguelikedungeons.dungeon.theme.Theme
import java.util.*

@Complete
interface IFilter {
    fun apply(worldIn: IWorld, rand: Random, theme: Theme, collidable: ICollidable)
}