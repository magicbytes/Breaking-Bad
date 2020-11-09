package com.magicbytes.breakingbad_interviewtask.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BreakingBadCharacter(
    val name: String,
    val imageUrl: String,
    val status: String,
    val nickname: String,
    val seasonAppearance: List<Int>,
    val occupations: List<String>
) : Parcelable {

    val appearances: String
        get() {
            return if (seasonAppearance.isEmpty()) {
                "-"
            } else {
                seasonAppearance.joinToString(", ")
            }
        }
}