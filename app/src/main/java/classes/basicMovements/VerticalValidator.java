package classes.basicMovements;

import classes.Board;
import classes.Piece;
import classes.Position;

public class VerticalValidator implements BasicMovementValidator {

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
                return !areObstacles(board, from, to) && colDiff <= this.maxSteps;
            } else {
                return colDiff <= this.maxSteps;
            }
        } else return false;
    }

    public boolean areObstacles(Board board, Position from, Position to) {
        int minRow = Math.min(from.getRow(), to.getRow());
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

    @Override
    public Board move(Board board, Position from, Position to) {
        Piece piece = board.getPiece(from.getRow(), from.getCol());
        Position[][] newBoard = board.copyBoard();
        newBoard[from.getRow()][from.getCol()].setPiece(null);
        newBoard[to.getRow()][to.getCol()].setPiece(piece);
        return new Board(newBoard);
    }

}
