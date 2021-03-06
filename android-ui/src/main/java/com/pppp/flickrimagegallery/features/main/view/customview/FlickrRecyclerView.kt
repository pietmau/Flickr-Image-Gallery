package com.pppp.flickrimagegallery.features.main.view.customview

import android.content.Context
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.util.AttributeSet
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pppp.entites.FlickrImage
import com.pppp.flickrimagegallery.R
import com.pppp.flickrimagegallery.application.imageloader.ImageLoader
import com.pppp.uscases.main.events.Detail
import kotlin.properties.Delegates.observable
import kotlin.reflect.KProperty

private const val MANY_ROWS = 5
private const val FEW_ROWS = 3

class FlickrRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    var loader: ImageLoader? by observable(null) { _: KProperty<*>, _: ImageLoader?, newValue: ImageLoader? ->
        newValue?.let { adapter = FlickrAdapter(it) }
    }

    private val flickrAdapter
        get() = adapter as FlickrAdapter

    var onItemClick: OnItemClick? by observable(null) { _: KProperty<*>, _: OnItemClick?, _: OnItemClick? ->
        flickrAdapter.onClick = { entry, position ->
            onItemClicked(entry, position)
        }
    }

    private val span: Int
        get() = if (resources.configuration.orientation == ORIENTATION_LANDSCAPE) MANY_ROWS else FEW_ROWS

    init {
        layoutManager = GridLayoutManager(this.context, span)
    }

    fun onEntriesAvailable(results: List<com.pppp.entites.FlickrImage>) {
        flickrAdapter.setItems(results)
    }

    private fun onItemClicked(entry: FlickrImage, position: Int) {
        val item = RecyclerItem(entry, position)
        onItemClick?.invoke(item)
    }

    fun getImageViewAtPostiion(position: Int) =
        layoutManager?.findViewByPosition(position)?.findViewById<ImageView>(R.id.image)

    private data class RecyclerItem(
        override val image: FlickrImage,
        override val position: Int
    ) : Detail {
        override val imageUrl: String = image.imageUrl ?: ""
        override val id = image.id
    }
}

typealias OnItemClick = (Detail) -> Unit



