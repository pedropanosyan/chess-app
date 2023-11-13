package lan

import com.fasterxml.jackson.core.type.TypeReference
import edu.austral.dissis.chess.gui.*
import edu.austral.ingsis.clientserver.Client
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.netty.client.NettyClientBuilder
import javafx.application.Platform
import lan.clientListeners.*
import java.net.InetSocketAddress

class Client(private val gameView: GameView) {

    private lateinit var client: Client

    fun start() {
        client = build()
        client.connect()
        client.send(Message("init", Unit))
        gameView.addListener(SendMoveListener(this))
    }

    fun handleMove (payload: Move){
        client.send(Message("move", payload))
    }

    private fun build() : Client {
        return NettyClientBuilder.createDefault()
            .withAddress(InetSocketAddress(8080))
            .addMessageListener(
                "init", object : TypeReference<Message<InitialState>>() {},
                InitialListener(this)
            )
            .addMessageListener(
                "move",
                object : TypeReference<Message<NewGameState>> () {},
                NewStateListener(this)
            )
            .addMessageListener(
                "invalid",
                object : TypeReference<Message<InvalidMove>> () {},
                InvalidMoveListener(this)
            )
            .addMessageListener(
                "end-of-game",
                object : TypeReference<Message<GameOver>> () {},
                EndOfGameListener(this)
            )
            .build()
    }
    fun handleInit(payload: InitialState) {
        Platform.runLater {
            gameView.handleInitialState(payload)
        }
    }
    fun handleValidMove(payload: NewGameState) {
        Platform.runLater {
            gameView.handleMoveResult(payload)
        }
    }
    fun handleInvalidMove(payload: InvalidMove){
        Platform.runLater {
            gameView.handleMoveResult(payload)
        }
    }
    fun handleGameOver (payload: GameOver){
        Platform.runLater {
            gameView.handleMoveResult(payload)
        }
    }
}