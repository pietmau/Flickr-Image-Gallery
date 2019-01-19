package com.pppp.flickrimagegallery.pokos

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.pppp.entites.*

// For simplicity mapping only id, title image url and time stamp
@Entity
internal data class RoomFlickrImage(
    var timestamp: Long = System.currentTimeMillis(),
    @PrimaryKey
    override val id: String,
    override val title: String?,
    override val imageUrl: String?
) : FlickrImage {
    @Ignore
    override val content: Content? = null
    @Ignore
    override val author: Author? = null
    @Ignore
    override val category: Category? = null
    @Ignore
    override val updated: String? = null
    @Ignore
    override val link: List<Link>? = null
    @Ignore
    override val published: String? = null
    @Ignore
    override val displaycategories: String? = null
}