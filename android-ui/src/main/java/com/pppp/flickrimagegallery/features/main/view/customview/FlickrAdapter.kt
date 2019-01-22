package com.pppp.flickrimagegallery.features.main.view.customview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pppp.entites.FlickrImage
import com.pppp.flickrimagegallery.R
import com.pppp.flickrimagegallery.application.imageloader.ImageLoader

internal class FlickrAdapter(private val loader: ImageLoader) :
    RecyclerView.Adapter<EntryHolder>() {
    private val entries: MutableList<com.pppp.entites.FlickrImage> = mutableListOf()
    var onClick: OnClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EntryHolder(inflater.inflate(R.layout.item, null), loader)
    }

    override fun onBindViewHolder(holder: EntryHolder, position: Int) {
        holder.bind(entries[position], onClick)
    }

    override fun getItemCount() = entries.size

    override fun onViewRecycled(holder: EntryHolder) {
        holder.unbind()
    }

    fun setItems(newResults: List<com.pppp.entites.FlickrImage>) {
        DiffUtil.calculateDiff(FlickrDiffCallback(entries, newResults)).dispatchUpdatesTo(this)
        entries.clear()
        entries.addAll(newResults)
    }

    private class FlickrDiffCallback(
        private val old: List<com.pppp.entites.FlickrImage>,
        private val new: List<com.pppp.entites.FlickrImage>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            old[oldItemPosition] == new[newItemPosition]

        override fun getOldListSize() = old.size

        override fun getNewListSize() = new.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            old[oldItemPosition] == new[newItemPosition]
    }
}

typealias OnClick = (FlickrImage, Int) -> Unit