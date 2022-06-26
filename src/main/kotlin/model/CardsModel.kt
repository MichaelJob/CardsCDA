package model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import data.CardsRepository

class CardsModel(repo: CardsRepository) {
    var next = "next"
    var data = repo.data
    var id by mutableStateOf(0)
}