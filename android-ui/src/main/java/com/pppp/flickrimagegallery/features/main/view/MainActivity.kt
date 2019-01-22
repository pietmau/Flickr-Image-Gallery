package com.pppp.flickrimagegallery.features.main.view

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_LONG
import com.pppp.entites.FlickrImage
import com.pppp.flickrimagegallery.R
import com.pppp.flickrimagegallery.application.imageloader.ImageLoader
import com.pppp.flickrimagegallery.features.detail.view.DetailActivity
import com.pppp.flickrimagegallery.features.main.view.controller.Controller
import com.pppp.uscases.main.events.Detail
import com.pppp.uscases.main.events.Event
import com.pppp.uscases.main.events.Model
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var controller: Controller<Model, Event>
    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        AndroidInjection.inject(this)
        recycler.loader = imageLoader
        recycler.onItemClick = { itemClicked ->
            controller.accept(Event.DetailSelected(itemClicked))
        }
        controller.connect(accept = ::render)
    }

    private fun render(model: Model) {
        when (model) {
            is Model.Complete -> onLoadComplete(model.result)
            is Model.Starting, is Model.Loading -> onLoading()
            is Model.Error -> onError(model.throwable.localizedMessage)
            is Model.NavigateToDetail -> showDetail(model.detail)
        }
    }

    private fun onError(message: String?) {
        progress.visibility = GONE
        Snackbar.make(container, message ?: getString(R.string.error), LENGTH_LONG).show()
    }

    private fun onLoading() {
        progress.visibility = VISIBLE
    }

    private fun showDetail(detail: Detail) {
        progress.visibility = GONE
        val imageView = recycler.getImageViewAtPostiion(detail.position)
        if (imageView?.isImageLoaded() == true) {
            startActivityWithTransition(detail, imageView)
        } else {
            startActivityWithoutTransition(detail)
        }
    }

    private fun startActivityWithTransition(detail: Detail, imageView: ImageView) {
        val intent = getDetailActivityIntent(detail)
        val options = ActivityOptions.makeSceneTransitionAnimation(
            this, imageView, getString(R.string.image_transition)
        )
        startActivity(intent, options.toBundle())
    }

    private fun startActivityWithoutTransition(detail: Detail) {
        val detailActivityIntent = getDetailActivityIntent(detail)
        startActivity(detailActivityIntent)
    }

    private fun getDetailActivityIntent(detail: Detail) =
        Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.IMAGE_URL, detail.imageUrl)
        }

    private fun onLoadComplete(result: List<FlickrImage>) {
        progress.visibility = GONE
        recycler.onEntriesAvailable(result)
    }

    override fun onPause() {
        super.onPause()
        controller.stop()
    }

    public override fun onResume() {
        super.onResume()
        controller.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        controller.disconnect()
    }
}

fun ImageView.isImageLoaded(): Boolean = this.drawable != null
