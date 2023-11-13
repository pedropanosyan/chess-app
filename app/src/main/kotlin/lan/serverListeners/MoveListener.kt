package lan.serverListeners

import edu.austral.dissis.chess.gui.Move
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener
import lan.Server

class MoveListener(private val server: Server): MessageListener<Move> {

    override fun handleMessage(message: Message<Move>) {
        server.handleMove(message.payload)
    }

}