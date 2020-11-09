package com.magicbytes.breakingbad_interviewtask.features.charactersList

import BreakingBadCharactersRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magicbytes.breakingbad_interviewtask.domain.BreakingBadCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BreakingBadCharactersViewModel(private val charactersRepo: BreakingBadCharactersRepository) : ViewModel() {

    private var allFoundCharacters: List<BreakingBadCharacter> = emptyList()

    private val _characters = MutableLiveData<List<BreakingBadCharacter>>()
    private val _isLoading = MutableLiveData<Boolean>()

    val characters: LiveData<List<BreakingBadCharacter>>
        get() = _characters

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        loadAllCharacters()
    }

    /**
     * If -1 return all characters from all seasons, otherwise all characters specific for the [seasonId]
     */
    fun filterBy(seasonId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val characters = if (seasonId < 0) {
                allFoundCharacters
            } else {
                allFoundCharacters.filter {
                    it.seasonAppearance.contains(seasonId)
                }
            }
            _characters.postValue(characters)
        }
    }

    private fun loadAllCharacters() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            allFoundCharacters = charactersRepo.getAll()
            _isLoading.postValue(false)
            _characters.postValue(allFoundCharacters)
        }
    }
}