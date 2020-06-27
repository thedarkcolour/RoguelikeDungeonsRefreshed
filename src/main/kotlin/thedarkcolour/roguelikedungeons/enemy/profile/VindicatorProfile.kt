package thedarkcolour.roguelikedungeons.enemy.profile

import net.minecraft.entity.EntityType
import net.minecraft.world.World
import thedarkcolour.roguelikedungeons.enemy.ModifiableEnemy
import thedarkcolour.roguelikedungeons.loot.Quality
import thedarkcolour.roguelikedungeons.loot.getSpecialAxe
import java.util.*

class VindicatorProfile : IEnemyProfile {
    override fun addEquipment(worldIn: World, rand: Random, level: Int, enemy: ModifiableEnemy) {
        enemy.setEntityType(EntityType.VINDICATOR)
        enemy.setWeapon(getSpecialAxe(rand, Quality.getWeaponQuality(rand, level)))
    }
}