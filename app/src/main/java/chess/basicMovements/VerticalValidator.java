package chess.basicMovements;

import common.Board;
import common.Piece;
import common.Position;
import common.movementValidator.MovementValidator;

import java.util.ArrayList;
import java.util.List;

public class VerticalValidator implements MovementValidator {

    boolean canJump;
    int maxSteps;

    public VerticalValidator(boolean canJump) {
        this.canJump = canJump;
        this.maxSteps = 8;
    }

    public VerticalValidator(boolean canJump, int maxSteps) {
        this.canJump = canJump;
        this.maxSteps = maxSteps;
    }

    @Override
    public boolean validateMove(Board board, Position from, Position to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        if (colDiff == 0 && rowDiff > 0) {
            if (!canJump) {
                return !areObstacles(board, from, to) && rowDiff <= this.maxSteps;
            } else {
                return colDiff <= this.maxSteps;
            }
        } else return false;
    }

    public boolean areObstacles(Board board, Position from, Position to) {
        int minRow = Math.min(from.getRow(), to.getRow())+1;
        int maxRow = Math.max(from.getRow(), to.getRow());

        while (minRow < maxRow) {
            Piece pieceInBetween = board.getPiece(minRow, from.getCol());
            if (pieceInBetween != null) {
                return true;
            }
            minRow++;
        }
        if (to.hasPiece()) return from.getPiece().getColour() == to.getPiece().getColour();
        else return false;
    }

}
