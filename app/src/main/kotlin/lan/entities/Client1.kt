package lan.entities

import edu.austral.dissis.chess.gui.CachedImageResolver
import edu.austral.dissis.chess.gui.DefaultImageResolver
import edu.austral.dissis.chess.gui.GameView
import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.Scene
import javafx.stage.Stage
import lan.Client

fun main() {
    launch(Client1::class.java)
}

class Client1  :  Application() {
    private val imageResolver = CachedImageResolver(DefaultImageResolver())
    private val root = GameView(imageResolver)
    private val client = Client(root)

    init {
        client.start()
    }

    override fun start(primaryStage: Stage) {
        primaryStage.title = "Pedro"
        primaryStage.scene = Scene(root)
        primaryStage.show()
    }
}