package com.cinemato.memorygameapp.models

data class MemoryCard(
    val identifier: Int,
    var isOpen: Boolean = false,
    var isMatched: Boolean = false
)
