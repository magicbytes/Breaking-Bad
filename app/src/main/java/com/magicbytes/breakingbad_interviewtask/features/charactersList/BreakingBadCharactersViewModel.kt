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
    private var maxSeasonsNumber = 0

    private val _characters = MutableLiveData<List<BreakingBadCharacter>>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _filterOptions = MutableLiveData<List<Int>>()
    private val _currentFilter = MutableLiveData<Int>()

    val currentFilter: LiveData<Int>
        get() = _currentFilter

    val characters: LiveData<List<BreakingBadCharacter>>
        get() = _characters

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val filterOptions: LiveData<List<Int>>
        get() = _filterOptions

    init {
        loadAllCharacters()
    }

    /**
     * If -1 return all characters from all seasons, otherwise all characters specific for the [seasonId]
     */
    fun filterBy(seasonId: Int) {
        _currentFilter.postValue(seasonId)
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

    fun showFilterOptions() {
        val availableOptions = (1 until maxSeasonsNumber).map { it }
        _filterOptions.value = availableOptions
    }

    private fun loadAllCharacters() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            allFoundCharacters = charactersRepo.getAll()
            maxSeasonsNumber = allFoundCharacters.map { it.seasonAppearance }.flatten().toSet().maxOrNull() ?: 0
            _currentFilter.postValue(-1)

            _isLoading.postValue(false)
            _characters.postValue(allFoundCharacters)
        }
    }
}