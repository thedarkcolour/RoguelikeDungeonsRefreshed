package thedarkcolour.roguelikedungeons.loot.treasure

import net.minecraft.item.ItemStack
import net.minecraft.tileentity.ChestTileEntity
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import thedarkcolour.roguelikedungeons.dungeon.theme.CHEST_PLACER
import thedarkcolour.roguelikedungeons.dungeon.theme.TRAPPED_CHEST_PLACER
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn
import java.util.*

class TreasureChest(val type: TreasureType) {

    private var seed = 0L
    private var chest: ChestTileEntity? = null
    var rand = Random()
    var level = 0

    fun place(worldIn: DungeonWorldIn, rand: Random, pos: BlockPos, level: Int, trapped: Boolean): TreasureChest {
        this.rand = rand
        this.level = level

        val block = if (trapped) CHEST_PLACER else TRAPPED_CHEST_PLACER

        if (!block.place(worldIn, pos)) {
            throw IllegalStateException("Failed to place treasure chest")
        }

        val chest = worldIn.getTileEntity(pos)
        this.chest = if (chest is ChestTileEntity) chest else null
        this.seed = Objects.hash(pos.hashCode(), worldIn.seed).toLong()

        worldIn.addChest(this)

        return this
    }

    fun setSlot(slot: Int, item: ItemStack) {}

    fun setRandomEmptySlot(item: ItemStack) {}

    fun setLootTable(table: ResourceLocation) {}

    fun isSlotEmpty(slot: Int) {}

    val size: Int = 0
}