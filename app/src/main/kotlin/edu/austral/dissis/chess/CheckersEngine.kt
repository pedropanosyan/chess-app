package edu.austral.dissis.chess

import checkers.Connector
import checkers.GameCreator
import checkers.enums.Colour
import common.exceptions.EndGameException
import common.exceptions.InvalidMoveException

import edu.austral.dissis.chess.gui.*

class CheckersEngine(): GameEngine {

    private val myGame = GameCreator.createClassicGame(8)
    private var currentPlayer = Colour.WHITE

    override fun applyMove(move: Move): MoveResult {
        val fromPos = Connector.fromHisToMinePosition(move.from)
        val realFromPos = myGame.lastMove.getPosition(fromPos.row, fromPos.col)
        val fromPiece = myGame.lastMove.getPiece(fromPos.row, fromPos.col)
        val toPos = Connector.fromHisToMinePosition(move.to)
        val realToPos = myGame.lastMove.getPosition(toPos.row, toPos.col)

        if (fromPiece == null) return InvalidMove("No piece in from position")

        try {
            if (fromPiece.colour != currentPlayer) return InvalidMove("Piece does not belong to current player")
            val newBoard = myGame.move(realFromPos, realToPos)
            currentPlayer = if (currentPlayer == Colour.WHITE) Colour.BLACK else Colour.WHITE
            return NewGameState(Connector.getPieces(newBoard), Connector.adaptColour(currentPlayer))
        } catch (e: InvalidMoveException) {
            return e.message?.let { InvalidMove(it) }!!
        } catch (e: EndGameException) {
            return GameOver(Connector.adaptColour(currentPlayer))
        }
    }

    override fun init(): InitialState {
        return InitialState(
            Connector.adaptBoard(myGame.lastMove),(Connector.getPieces(myGame.lastMove)),
            PlayerColor.WHITE
        )
    }


}