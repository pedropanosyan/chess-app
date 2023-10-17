package classes.basicMovements;

import classes.Board;
import classes.Piece;
import classes.Position;

public class HorizontalValidator implements BasicMovementValidator {

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

    @Override
    public Board move(Board board, Position from, Position to) {
        Piece piece = board.getPiece(from.getRow(), from.getCol());
        Position[][] newBoard = board.copyBoard();
        newBoard[from.getRow()][from.getCol()].setPiece(null);
        newBoard[to.getRow()][to.getCol()].setPiece(piece);
        return new Board(newBoard);
    }

    public boolean areObstacles(Board board, Position from, Position to){
        int minCol = Math.min(from.getCol(), to.getCol());
        int maxCol = Math.max(from.getCol(), to.getCol());

        while (minCol < maxCol) {
            Piece pieceInBetween = board.getPiece(from.getRow(), minCol);
            if (pieceInBetween != null) {
                return true;
            }
            minCol++;
        }
        if (to.hasPiece()) return from.getPiece().getColour() == to.getPiece().getColour();
        else return false;
    }
}
