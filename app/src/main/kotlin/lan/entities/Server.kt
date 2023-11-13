package lan.entities

import edu.austral.dissis.chess.MyEngine
import edu.austral.dissis.chess.gui.CachedImageResolver
import edu.austral.dissis.chess.gui.DefaultImageResolver
import edu.austral.dissis.chess.gui.GameView
import edu.austral.ingsis.clientserver.netty.server.NettyServerBuilder
import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.Scene
import javafx.stage.Stage
import lan.Server

fun main() {
    launch(ChessGameApplication::class.java)
}

class ChessGameApplication : Application() {
    private val gameEngine = MyEngine()
    private val imageResolver = CachedImageResolver(DefaultImageResolver())
    private val root = GameView(imageResolver)
    private val server = NettyServerBuilder.createDefault()
    private val serverBuilder : Server = Server(gameEngine, server, root)

    companion object {
        const val gameTitle = "Server"
    }
    init {
        serverBuilder.start()
    }


    override fun start(primaryStage: Stage) {
        primaryStage.title = gameTitle
        primaryStage.scene = Scene(root)
        primaryStage.show()
    }


}
