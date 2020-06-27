package thedarkcolour.roguelikedungeons.enemy.profile

import net.minecraft.entity.EntityType
import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.potion.Potions
import net.minecraft.world.World
import thedarkcolour.roguelikedungeons.enemy.ModifiableEnemy
import thedarkcolour.roguelikedungeons.enemy.potion.getTippedArrow
import thedarkcolour.roguelikedungeons.loot.*
import java.util.*

class MagicArcherProfile : IEnemyProfile {
    override fun addEquipment(worldIn: World, rand: Random, level: Int, enemy: ModifiableEnemy) {
        enemy.setEntityType(EntityType.STRAY)

        enemy.setItem(EquipmentSlotType.OFFHAND, getTippedArrow(Potions.HARMING))
        enemy.setItem(EquipmentSlotType.OFFHAND, getRandomBow(rand, level, canEnchant(worldIn.difficulty, rand, level)))

        for (slot in ARMOR_SLOTS) {
            val item = getDyedEquipment(rand, slot, 0xb2ff66)
            enchantItem(rand, item, 20)

            enemy.setItem(slot, item)
        }
    }
}