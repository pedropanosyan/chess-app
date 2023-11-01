package common;

import common.enums.Colour;
import common.move.Move;
import common.exceptions.EndGameException;
import common.exceptions.InvalidMoveException;
import common.winningValidator.WinningValidator;

import java.util.Stack;

public class Game {

    private final GameVersion version;
    private Stack<Board> moveHistory = new Stack<>();
    private final Player[] players;

    public Game(GameVersion version, Board board, Player[] players) {
        this.players = players;
        this.version = version;
        this.moveHistory.push(board);
    }

    public Board move(Position from, Position to) throws InvalidMoveException, EndGameException {
        if (!from.hasPiece()) throw new InvalidMoveException("Trying to move from an empty position");
        Piece piece = from.getPiece();
        Board newBoard = checkIfValidMovement(piece, from, to);
        if (checkWinningConditions(newBoard, piece.getColour())) throw new EndGameException();
        return submitMove(newBoard);
    }

    private Board checkIfValidMovement(Piece piece, Position from, Position to) throws InvalidMoveException {
        try {
            Move move = this.version.getBasicMovementsByPiece(piece.getType());
            return move.move(getLastMove(), from, to);
        } catch (InvalidMoveException e) {
            throw new InvalidMoveException(e.getMessage());
        }
    }

    private boolean checkWinningConditions(Board board, Colour colour) {
        for (WinningValidator winningCondition : this.version.getWinningConditions()) {
            if (winningCondition.validated(this.version.getMove(), board, getOppositeColour(colour))) {
                return true;
            }
        }
        return false;
    }

    private Board submitMove(Board board) {
        this.moveHistory.push(board);
        return board;
    }

    private Colour getOppositeColour(Colour colour) {
        return colour == Colour.WHITE ? Colour.BLACK : Colour.WHITE;
    }

    private Colour getPlayerTurn() {
        for (Player player : players) {
            if (player.isTurn()) {
                return player.getColour();
            }
        }
        return null;
    }

    private void addBoardToHistory(Board nextBoard) {
        moveHistory.push(nextBoard);
    }

    public Board getLastMove() { return this.moveHistory.peek(); }

    public GameVersion getVersion() {
        return version;
    }

    private void changeTurn() {
        for (int i = 0; i < players.length; i++) {
            if (players[i].isTurn()) {
                players[i].setTurn();
                players[(i + 1) % players.length].setTurn();
                break;
            }
        }
    }
}
