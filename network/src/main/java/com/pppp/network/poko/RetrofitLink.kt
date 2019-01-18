package com.pppp.network.poko

import com.pppp.entites.Link
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root
data class RetrofitLink(
    @field:Attribute(name = "rel", required = false)
    override var rel: String? = null,

    @field:Attribute(name = "type", required = false)
    override var type: String? = null,

    @field:Attribute(name = "href", required = false)
    override var href: String? = null
) : Link
			
			