package chess2.validator;

import common2.Board;
import common2.Piece;
import common2.Position;
import common2.enums.Colour;
import common2.validator.MovementValidator;

public class PawnValidator implements MovementValidator {

    int forward;
    int backward;

    public PawnValidator(int forward, int backward) {
        this.forward = forward;
        this.backward = backward;
    }

    @Override
    public boolean validateMove(Board board, Position from, Position to) {
        int rowDiff = to.getRow() - from.getRow();
        int colDiff = Math.abs(to.getCol() - from.getCol());

        if (!pieceExists(board, from)) return false;
        if (isVerticalMovementValid(rowDiff, colDiff, hasMoved(board, from))) return true;

        if (!board.existsPosition(to)) return false;

        return isDiagonalMovementValid(board, from, to);
    }

    private static boolean hasMoved(Board board, Position from) {
        return board.existsPosition(from) && board.getPiece(from).hasMoved();
    }

    private boolean isVerticalMovementValid(int rowDiff, int colDiff, Boolean hasMoved) {
        if (movesBackward(rowDiff, colDiff)) return true;
        if (movesForward(rowDiff, colDiff)) return true;
        if (isDoubleMoveForward(rowDiff, colDiff, hasMoved)) return true;
        if (isDoubleBackward(rowDiff, colDiff, hasMoved)) return true;
        else return false;
    }

    private boolean isDiagonalMovementValid(Board board, Position from, Position to) {
        int rowDiff = to.getRow() - from.getRow();
        int colDiff = Math.abs(to.getCol() - from.getCol());

        if (!board.existsPosition(to)) return false;

        Colour fromColour = board.getPiece(from).getColour();
        Colour toColour = board.getPiece(to).getColour();

        if (!areDifferentColors(fromColour, toColour)) return false;
        if (movesDiagonalForward(rowDiff, colDiff)) return true;
        if (movesDiagonalBackward(rowDiff, colDiff)) return true;
        else return false;
    }

    private static boolean movesDiagonalForward(int rowDiff, int colDiff) {
        return rowDiff == 1 && colDiff == 1;
    }

    private static boolean movesDiagonalBackward(int rowDiff, int colDiff) {
        return rowDiff == -1 && colDiff == 1;
    }

    private boolean movesForward(int rowDiff, int colDiff) {
        return rowDiff == 1 && colDiff == 0 && this.forward > 0;
    }

    private boolean movesBackward(int rowDiff, int colDiff) {
        return rowDiff == -1 && colDiff == 0 && this.backward > 0;
    }

    private boolean isDoubleMoveForward(int rowDiff, int colDiff, boolean hasMoved) {
        return rowDiff == 2 && colDiff == 0 && this.forward > 0 && !hasMoved;
    }

    private boolean isDoubleBackward(int rowDiff, int colDiff, Boolean hasMoved) {
        return rowDiff == -2 && colDiff == 0 && this.backward > 0 && !hasMoved;
    }

    private static boolean areDifferentColors(Colour from, Colour to) {
        return from != to;
    }

    private static boolean pieceExists(Board board, Position to) {
        return !board.existsPosition(to);
    }



}

