package thedarkcolour.roguelikedungeons.enemy.profile

import net.minecraft.world.World
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.enemy.ModifiableEnemy
import thedarkcolour.roguelikedungeons.loot.canEnchant
import thedarkcolour.roguelikedungeons.loot.getRandomTool
import java.util.*

@Complete
class BabyProfile : IEnemyProfile {
    override fun addEquipment(worldIn: World, rand: Random, level: Int, enemy: ModifiableEnemy) {
        enemy.setChild(true)

        if (rand.nextBoolean()) {
            EnemyProfileType.VILLAGER.addEquipment(worldIn, rand, level, enemy)
        }

        val weapon = getRandomTool(rand, level, canEnchant(worldIn.difficulty, rand, level))
        enemy.setWeapon(weapon)
    }
}