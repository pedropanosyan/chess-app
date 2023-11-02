package common;

import common.enums.Colour;
import common.move.Move;
import common.exceptions.EndGameException;
import common.exceptions.InvalidMoveException;
import common.winningValidator.WinningValidator;

public class Game {

    private final GameVersion version;
    private final Board board;

    public Game(GameVersion version, Board board) {
        this.version = version;
        this.board  = board;
    }

    public Game move(Position from, Position to) throws InvalidMoveException, EndGameException {
        if (!from.hasPiece()) throw new InvalidMoveException("Trying to move from an empty position");
        Piece piece = from.getPiece();
        Board newBoard = validateAndMove(piece, from, to);
        if (gameHasFinished(newBoard, piece.getColour())) throw new EndGameException();
        return new Game(this.version, newBoard);
    }

    private Board validateAndMove(Piece piece, Position from, Position to) throws InvalidMoveException {
        try {
            Move move = this.version.getBasicMovementsByPiece(piece.getType());
            return move.move(this.board, from, to);
        } catch (InvalidMoveException e) {
            throw new InvalidMoveException(e.getMessage());
        }
    }

    private boolean gameHasFinished(Board board, Colour colour) {
        for (WinningValidator winningCondition : this.version.getWinningConditions()) {
            if (winningCondition.validated(this.version.getMove(), board, getOppositeColour(colour))) {
                return true;
            }
        }
        return false;
    }

    private Colour getOppositeColour(Colour colour) {
        return colour == Colour.WHITE ? Colour.BLACK : Colour.WHITE;
    }

    public Board getBoard() {
        return this.board;
    }

}
