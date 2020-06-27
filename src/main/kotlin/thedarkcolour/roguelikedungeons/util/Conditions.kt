package thedarkcolour.roguelikedungeons.util

import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.util.Direction
import thedarkcolour.roguelikedungeons.loot.weight.IWeighted
import thedarkcolour.roguelikedungeons.loot.weight.WeightedPool

/**
 * Ensures a [condition] is `true`.
 *
 * @param condition The condition to check
 * @param message The exception message if the check fails
 *
 * @throws IllegalArgumentException if the condition is `false`
 */
fun checkArgument(condition: Boolean, message: String) {
    if (!condition) {
        throw IllegalArgumentException(message)
    }
}

/**
 * Ensures that the [slot] is an armor slot.
 *
 * @param slot The slot to check
 *
 * @throws IllegalArgumentException if the slot is **not** an armor slot
 */
fun checkArmorSlot(slot: EquipmentSlotType) {
    if (slot.slotType != EquipmentSlotType.Group.ARMOR) {
        throw IllegalArgumentException("Equipment slot ${slot.name} must be an armor slot")
    }
}

/**
 * Ensures that [i] is within the bounds of the specified [range].
 *
 * @param i The integer to check
 * @param range The range to check against
 *
 * @throws IllegalArgumentException if [i] is **not** in the specified [range]
 */
fun checkRange(i: Int, range: IntRange) {
    if (i !in range) {
        throw IllegalArgumentException("Integer $i was not in the expected range $range")
    }
}

/**
 * Ensures that the [IWeighted] has entries
 */
fun checkNotEmpty(weighted: WeightedPool<*>) {
    if (weighted.isEmpty()) {
        throw IllegalStateException("IWeighted instance of class '${weighted.javaClass}' must not be empty")
    }
}

/**
 * Ensures that [array] is not empty.
 *
 * @param array The array to check
 *
 * @throws IllegalArgumentException if [array] is **empty**
 */
fun checkNotEmpty(array: Array<*>) {
    if (array.isEmpty()) {
        throw IllegalArgumentException("Array $array must not be empty")
    }
}

fun checkHorizontal(direction: Direction) {
    if (direction.horizontalIndex == -1) {
        throw IllegalArgumentException("Direction must be horizontal")
    }
}

/**
 * If a previously checked condition fails, use this function to indicate a failure.
 *
 * @throws IllegalArgumentException to indicate an invalid condition that bypassed a previous check
 */
fun checkFailed(): Nothing =
    throw IllegalStateException("Condition check failed")