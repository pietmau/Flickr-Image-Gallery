package com.pppp.network.poko

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList

data class Entry(
    var id: String? = null,

    var content: Content? = null,

    var author: Author? = null,

    @field:Element(name = "title")
    var title: String? = null,

    var category: Category? = null,

    var updated: String? = null,

    @set:ElementList(name = "link", required = false, inline = true)
    @get:ElementList(name = "link", required = false, inline = true)
    internal var link: List<Link>? = null,

    var published: String? = null,

    var displaycategories: String? = null
)
			
			