package com.pppp.network.poko

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList

data class RetrofitEntry(
    @field:Element(name = "title")
    override var id: String? = null,

    override var content: Content? = null,

    override var author: Author? = null,

    @field:Element(name = "title")
    override var title: String? = null,

    override var category: Category? = null,

    override var updated: String? = null,

    @set:ElementList(name = "link", required = false, inline = true)
    @get:ElementList(name = "link", required = false, inline = true)
    override var link: List<Link>? = null,

    override var published: String? = null,

    override var displaycategories: String? = null
) : Entry {
    override val imageUrl = getImageLink(link)

    private fun getImageLink(link: List<Link>?) =
        link?.find { it.type?.equals(TYPE, true) == true }?.href

    companion object {
        private val TYPE = "image/jpeg"
    }
}
			
			