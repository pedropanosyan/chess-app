package lan.clientListeners

import edu.austral.dissis.chess.gui.NewGameState
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener
import lan.Client

class NewStateListener(private val client: Client) : MessageListener<NewGameState> {

    override fun handleMessage(message: Message<NewGameState>) {
        client.handleValidMove(message.payload)
    }
}