package thedarkcolour.roguelikedungeons.enemy.profile

import net.minecraft.world.World
import thedarkcolour.roguelikedungeons.enemy.ModifiableEnemy
import java.util.*

class SkeletonProfile : IEnemyProfile {
    override fun addEquipment(worldIn: World, rand: Random, level: Int, enemy: ModifiableEnemy) {
        if (level == 3 && rand.nextInt(40) == 0) {
            EnemyProfileType.POISON_ARCHER.addEquipment(worldIn, rand, level, enemy)
        }
    }
}