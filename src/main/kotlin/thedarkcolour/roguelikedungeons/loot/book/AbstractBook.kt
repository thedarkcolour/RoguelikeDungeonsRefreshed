package thedarkcolour.roguelikedungeons.loot.book

import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.ListNBT
import net.minecraft.nbt.StringNBT
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.StringTextComponent

abstract class AbstractBook {
    protected fun create(pages: List<String>, author: String, title: String): ItemStack {
        val book = ItemStack(Items.WRITTEN_BOOK, 1)
        val pagesList = ListNBT()

        for (page in pages) {
            val text = StringTextComponent(page)
            val json = ITextComponent.Serializer.toJson(text)
            val pageNbt = StringNBT.of(json)
            pagesList.add(pageNbt)
        }

        book.setTagInfo("pages", pagesList)
        book.setTagInfo("author", StringNBT.of(author))
        book.setTagInfo("title", StringNBT.of(title))

        return book
    }
}