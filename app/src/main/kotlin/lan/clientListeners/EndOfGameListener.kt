package lan.clientListeners

import edu.austral.dissis.chess.gui.GameOver
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener
import lan.Client

class EndOfGameListener(private val client: Client) : MessageListener<GameOver> {

    override fun handleMessage(message: Message<GameOver>) {
        client.handleGameOver(message.payload)
    }
}