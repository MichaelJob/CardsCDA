// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import data.CardsRepository
import model.CardsModel
import ui.CardsUI


fun main() = application {

    lateinit var model: CardsModel

    Window(
        title = "Cards",
        icon = BitmapPainter(useResource("cards.png", ::loadImageBitmap)),
        onCloseRequest = ::exitApplication
    ) {
        CardsRepository.loadCards()
        model = CardsModel(CardsRepository)
        CardsUI(model)
    }
}


