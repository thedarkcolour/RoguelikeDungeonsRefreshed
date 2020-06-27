package thedarkcolour.roguelikedungeons.enemy.profile

import net.minecraft.entity.EntityType
import net.minecraft.world.World
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.enemy.ModifiableEnemy
import java.util.*

@Complete
class WitchProfile : IEnemyProfile {
    override fun addEquipment(worldIn: World, rand: Random, level: Int, enemy: ModifiableEnemy) {
        enemy.setEntityType(EntityType.WITCH)
    }
}