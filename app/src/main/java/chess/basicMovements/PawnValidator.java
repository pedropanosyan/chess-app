package chess.basicMovements;

import common.Board;
import common.Position;
import common.movementValidator.MovementValidator;

import java.util.ArrayList;
import java.util.List;

public class PawnValidator implements MovementValidator {

    boolean canJump;
    int forward;
    int backward;

    public PawnValidator(boolean canJump, int forward, int backward) {
        this.canJump = canJump;
        this.forward = forward;
        this.backward = backward;
    }

    @Override
    public boolean validateMove(Board board, Position from, Position to) {
        int rowDiff = to.getRow() - from.getRow();
        int colDiff = Math.abs(to.getCol() - from.getCol());

        boolean hasMoved = from.getPiece().hasMoved();

        if (movesForward(rowDiff, colDiff) && isEmpty(to)) {
            if (isCoronation(to)) {
                return true;
            }
            return true;
        }
        if (movesBackward(rowDiff, colDiff) && isEmpty(to)) {
            return true;
        }
        if (isDoubleMoveForward(rowDiff, colDiff) && isEmpty(to)) {
            if (!hasMoved || this.forward > 1) {
                return board.getPiece(from.getRow() + 1, from.getCol()) == null;
            }
        }
        if (movesDoubleBackward(rowDiff, colDiff) && isEmpty(to)) {
            if (!hasMoved || this.backward > 1) {
                return board.getPiece(from.getRow() - 1, from.getCol()) == null;
            }
        }
        if (movesDiagonalForward(rowDiff, colDiff) && pieceExists(to) && areDifferentColors(from, to)) {
            return true;
        }
        if (movesDiagonalBackward(rowDiff, colDiff) && pieceExists(to) && areDifferentColors(from, to)) {
            return true;
        }
        return false;
    }

    private boolean isCoronation(Position to) {
        return (to.getRow() == 0 && this.backward > 0) || (to.getRow() == 7 && this.forward > 0);
    }

    private static boolean movesDiagonalForward(int rowDiff, int colDiff) {
        return rowDiff == 1 && colDiff == 1;
    }

    private static boolean movesDiagonalBackward(int rowDiff, int colDiff) {
        return rowDiff == -1 && colDiff == 1;
    }

    private boolean movesBackward(int rowDiff, int colDiff) {
        return rowDiff == -1 && colDiff == 0 && this.backward > 0;
    }

    private boolean movesDoubleBackward(int rowDiff, int colDiff) {
        return rowDiff == -2 && colDiff == 0 && this.backward > 0;
    }

    private static boolean areDifferentColors(Position from, Position to) {
        return to.getPiece().getColour() != from.getPiece().getColour();
    }

    private static boolean pieceExists(Position to) {
        return to.hasPiece();
    }

    private boolean isDoubleMoveForward(int rowDiff, int colDiff) {
        return rowDiff == 2 && colDiff == 0 && this.forward > 0;
    }

    private static boolean isEmpty(Position to) {
        return !pieceExists(to);
    }

    private boolean movesForward(int rowDiff, int colDiff) {
        return rowDiff == 1 && colDiff == 0 && this.forward > 0;
    }


}

