package thedarkcolour.roguelikedungeons.enemy.profile

import net.minecraft.entity.EntityType
import net.minecraft.world.World
import thedarkcolour.roguelikedungeons.enemy.ModifiableEnemy
import thedarkcolour.roguelikedungeons.loot.canEnchant
import thedarkcolour.roguelikedungeons.loot.getRandomWeapon
import java.util.*

class VillagerProfile : IEnemyProfile {
    override fun addEquipment(worldIn: World, rand: Random, level: Int, enemy: ModifiableEnemy) {
        enemy.setEntityType(EntityType.ZOMBIE_VILLAGER)

        val weapon = getRandomWeapon(rand, level, canEnchant(worldIn.difficulty, rand, level))
    }
}