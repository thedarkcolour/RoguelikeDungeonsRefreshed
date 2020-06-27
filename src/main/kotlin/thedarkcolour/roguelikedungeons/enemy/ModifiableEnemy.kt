package thedarkcolour.roguelikedungeons.enemy

import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.monster.ZombieEntity
import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.ItemStack
import net.minecraft.util.text.StringTextComponent
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.loot.getRandomShield
import java.util.*

@Complete
class ModifiableEnemy(private var entity: LivingEntity) {

    fun setItem(slot: EquipmentSlotType, item: ItemStack) {
        entity.setItemStackToSlot(slot, item)
    }

    fun setEntityType(type: EntityType<*>) {
        val old = entity
        val new = type.create(old.world)!!

        new.copyLocationAndAnglesFrom(old)

        if (new is ZombieEntity) {
            new.isChild = old.isChild
        }

        for (slot in EquipmentSlotType.values()) {
            new.setItemStackToSlot(slot, old.getItemStackFromSlot(slot))
        }

        old.remove()
        new.world.addEntity(new)
    }

    fun setChild(isChild: Boolean) {
        if (entity is ZombieEntity) {
            (entity as ZombieEntity).isChild = isChild
        }
    }

    fun isOf(type: Class<*>): Boolean {
        return type.isInstance(entity)
    }

    fun setName(name: String) {
        entity.customName = StringTextComponent(name)
        entity.isCustomNameVisible = true
    }

    fun setWeapon(weapon: ItemStack) {
        setItem(EquipmentSlotType.MAINHAND, weapon)
    }

    fun addShield(rand: Random) {
        setItem(EquipmentSlotType.OFFHAND, getRandomShield(rand))
    }
}