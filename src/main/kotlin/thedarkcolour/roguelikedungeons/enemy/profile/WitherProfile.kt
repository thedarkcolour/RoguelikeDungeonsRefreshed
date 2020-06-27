package thedarkcolour.roguelikedungeons.enemy.profile

import net.minecraft.entity.EntityType
import net.minecraft.world.World
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.enemy.ModifiableEnemy
import thedarkcolour.roguelikedungeons.loot.canEnchant
import thedarkcolour.roguelikedungeons.loot.getRandomSword
import java.util.*

@Complete
class WitherProfile : IEnemyProfile {
    override fun addEquipment(worldIn: World, rand: Random, level: Int, enemy: ModifiableEnemy) {
        enemy.setEntityType(EntityType.WITHER_SKELETON)
        enemy.setWeapon(getRandomSword(rand, level, canEnchant(worldIn.difficulty, rand, level)))

        EnemyProfileType.TALL_MOB.addEquipment(worldIn, rand, level, enemy)
    }
}