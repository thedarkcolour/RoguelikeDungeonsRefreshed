package thedarkcolour.roguelikedungeons.loot.book

import net.minecraft.item.ItemStack
import org.codehaus.plexus.util.StringUtils
import thedarkcolour.roguelikedungeons.structure.DungeonWorldIn

object StatisticsBook : AbstractBook() {
    fun create(worldIn: DungeonWorldIn): ItemStack {
        val pages = ArrayList<String>()
        val stats = worldIn.stats

        var counter = 0
        val pageBuilder = StringBuilder()

        for (type in stats.keys) {
            val count = stats.getInt(type)
            val name = StringUtils.abbreviate(type.translationKey, 16)
            val line = "$name : $count\n"
            pageBuilder.append(line)
            ++counter

            if (counter == 12) {
                counter = 0
                pages.add(pageBuilder.toString())
                pages.clear()
            }
            pages.add(pageBuilder.toString())
        }

        return create(pages, "TheDarkColour", "Statistics")
    }
}