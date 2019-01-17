package com.pppp.flickrimagegallery.features.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pppp.uscases.Event
import com.pppp.uscases.Model
import com.spotify.mobius.Connection
import com.spotify.mobius.MobiusLoop
import com.spotify.mobius.functions.Consumer
import dagger.android.AndroidInjection
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var controller: MobiusLoop.Controller<Model, Event>
    private lateinit var eventConsumer: Consumer<Event>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        controller.connect(this::connectViews);
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
            }

            override fun dispose() { /* NoOp */
            }
        }
    }
}

