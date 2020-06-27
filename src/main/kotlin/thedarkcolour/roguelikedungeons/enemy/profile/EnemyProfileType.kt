package thedarkcolour.roguelikedungeons.enemy.profile

import net.minecraft.entity.monster.SkeletonEntity
import net.minecraft.entity.monster.ZombieEntity
import net.minecraft.world.World
import thedarkcolour.roguelikedungeons.enemy.ModifiableEnemy
import java.util.*

/**
 * @property supplier the supplier for [IEnemyProfile] instances
 */
enum class EnemyProfileType(supplier: () -> IEnemyProfile) {
    TALL_MOB(::TallMobProfile),
    ZOMBIE(::ZombieProfile),
    PIGMAN(::PigmanProfile),
    SKELETON(::SkeletonProfile),
    VILLAGER(::VillagerProfile),
    HUSK(::HuskProfile),
    BABY(::BabyProfile),
    ASHLEA(::AshleaProfile),
    RLEAHY(::RleahyProfile),
    ARCHER(::ArcherProfile),
    WITHER(::WitherProfile),
    POISON_ARCHER(::PoisonArcherProfile),
    MAGIC_ARCHER(::MagicArcherProfile),
    SWORDSMAN(::SwordsmanProfile),
    EVOKER(::EvokerProfile),
    VINDICATOR(::VindicatorProfile),
    WITCH(::WitchProfile),
    JOHNNY(::JohnnyProfile);

    private val supplier = supplier()

    fun addEquipment(worldIn: World, rand: Random, level: Int, enemy: ModifiableEnemy) {
        supplier.addEquipment(worldIn, rand, level, enemy)
    }

    companion object {
        fun equip(
            worldIn: World,
            rand: Random,
            level: Int,
            enemy: ModifiableEnemy
        ) {
            var type: EnemyProfileType? = null

            if (enemy.isOf(ZombieEntity::class.java)) type = ZOMBIE
            if (enemy.isOf(SkeletonEntity::class.java)) type = SKELETON
        }
    }
}