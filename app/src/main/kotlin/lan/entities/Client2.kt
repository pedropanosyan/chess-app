package edu.austral.dissis.chess.`client-server`

import edu.austral.dissis.chess.gui.CachedImageResolver
import edu.austral.dissis.chess.gui.DefaultImageResolver
import edu.austral.dissis.chess.gui.GameView
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import lan.Client

fun main() {
    Application.launch(Client2::class.java)
}

class Client2  :  Application() {
    private val imageResolver = CachedImageResolver(DefaultImageResolver())
    private val root = GameView(imageResolver)
    private val client = Client(root)

    init {
        client.start()
    }

    override fun start(primaryStage: Stage) {
        primaryStage.title = "Jeronimo"
        primaryStage.scene = Scene(root)
        primaryStage.show()
    }
}