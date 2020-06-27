package thedarkcolour.roguelikedungeons.enemy.profile

import net.minecraft.entity.EntityType
import net.minecraft.world.World
import thedarkcolour.roguelikedungeons.enemy.ModifiableEnemy
import thedarkcolour.roguelikedungeons.loot.getRandomSword
import java.util.*

class PigmanProfile : IEnemyProfile {
    override fun addEquipment(worldIn: World, rand: Random, level: Int, enemy: ModifiableEnemy) {
        enemy.setEntityType(EntityType.ZOMBIE_PIGMAN)

        enemy.setWeapon(getRandomSword(rand, level, true))
        enemy.addShield(rand)

        EnemyProfileType.TALL_MOB.addEquipment(worldIn, rand, level, enemy)
    }
}