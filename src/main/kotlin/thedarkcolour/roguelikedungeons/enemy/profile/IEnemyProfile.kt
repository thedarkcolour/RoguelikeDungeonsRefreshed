package thedarkcolour.roguelikedungeons.enemy.profile

import net.minecraft.world.World
import thedarkcolour.roguelikedungeons.enemy.ModifiableEnemy
import java.util.*

interface IEnemyProfile {
    fun addEquipment(worldIn: World, rand: Random, level: Int, enemy: ModifiableEnemy)
}