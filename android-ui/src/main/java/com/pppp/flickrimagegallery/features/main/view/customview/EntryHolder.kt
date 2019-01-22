package com.pppp.flickrimagegallery.features.main.view.customview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pppp.entites.FlickrImage
import com.pppp.flickrimagegallery.application.imageloader.ImageLoader
import kotlinx.android.synthetic.main.item.view.*

class EntryHolder(itemView: View, private val imageLoader: ImageLoader) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(model: FlickrImage, onItemClick: OnClick?) {
        imageLoader.loadImage(itemView.image, model.imageUrl)
        itemView.item_title.text = model.title
        itemView.setOnClickListener {
            onItemClick?.invoke(model, this.adapterPosition)
        }
    }

    fun unbind() {
        imageLoader.cancelTask(itemView.image)
        itemView.setOnClickListener(null)
    }
}
