package com.clarkelamothe.intermedia.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.clarkelamothe.intermedia.data.Resource
import com.clarkelamothe.intermedia.databinding.CardEventsBinding
import com.clarkelamothe.intermedia.databinding.FragmentEventsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventsFragment : Fragment() {
    private lateinit var binding2: CardEventsBinding
    private lateinit var comicsAdapter: ComicsAdapter
    private lateinit var eventsAdapter: EventsAdapter
    private lateinit var binding: FragmentEventsBinding
    private val eventsViewModel: EventsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEventsBinding.inflate(inflater, container, false)
        binding2 = CardEventsBinding.inflate(inflater, container, false)

        loadEvent()

        showResult()

        return binding.root
    }

    fun onEventClick(position: Int) {
        val id = eventsAdapter.events[position].id.toString()
        loadComics(id)
    }

    private fun showResult() {
        binding2.expand.rotation = 180F
//        binding2.rvEventComics.isVisible = true
    }


    private fun loadEvent() {
        eventsViewModel.eventsResult.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    loading()
                }
                Resource.Status.SUCCESS -> {
                    success()
                    eventsAdapter = EventsAdapter(it.data!!.results, this)
                    binding.rvEvents.adapter = eventsAdapter
                }
                Resource.Status.ERROR -> {
                    retry()
                }
            }
        })
    }

    private fun loadComics(id: String) {
        eventsViewModel.comicsResult.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                }
                Resource.Status.SUCCESS -> {
                    eventsViewModel.getComicsResponse(id)
                    comicsAdapter = ComicsAdapter(it.data!!.results)
                    binding2.rvEventComics.adapter = comicsAdapter
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(context, "Oops! Something went wrong.", Toast.LENGTH_LONG).show()
                }
            }
        })
    }


    private fun retry() {
        binding.apply {
            loading.root.isVisible = false
            error.root.isVisible = true
            error.retry.setOnClickListener {
                eventsViewModel.getEventsResponse()
            }
        }
    }

    private fun loading() {
        binding.apply {
            loading.root.isVisible = true
            error.root.isVisible = false
        }
    }

    private fun success() {
        binding.apply {
            loading.root.isVisible = false
            error.root.isVisible = false
        }
    }
}