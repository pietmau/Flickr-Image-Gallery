package com.pppp.network.poko

import com.pppp.entites.Feed
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false, name = "feed")
data class RetrofitFeed(
    @field: Attribute(required = false)
    override var id: String? = null,

    @field: Attribute(required = false)
    override var icon: String? = null,

    @field: Attribute(required = false)
    override var title: String? = null,

    @field: Attribute(required = false)
    override var updated: String? = null,

    @get: ElementList(name = "link", required = false, inline = true)
    @set: ElementList(name = "link", required = false, inline = true)
    override var link: List<RetrofitLink>? = null,

    @get: ElementList(name = "entry", required = false, inline = true, entry = "entry")
    @set: ElementList(name = "entry", required = false, inline = true, entry = "entry")
    override var images: List<RetrofitFlickrImage>? = null,

    @field: Attribute(required = false)
    override var subtitle: String? = null,

    @field: Element(required = false)
    override var generator: RetrofitGenerator? = null,

    @field: Attribute(required = false)
    override var xmlns: String? = null
) : Feed
            