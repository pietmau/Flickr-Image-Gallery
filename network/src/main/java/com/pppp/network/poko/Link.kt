package com.pppp.network.poko

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root
data class Link(
    @field:Attribute(name = "rel", required = false)
    var rel: String? = null,

    @field:Attribute(name = "type", required = false)
    var type: String? = null,

    @field:Attribute(name = "href", required = false)
    var href: String? = null
)
			
			