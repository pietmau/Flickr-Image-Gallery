package com.pppp.network.poko

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false)
data class Feed(
    @field: Attribute(required = false)
    var id: String? = null,

    @field: Attribute(required = false)
    var icon: String? = null,

    @field: Attribute(required = false)
    var title: String? = null,

    @field: Attribute(required = false)
    var updated: String? = null,

    @get: ElementList(name = "link", required = false, inline = true)
    @set: ElementList(name = "link", required = false, inline = true)
    var link: List<Link>? = null,

    @get: ElementList(name = "entry", required = false, inline = true)
    @set: ElementList(name = "entry", required = false, inline = true)
    var entry: List<Entry>? = null,

    @field: Attribute(required = false)
    var subtitle: String? = null,

    @field: Element(required = false)
    var generator: Generator? = null,

    @field: Attribute(required = false)
    var xmlns: String? = null
)
			
			