package com.magicbytes.breakingbad_interviewtask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.magicbytes.breakingbad_interviewtask.domain.BreakingBadCharacter
import com.magicbytes.breakingbad_interviewtask.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_breaking_bad_characters.view.*

class BreakingBadCharactersViewHolder(parent: ViewGroup, onClick: (adapterPosition: Int) -> Unit) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_breaking_bad_characters, parent, false)
    ) {

    init {
        itemView.setOnClickListener { onClick(adapterPosition) }
    }

    fun onBind(breakingBadCh: BreakingBadCharacter) {
        Picasso.get()
            .load(breakingBadCh.imageUrl)
            .into(itemView.profileImageView)

        itemView.nameTextView.text = breakingBadCh.name
    }
}