package com.magicbytes.breakingbad_interviewtask

import BreakingBadCharactersAdapter
import BreakingBadCharactersViewModel
import BreakingBadCharactersViewModelFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.magicbytes.breakingbad_interviewtask.domain.BreakingBadCharacter
import kotlinx.android.synthetic.main.activity_breaking_bad_characters.*

class BreakingBadCharactersActivity : AppCompatActivity() {

    private val viewModel: BreakingBadCharactersViewModel by viewModels { BreakingBadCharactersViewModelFactory() }
    private lateinit var adapter: BreakingBadCharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breaking_bad_characters)

        adapter = BreakingBadCharactersAdapter { showDetails(it) }
        charactersRecyclerView.adapter = adapter

        listenViewModel()
    }

    private fun listenViewModel() {
        viewModel.isLoading.observe(this) {
            swipeToRefreshLayout.isRefreshing = it
        }
        viewModel.charachters.observe(this) {
            adapter.breakingBadCharacters = it
        }
    }

    private fun showDetails(breakingBadCharacter: BreakingBadCharacter) {

    }
}