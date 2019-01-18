package com.pppp.flickrimagegallery.features.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pppp.entites.FlickrImage
import com.pppp.flickrimagegallery.R
import com.pppp.mvicoreapp.main.view.customview.ClickBlocker
import com.pppp.mvicoreapp.main.view.customview.ImageLoader
import com.pppp.uscases.Event
import com.pppp.uscases.Model
import com.spotify.mobius.Connection
import com.spotify.mobius.MobiusLoop
import com.spotify.mobius.functions.Consumer
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var controller: MobiusLoop.Controller<Model, Event>
    private lateinit var eventConsumer: Consumer<Event>
    @Inject
    lateinit var clickBlocker: ClickBlocker
    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        AndroidInjection.inject(this)
        recyler.clickBlocker = clickBlocker//TODO
        recyler.loader = imageLoader
        recyler.onItemClick = { item, position -> }
        controller.connect(this::connectViews);
    }

    private fun render(model: Model) {
        when (model) {
            is Model.Complete -> onComplete(model.result)
        }
    }

    private fun onComplete(result: List<FlickrImage>) {
        recyler.onEntriesAvailable(result)
    }

    override fun onPause() {
        super.onPause()
        controller.stop();
    }

    public override fun onResume() {
        super.onResume()
        controller.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        controller.disconnect();
    }

    private fun connectViews(eventConsumer: Consumer<Event>): Connection<Model> {
        this.eventConsumer = eventConsumer

        return object : Connection<Model> {
            override fun accept(model: Model) {
                render(model)
            }

            override fun dispose() { /* NoOp */
            }
        }
    }

}

