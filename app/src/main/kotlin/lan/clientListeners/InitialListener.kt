package lan.clientListeners

import edu.austral.dissis.chess.gui.InitialState
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener
import lan.Client


class InitialListener(private val client: Client) :  MessageListener<InitialState>{

    override fun handleMessage(message: Message<InitialState>) {
        client.handleInit(message.payload)
    }

}