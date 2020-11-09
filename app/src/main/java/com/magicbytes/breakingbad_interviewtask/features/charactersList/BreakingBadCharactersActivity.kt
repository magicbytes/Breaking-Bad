package com.magicbytes.breakingbad_interviewtask.features.charactersList

import BreakingBadCharactersAdapter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.magicbytes.breakingbad_interviewtask.R
import com.magicbytes.breakingbad_interviewtask.domain.BreakingBadCharacter
import com.magicbytes.breakingbad_interviewtask.features.characterDetails.BreakingBadCharactersDetailsActivity
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_breaking_bad_characters, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_filter -> {
                viewModel.showFilterOptions()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun listenViewModel() {
        viewModel.isLoading.observe(this) {
            swipeToRefreshLayout.isRefreshing = it
        }
        viewModel.characters.observe(this) {
            adapter.breakingBadCharacters = it
        }
        viewModel.currentFilter.observe(this) { seasonNumber ->
            title = if (seasonNumber == -1) {
                "Characters - All seasons"
            } else {
                "Characters - Season $seasonNumber"
            }
        }
        viewModel.filterOptions.observe(this) { filters ->
            if (filters.isEmpty()) {
                Toast.makeText(this, "There are no filter to select from...", Toast.LENGTH_SHORT).show()
            } else {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Choose season")

                val options = filters.map { "Season $it" }.toMutableList()
                options.add(0, "All Seasons")

                builder.setItems(options.toTypedArray()) { _, which ->
                    if (which == 0) {
                        viewModel.filterBy(-1)
                    } else {
                        viewModel.filterBy(filters[which - 1])
                    }
                }

                val dialog = builder.create()
                dialog.show()
            }
        }
    }

    private fun showDetails(breakingBadCharacter: BreakingBadCharacter) {
        BreakingBadCharactersDetailsActivity.start(this, breakingBadCharacter)
    }
}