package com.pppp.flickrimagegallery.pokos

import com.pppp.entites.*

data class RoomFlickrImage(
    override var id: String?,
    override val content: Content? = null,
    override val author: Author? = null,
    override var title: String?,
    override val category: Category? = null,
    override var updated: String? = null,
    override val link: List<Link>?,
    override var published: String? = null,
    override var displaycategories: String? = null,
    override val imageUrl: String?
) : FlickrImage