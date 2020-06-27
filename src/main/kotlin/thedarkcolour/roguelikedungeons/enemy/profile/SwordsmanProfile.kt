package thedarkcolour.roguelikedungeons.enemy.profile

import net.minecraft.world.World
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.enemy.ModifiableEnemy
import thedarkcolour.roguelikedungeons.loot.Novelty
import thedarkcolour.roguelikedungeons.loot.canEnchant
import thedarkcolour.roguelikedungeons.loot.getRandomSword
import thedarkcolour.roguelikedungeons.util.chance
import java.util.*

@Complete
class SwordsmanProfile : IEnemyProfile {
    override fun addEquipment(worldIn: World, rand: Random, level: Int, enemy: ModifiableEnemy) {
        if (rand.chance(20)) {
            Novelty.VALANDRAH.addItem(worldIn, rand, level, enemy)
        } else {
            enemy.setWeapon(getRandomSword(rand, level, canEnchant(worldIn.difficulty, rand, level)))
        }
        enemy.addShield(rand)

        EnemyProfileType.TALL_MOB.addEquipment(worldIn, rand, level, enemy)
    }
}