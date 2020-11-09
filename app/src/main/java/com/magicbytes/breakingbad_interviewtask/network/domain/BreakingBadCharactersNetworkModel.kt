package com.magicbytes.breakingbad_interviewtask.network.domain

data class BreakingBadCharactersNetworkModel(
    val name: String,
    val img: String,
    val status: String,
    val nickname: String,
    val appearance: List<Int>?,
    val occupation: List<String>
)