package com.pppp.network.poko

import com.pppp.entites.FlickrImage
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList

data class RetrofitFlickrImage(
    @field:Element(name = "id")
    override var id: String = "",

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
) : FlickrImage {
    override val imageUrl by lazy { link?.find { it.type?.equals(TYPE, true) == true }?.href }

    companion object {
        private const val TYPE = "image/jpeg"
    }
}
            