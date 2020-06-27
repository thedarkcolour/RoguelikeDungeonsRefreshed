package thedarkcolour.roguelikedungeons.enemy.profile

import net.minecraft.world.World
import thedarkcolour.roguelikedungeons.enemy.ModifiableEnemy
import thedarkcolour.roguelikedungeons.loot.ARMOR_SLOTS
import thedarkcolour.roguelikedungeons.loot.canEnchant
import thedarkcolour.roguelikedungeons.loot.getRandomArmor
import java.util.*

class TallMobProfile : IEnemyProfile {
    override fun addEquipment(worldIn: World, rand: Random, level: Int, enemy: ModifiableEnemy) {
        for (slot in ARMOR_SLOTS) {
            val item = getRandomArmor(rand, level, slot, canEnchant(worldIn.difficulty, rand, level))

            enemy.setItem(slot, item)
        }
    }
}