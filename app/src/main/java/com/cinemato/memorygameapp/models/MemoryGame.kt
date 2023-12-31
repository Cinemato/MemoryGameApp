package com.cinemato.memorygameapp.models

import android.util.Log
import com.cinemato.memorygameapp.utils.DEFAULT_ICONS

class MemoryGame(
    private val boardSize: BoardSize,
    private val customImages: List<String>?
) {
    val cards: List<MemoryCard>
    var numPairsFound = 0

    private var indexOfSingleSelectedCard: Int? = null
    private var numCardFlips = 0

    init {
        if (customImages == null) {
            val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
            val randomizedImages = (chosenImages + chosenImages).shuffled()
            cards = randomizedImages.map {
                MemoryCard(it)
            }
        } else {
            val randomizedImages = (customImages + customImages).shuffled()
            cards = randomizedImages.map {
                MemoryCard(it.hashCode(), it)
            }
        }
    }

    fun flipCard(position: Int): Boolean {
        val card = cards[position]
        var foundMatch = false

        if (indexOfSingleSelectedCard == null) {
            restoreCards()
            indexOfSingleSelectedCard = position
        } else {
            foundMatch = checkForMatch(indexOfSingleSelectedCard!!, position)
            indexOfSingleSelectedCard = null
        }

        card.isOpen = !card.isOpen
        numCardFlips++

        return foundMatch
    }

    private fun checkForMatch(position1: Int, position2: Int): Boolean {
        if (cards[position1].identifier != cards[position2].identifier) {
            return false
        }

        cards[position1].isMatched = true
        cards[position2].isMatched = true
        numPairsFound++
        return true
    }

    fun restoreCards() {
        for(card in cards) {
            if (!card.isMatched) {
                card.isOpen = false
            }
        }
    }

    fun haveWonGame(): Boolean {
        return numPairsFound == boardSize.getNumPairs()
    }

    fun getNumMoves(): Int {
        return numCardFlips / 2
    }
}