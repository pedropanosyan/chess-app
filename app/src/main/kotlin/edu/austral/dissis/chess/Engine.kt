package edu.austral.dissis.chess;

import common2.Adapter
import common2.Adapter.adaptDecreasePosition
import common2.enums.Colour
import common2.exceptions.EndGameException
import common2.exceptions.InvalidMoveException
import common2.exceptions.NotTurnException
import common2.factory.GameFactory

import edu.austral.dissis.chess.gui.*

class Engine: GameEngine {

    private var classicGame = GameFactory();
    private var myGame = classicGame.createClassicCheckersGame();
    private var currentPlayer = Colour.WHITE

    override fun applyMove(move: Move): MoveResult {
        val fromPos = adaptDecreasePosition(move.from)
        val toPos = adaptDecreasePosition(move.to)
        return try {
            myGame = myGame.move(fromPos, toPos)
            currentPlayer = if (currentPlayer == Colour.WHITE) Colour.BLACK else Colour.WHITE
            NewGameState(Adapter.getPieces(myGame.board), Adapter.adaptColour(currentPlayer))
        } catch (e: InvalidMoveException) {
            e.message?.let { InvalidMove(it) }!!
        } catch (e: EndGameException) {
            GameOver(Adapter.adaptColour(currentPlayer))
        } catch (e: NotTurnException) {
            e.message?.let { InvalidMove(it) }!!
        }
    }

    override fun init(): InitialState {
        return InitialState(
            Adapter.adaptBoard(myGame.board),(Adapter.getPieces(myGame.board)),
            PlayerColor.WHITE
        )
    }
}