package com.pppp.network.poko

import com.pppp.entites.Entry
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList

data class RetrofitEntry(
    @field:Element(name = "id")
    override var id: String? = null,

    override var content: RetrofitContent? = null,

    override var author: RetrotifAuthor? = null,

    @field:Element(name = "title")
    override var title: String? = null,

    override var category: RetrofitCategory? = null,

    override var updated: String? = null,

    @set:ElementList(name = "link", required = false, inline = true, entry = "link")
    @get:ElementList(name = "link", required = false, inline = true, entry = "link")
    override var link: List<RetrofitLink>? = null,

    override var published: String? = null,

    override var displaycategories: String? = null
) : Entry {
    override val imageUrl = getImageLink(link)

    private fun getImageLink(link: List<RetrofitLink>?) =
        link?.find { it.type?.equals(TYPE, true) == true }?.href

    companion object {
        private val TYPE = "image/jpeg"
    }
}
			
			