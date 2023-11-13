package lan.serverListeners

import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener
import lan.Server

class ServerConnectionListener(private val server: Server) : MessageListener<Unit> {

    override fun handleMessage(message: Message<Unit>) {
        server.handleInitialConnection()
    }

}