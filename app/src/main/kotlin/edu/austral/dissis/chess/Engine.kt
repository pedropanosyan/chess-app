package edu.austral.dissis.chess;

import common.Adapter
import common.enums.Colour
import common.exceptions.EndGameException
import common.exceptions.InvalidMoveException
import common.gameCreator.CheckersGameConstructor
import common.gameCreator.ChessGameConstructor
import edu.austral.dissis.chess.gui.*

class Engine: GameEngine {

    private var myGame = CheckersGameConstructor.createClassicGame(8)
    private var currentPlayer = Colour.WHITE


    override fun applyMove(move: Move): MoveResult {
        val fromPos = Adapter.fromHisToMinePosition(move.from)
        val realFromPos = myGame.board.getPosition(fromPos.row, fromPos.col)
        val fromPiece = myGame.board.getPiece(fromPos.row, fromPos.col)
        val toPos = Adapter.fromHisToMinePosition(move.to)
        val realToPos = myGame.board.getPosition(toPos.row, toPos.col)

        if (fromPiece == null) return InvalidMove("No piece in from position")

        try {
            if (fromPiece.colour != currentPlayer) return InvalidMove("Piece does not belong to current player")
            val newGame = myGame.move(realFromPos, realToPos)
            myGame = newGame
            currentPlayer = if (currentPlayer == Colour.WHITE) Colour.BLACK else Colour.WHITE
            return NewGameState(Adapter.getPieces(newGame.board), Adapter.adaptColour(currentPlayer))
        } catch (e: InvalidMoveException) {
            return e.message?.let { InvalidMove(it) }!!
        } catch (e: EndGameException) {
            return GameOver(Adapter.adaptColour(currentPlayer))
        }
    }

    override fun init(): InitialState {
        return InitialState(
            Adapter.adaptBoard(myGame.board),(Adapter.getPieces(myGame.board)),
            PlayerColor.WHITE
        )
    }
}