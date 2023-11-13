package lan.clientListeners

import edu.austral.dissis.chess.gui.GameEventListener
import edu.austral.dissis.chess.gui.Move
import lan.Client

class SendMoveListener(private var client: Client) :  GameEventListener {

    override fun handleMove(move: Move) {
        client.handleMove(move)
    }
}