package thedarkcolour.roguelikedungeons.enemy.profile

import net.minecraft.world.World
import thedarkcolour.roguelikedungeons.enemy.ModifiableEnemy
import thedarkcolour.roguelikedungeons.loot.ARMOR_SLOTS
import thedarkcolour.roguelikedungeons.loot.Novelty
import thedarkcolour.roguelikedungeons.loot.getDyedEquipment
import java.util.*

class AshleaProfile : IEnemyProfile {
    override fun addEquipment(worldIn: World, rand: Random, level: Int, enemy: ModifiableEnemy) {
        enemy.setChild(true)

        EnemyProfileType.VILLAGER.addEquipment(worldIn, rand, level, enemy)

        Novelty.ASHLEA.addItem(worldIn, rand, level, enemy)

        for (slot in ARMOR_SLOTS) {
            enemy.setItem(slot, getDyedEquipment(rand, slot, 0x3f00ff))
        }
    }
}