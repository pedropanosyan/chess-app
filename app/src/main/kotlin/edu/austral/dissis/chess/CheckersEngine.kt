//package edu.austral.dissis.chess
//
//import common.Adapter
//import common.Player
//import common.enums.Colour
//import common.exceptions.EndGameException
//import common.exceptions.InvalidMoveException
//import common.gameCreator.CheckersGameConstructor
//
//import edu.austral.dissis.chess.gui.*
//
//class CheckersEngine(): GameEngine {
//
//    private var myGame = CheckersGameConstructor.createClassicGame(Player
//        ("Player 1", Colour.WHITE),
//        Player("Player 2", Colour.BLACK), 8)
//
//    private var currentPlayer = Colour.WHITE
//
//    override fun applyMove(move: Move): MoveResult {
//        val fromPos = Adapter.fromHisToMinePosition(move.from)
//        val realFromPos = myGame.board.getPosition(fromPos.row, fromPos.col)
//        val fromPiece = myGame.board.getPiece(fromPos.row, fromPos.col)
//        val toPos = Adapter.fromHisToMinePosition(move.to)
//        val realToPos = myGame.board.lastMove.getPosition(toPos.row, toPos.col)
//
//        if (fromPiece == null) return InvalidMove("No piece in from position")
//
//        try {
//            if (fromPiece.colour != currentPlayer) return InvalidMove("Piece does not belong to current player")
//            val newBoard = myGame.move(realFromPos, realToPos)
//            currentPlayer = if (currentPlayer == Colour.WHITE) Colour.BLACK else Colour.WHITE
//            return NewGameState(Adapter.getPieces(newBoard), Adapter.adaptColour(currentPlayer))
//        } catch (e: InvalidMoveException) {
//            return e.message?.let { InvalidMove(it) }!!
//        } catch (e: EndGameException) {
//            return GameOver(Adapter.adaptColour(currentPlayer))
//        }
//    }
//
//    override fun init(): InitialState {
//        return InitialState(
//            Adapter.adaptBoard(myGame.lastMove),(Adapter.getPieces(myGame.lastMove)),
//            PlayerColor.WHITE
//        )
//    }
//
//
//}