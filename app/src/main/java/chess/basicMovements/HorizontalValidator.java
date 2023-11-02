package chess.basicMovements;

import common.Board;
import common.Piece;
import common.Position;
import common.movementValidator.MovementValidator;

import java.util.ArrayList;
import java.util.List;

public class HorizontalValidator implements MovementValidator {

    boolean canJump;
    int maxSteps;

    public HorizontalValidator(boolean canJump) {
        this.canJump = canJump;
        this.maxSteps = 8;
    }

    public HorizontalValidator(boolean canJump, int maxSteps) {
        this.canJump = canJump;
        this.maxSteps = maxSteps;
    }

    @Override
    public boolean validateMove(Board board, Position from, Position to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        if (rowDiff == 0 && colDiff > 0) {
            if (!canJump) {
                return !areObstacles(board, from, to) && colDiff <= this.maxSteps;
            } else {
                return colDiff <= this.maxSteps;
            }
        }
        return false;
    }

    public boolean areObstacles(Board board, Position from, Position to){
        int minCol = Math.min(from.getCol(), to.getCol())+1;
        int maxCol = Math.max(from.getCol(), to.getCol());

        while (minCol < maxCol) {
            Piece pieceInBetween = board.getPiece(from.getRow(), minCol);
            if (pieceInBetween != null) {
                return true;
            }
            minCol++;
        }
        Piece piece = board.getPiece(to.getRow(), to.getCol());
        if (piece != null) return from.getPiece().getColour() == piece.getColour();
        else return false;
    }
}
