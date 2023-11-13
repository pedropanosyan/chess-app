package lan

import com.fasterxml.jackson.core.type.TypeReference
import common2.Adapter
import edu.austral.dissis.chess.MyEngine

import edu.austral.dissis.chess.gui.*
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.Server
import edu.austral.ingsis.clientserver.ServerBuilder
import lan.serverListeners.MoveListener
import lan.serverListeners.ServerConnectionListener

class Server (private val game: MyEngine, private val builder : ServerBuilder, private val gameView: GameView) {

    private lateinit var server: Server

    fun start() {
        server = build()
        server.start()
        gameView.handleInitialState(InitialState(game.init().boardSize, game.init().pieces, game.init().currentPlayer))
    }

    private fun build() : Server {
        return builder
            .withPort(8080)
            .addMessageListener(
                "move",
                object : TypeReference<Message<Move>> () {},
                MoveListener(this))
            .addMessageListener(
                "init",
                object : TypeReference<Message<Unit>> () {},
                ServerConnectionListener(this)
            )
            .build()
    }

    fun handleMove(move: Move) {
        when (val movement = game.applyMove(move)) {
            is NewGameState -> server.broadcast(Message("move", handleNextMove(movement)))
            is InvalidMove -> server.broadcast(Message("invalid", InvalidMove(movement.reason)))
            is GameOver -> server.broadcast(Message("end-of-game", GameOver(Adapter.adaptColour(game.currentPlayer))))
        }
    }

    private fun handleNextMove(moveResult: MoveResult) : MoveResult {
        gameView.handleMoveResult(moveResult)
        return moveResult
    }

    fun handleInitialConnection(){
        server.broadcast(Message("init", InitialState(game.init().boardSize, game.init().pieces, game.init().currentPlayer)))
    }
}