//package edu.austral.dissis.chess;
//
//import classes.Board
//import classes.Connector
//import classes.Player
//import classes.enums.Colour
//import classes.gameInterface.CreateAlternativeGame
//import classes.gameInterface.CreateClassicGame
//import classes.gameInterface.GameInterface
//import edu.austral.dissis.chess.gui.*
//import edu.austral.dissis.chess.gui.PlayerColor.BLACK
//import edu.austral.dissis.chess.gui.PlayerColor.WHITE
//import java.lang.Error
//
//class MyChess: GameEngine {
//
//    private val pedro = Player("Pedro", Colour.WHITE, true)
//    private val facundo = Player("Facundo", Colour.BLACK, false)
//
//
//    private val myGame = CreateAlternativeGame.createAlternativeGame(pedro, facundo)
//    private var currentPlayer = Colour.WHITE
//
//    override fun applyMove(move: Move): MoveResult {
//        val fromPos = Connector.fromHisToMinePosition(move.from)
//        val fromPiece = myGame.lastMove.getPiece(fromPos.row, fromPos.col)
//        val toPos = Connector.fromHisToMinePosition(move.to)
//        val toPiece = myGame.lastMove.getPiece(toPos.row, toPos.col)
//        try {
//            if (fromPiece == null) {
//                return InvalidMove("No piece in (${move.from.row}, ${move.from.column})")
//            } else if (fromPiece.colour != currentPlayer)
//                return InvalidMove("Piece does not belong to current player")
//            else {
//                val previousBoard = myGame.lastMove
//                val afterBoard = myGame.move(fromPos, toPos)
//                if (toPiece != null && toPiece.colour == currentPlayer) {
//                    return InvalidMove("There is a piece in (${move.to.row}, ${move.to.column})")
//                } else if (previousBoard == afterBoard) {
//                    return InvalidMove("Movimiento no valido")
//                } else {
//                    currentPlayer = if (currentPlayer == Colour.WHITE) Colour.BLACK else Colour.WHITE
//                    return NewGameState(Connector.getPieces(afterBoard), Connector.adaptColour(currentPlayer))
//                }
//            }
//        } catch (e: RuntimeException) {
//            return GameOver(Connector.adaptColour(currentPlayer))
//        }
//    }
//
//    override fun init(): InitialState {
//        return InitialState(Connector.adaptBoard(myGame.lastMove),(Connector.getPieces(myGame.lastMove)), WHITE)
//    }
//}