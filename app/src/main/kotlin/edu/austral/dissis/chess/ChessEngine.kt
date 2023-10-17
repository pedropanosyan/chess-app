package edu.austral.dissis.chess;

import classes.Connector
import classes.Player
import classes.enums.Colour
import classes.gameInterface.CreateClassicGame
import classes.gameInterface.GameInterface
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.chess.gui.PlayerColor.BLACK
import edu.austral.dissis.chess.gui.PlayerColor.WHITE

class MyChess: GameEngine {

    private val pedro = Player("Pedro", Colour.WHITE, true)
    private val facundo = Player("Facundo", Colour.BLACK, false)

    private val myGame = CreateClassicGame.createClassicGame(pedro, facundo)
    private var currentPlayer = WHITE

    override fun applyMove(move: Move): MoveResult {
        val fromPos = Connector.fromHisToMinePosition(move.from)
        val fromPiece = myGame.lastMove.getPiece(fromPos.row, fromPos.col)
        val toPos = Connector.fromHisToMinePosition(move.to)
        val toPiece = myGame.lastMove.getPiece(toPos.row, toPos.col)

        return if (fromPiece == null)
            InvalidMove("No piece in (${move.from.row}, ${move.from.column})")
        else if (Connector.adaptColour(fromPiece.colour) != currentPlayer)
            InvalidMove("Piece does not belong to current player")
        else if (toPiece != null && Connector.adaptColour(toPiece.colour) == currentPlayer)
            InvalidMove("There is a piece in (${move.to.row}, ${move.to.column})")
        else if (myGame.lastMove == (myGame.move(fromPos, toPos))){
            InvalidMove("Movimiento no valido")
        } else {
            myGame.move(fromPos, toPos)
            currentPlayer = if (currentPlayer == WHITE) BLACK else WHITE
            NewGameState(Connector.getPieces(myGame.lastMove), currentPlayer)
        }
    }

    override fun init(): InitialState {
        return InitialState(Connector.adaptBoard(myGame.lastMove),(Connector.getPieces(myGame.lastMove)), WHITE)
    }
}