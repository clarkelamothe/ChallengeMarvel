package com.clarkelamothe.intermedia.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clarkelamothe.intermedia.data.Resource
import com.clarkelamothe.intermedia.data.events.EventsRepository
import com.clarkelamothe.intermedia.data.models.ComicsResult
import com.clarkelamothe.intermedia.data.models.Data
import com.clarkelamothe.intermedia.data.models.EventsResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val eventsRepository: EventsRepository,
) : ViewModel() {
    private val _eventResult = MutableLiveData<Resource<Data<List<EventsResult>>>>()
    val eventsResult: LiveData<Resource<Data<List<EventsResult>>>> = _eventResult

    private val _comicResult = MutableLiveData<Resource<Data<List<ComicsResult>>>>()
    val comicsResult: LiveData<Resource<Data<List<ComicsResult>>>> = _comicResult

    init {
        getEventsResponse()
        getComicsResponse("116")
    }

    private fun getEventsResponse() = viewModelScope.launch(Dispatchers.Main) {
        _eventResult.value = Resource.loading()
        val result = withContext(Dispatchers.IO) {
            eventsRepository.events()
        }
        _eventResult.value = result
    }

    fun getComicsResponse(id: String) = viewModelScope.launch(Dispatchers.Main) {
        _comicResult.value = Resource.loading()
        val result = withContext(Dispatchers.IO) {
            eventsRepository.comics(id)
        }
        _comicResult.value = result
    }
}