package com.pppp.mvicoreapp.main.view.customview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class EntryHolder(itemView: View, private val imageLoader: ImageLoader) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(model: com.pppp.entites.Entry, onItemClick: OnItemClick?) {
        imageLoader.loadImage(itemView.image, model.imageUrl, {
            itemView.setOnClickListener {
                onItemClick?.invoke(model, this.adapterPosition)
            }
        })
        itemView.item_title.text = model.title
    }

    fun unbind() {
        imageLoader.cancelTask(itemView.image)
        itemView.setOnClickListener(null)
    }
}
