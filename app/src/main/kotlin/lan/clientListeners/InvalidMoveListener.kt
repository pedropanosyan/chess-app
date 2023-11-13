package lan.clientListeners

import edu.austral.dissis.chess.gui.InvalidMove
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener
import lan.Client

class InvalidMoveListener(private val client: Client) : MessageListener<InvalidMove> {

    override fun handleMessage(message: Message<InvalidMove>) {
        client.handleInvalidMove(message.payload)
    }
}