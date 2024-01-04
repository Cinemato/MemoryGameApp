package com.cinemato.memorygameapp.models

data class MemoryCard(
    val identifier: Int,
    val imageUrl: String? = null,
    var isOpen: Boolean = false,
    var isMatched: Boolean = false
)
