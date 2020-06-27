package thedarkcolour.roguelikedungeons.compat

import net.minecraft.entity.EntityType
import net.minecraft.world.ISeedReader

// world implementation
typealias IWorld = ISeedReader

// zombie pigman
fun getPigHumanoid(): EntityType<*> {
    return EntityType.field_233592_ba_
}