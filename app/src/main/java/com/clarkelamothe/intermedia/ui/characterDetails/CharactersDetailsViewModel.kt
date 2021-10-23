package com.clarkelamothe.intermedia.ui.characterDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clarkelamothe.intermedia.data.Resource
import com.clarkelamothe.intermedia.data.characters.CharactersRepository
import com.clarkelamothe.intermedia.data.models.ComicsResult
import com.clarkelamothe.intermedia.data.models.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharactersDetailsViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository,
) : ViewModel() {
    private val _comicResult = MutableLiveData<Resource<Data<List<ComicsResult>>>>()
    val comicResult: LiveData<Resource<Data<List<ComicsResult>>>> = _comicResult

    fun getComicsResponse(id: String) = viewModelScope.launch(Dispatchers.Main) {
        _comicResult.value = Resource.loading()
        val result = withContext(Dispatchers.IO) {
            charactersRepository.comics(id)
        }
        _comicResult.value = result
    }

}