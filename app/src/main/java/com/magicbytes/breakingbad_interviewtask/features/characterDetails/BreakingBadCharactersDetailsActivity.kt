package com.magicbytes.breakingbad_interviewtask.features.characterDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.magicbytes.breakingbad_interviewtask.R
import com.magicbytes.breakingbad_interviewtask.domain.BreakingBadCharacter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_breaking_bad_characters_details.*

class BreakingBadCharactersDetailsActivity : AppCompatActivity() {

    private val character: BreakingBadCharacter by lazy { intent.getParcelableExtra<BreakingBadCharacter>(ARG_CHARACTER)!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breaking_bad_characters_details)

        title = character.name

        showCharacterInformation()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showCharacterInformation() {
        Picasso.get()
            .load(character.imageUrl)
            .into(profileImageView)

        nameTextView.text = character.name
        occupationTextView.text = character.occupations.joinToString("\n") {  "- $it"}
        statusTextView.text = character.status
        nicknameTextView.text = character.nickname
        seasonAppearancesTextView.text = character.appearances
    }

    companion object {
        private const val ARG_CHARACTER = "Character"

        fun start(context: Context, character: BreakingBadCharacter) {
            val intent = Intent(context, BreakingBadCharactersDetailsActivity::class.java).apply {
                putExtra(ARG_CHARACTER, character)
            }
            context.startActivity(intent)
        }
    }
}