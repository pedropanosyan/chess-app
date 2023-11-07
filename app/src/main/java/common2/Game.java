package common2;

import common2.Move.BasicMove;
import common2.Move.Move;
import common2.enums.Colour;
import common2.exceptions.EndGameException;
import common2.exceptions.InvalidMoveException;
import common2.exceptions.NotTurnException;
import common2.turn.Turn;
import java.util.Optional;

import java.util.Optional;

public class Game {

    private final Board board;
    private final GameVersion gameVersion;
    private final Turn turn;

    public Game(Board board, Turn turn, GameVersion gameVersion) {
        this.board = board;
        this.gameVersion = gameVersion;
        this.turn = turn;
    }

    public Game move(Position from, Position to) throws InvalidMoveException, EndGameException, NotTurnException {
        if (!board.existsPosition(from)) throw new InvalidMoveException("No piece found in from position");
        if (!isTurnValid(board.getPiece(from).getColour())) throw new NotTurnException();

        Optional<Move> move = isMovementValid(from, to);
        if (move.isEmpty()) throw new InvalidMoveException("Invalid movement");

        Board newBoard = move.get().move(board, from, to);
        if (!rulesValid(from, to)) throw new InvalidMoveException("Rules are broken");
        if (gameEnded(newBoard, turn.getCurrentTurn())) throw new EndGameException();

        return new Game(newBoard, turn.nextTurn(), this.gameVersion);
    }

    private boolean isTurnValid(Colour colour) {
        return turn.getCurrentTurn() == colour;
    }

    private Optional<Move> isMovementValid(Position from, Position to) {
        Piece piece = board.getPiece(from);
        return this.gameVersion.getValidatorsByPieceType(piece.getType()).stream()
                .filter(validatorResult -> validatorResult.validateMove(board, from, to).getKey())
                .findFirst().flatMap(validValidator -> validValidator.validateMove(board, from, to).getValue());
    }

    private boolean rulesValid(Position from, Position to) {
        return this.gameVersion.getGlobalValidators().stream()
                .allMatch(validator -> validator.validateMove(board, from, to).getKey());
    }

    private boolean gameEnded(Board board, Colour colour) {
        return this.gameVersion.getWinningValidators().stream()
                .anyMatch(winningCondition -> winningCondition.validated(board, colour));
    }

    public Board getBoard() {
        return board;
    }
}
