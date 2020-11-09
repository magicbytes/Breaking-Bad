package com.magicbytes.breakingbad_interviewtask

import BreakingBadCharactersViewModel
import BreakingBadCharactersViewModelFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

class BreakingBadCharactersActivity : AppCompatActivity() {
    private val viewModel: BreakingBadCharactersViewModel by viewModels { BreakingBadCharactersViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breaking_bad_characters)
        listenViewModel()
    }

    fun listenViewModel() {

    }


    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, BreakingBadCharactersActivity::class.java))
        }
    }
}