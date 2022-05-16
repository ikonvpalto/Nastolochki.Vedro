package org.kvpbldsck.nastolochki.vedro.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import org.kvpbldsck.nastolochki.vedro.R
import org.kvpbldsck.nastolochki.vedro.activities.MainActivity

class EventsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_events, container, false)

        setupToolbar(view)

        return view
    }

    private fun setupToolbar(view: View) {
        val toolbar = view.getToolbar()

        (activity as MainActivity).setSupportActionBar(toolbar)
    }

    private fun View.getToolbar(): Toolbar = findViewById(R.id.events_toolbar)
}