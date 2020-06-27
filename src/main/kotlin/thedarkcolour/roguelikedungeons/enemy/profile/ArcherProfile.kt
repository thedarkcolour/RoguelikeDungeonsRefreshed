package thedarkcolour.roguelikedungeons.enemy.profile

import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.world.World
import thedarkcolour.roguelikedungeons.enemy.ModifiableEnemy
import thedarkcolour.roguelikedungeons.enemy.potion.getHarmfulArrow
import thedarkcolour.roguelikedungeons.loot.canEnchant
import thedarkcolour.roguelikedungeons.loot.getRandomBow
import java.util.*

class ArcherProfile : IEnemyProfile {
    override fun addEquipment(worldIn: World, rand: Random, level: Int, enemy: ModifiableEnemy) {
        if (canEnchant(worldIn.difficulty, rand, level) && rand.nextInt(10) == 0) {
            enemy.setItem(EquipmentSlotType.OFFHAND, getHarmfulArrow(rand, 1))
        }

        enemy.setItem(
            EquipmentSlotType.MAINHAND,
            getRandomBow(rand, level, canEnchant(worldIn.difficulty, rand, level))
        )
        EnemyProfileType.TALL_MOB.addEquipment(worldIn, rand, level, enemy)
    }
}