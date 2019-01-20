package com.pppp.flickrimagegallery.features.main.view.customview

import android.content.Context
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.util.AttributeSet
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pppp.flickrimagegallery.R
import kotlin.properties.Delegates.observable
import kotlin.reflect.KProperty

class FlickrRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    lateinit var clickBlocker: ClickBlocker

    var loader: ImageLoader? by observable(null) { _: KProperty<*>, _: ImageLoader?, newValue: ImageLoader? ->
        newValue?.let { adapter = FlickrAdapter(it) }
    }

    private val flickrAdapter
        get() = adapter as FlickrAdapter

    var onItemClick: OnItemClick? by observable(null) { _: KProperty<*>, _: OnItemClick?, _: OnItemClick? ->
        flickrAdapter.onItemClick = { entry, position ->
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

    private fun onItemClicked(entry: com.pppp.entites.FlickrImage, position: Int) {
        clickBlocker.executeIfAppropriate(this, position) {
            onItemClick?.invoke(entry, position)
        }
    }

    fun getImageViewAtPostiion(position: Int) =
        layoutManager?.findViewByPosition(position)?.findViewById<ImageView>(R.id.image)

    companion object {
        private const val MANY_ROWS = 5
        private const val FEW_ROWS = 3
    }
}

typealias OnItemClick = (com.pppp.entites.FlickrImage, Int) -> Unit
