package com.clarkelamothe.intermedia.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clarkelamothe.intermedia.data.Resource
import com.clarkelamothe.intermedia.data.characters.CharactersRepository
import com.clarkelamothe.intermedia.data.models.CharacterResult
import com.clarkelamothe.intermedia.data.models.Data
import com.clarkelamothe.intermedia.utils.ApiDetails.LIMIT_CHARACTER
import com.clarkelamothe.intermedia.utils.ApiDetails.OFFSET
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository,
) : ViewModel() {
    private val _characterResult = MutableLiveData<Resource<Data<List<CharacterResult>>>>()
    val characterResult: LiveData<Resource<Data<List<CharacterResult>>>> = _characterResult

    private var offset = OFFSET
    private val limit = LIMIT_CHARACTER

    init {
        getCharactersResponse(offset)
    }

    fun getCharactersResponse(offset: Int) = viewModelScope.launch(Dispatchers.Main) {
        _characterResult.value = Resource.loading()
        val result = withContext(Dispatchers.IO) {
            charactersRepository.characters(offset, limit)
        }
        _characterResult.value = result
    }

    fun loadMore() {
        offset = _characterResult.value?.data?.offset?.plus(limit) ?: 0
        if (offset != null) {
            getCharactersResponse(offset)
        }
    }
}