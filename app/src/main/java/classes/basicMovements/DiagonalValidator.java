package classes.basicMovements;

import classes.Board;
import classes.Piece;
import classes.Position;

public class DiagonalValidator implements BasicMovementValidator {

    boolean canJump;
    int maxSteps;

    public DiagonalValidator(boolean canJump) {
        this.canJump = canJump;
        this.maxSteps = 8;
    }

    public DiagonalValidator(boolean canJump, int maxSteps) {
        this.canJump = canJump;
        this.maxSteps = maxSteps;
    }

    @Override
    public boolean validateMove(Board board, Position from, Position to) {
        int rowDiff = to.getRow() - from.getRow();
        int colDiff = to.getCol() - from.getCol();
        if (Math.abs(rowDiff) != Math.abs(colDiff) ) return false;

        int rowIncrement = rowDiff > 0 ? 1 : -1;
        int colIncrement = colDiff > 0 ? 1 : -1;

        int row = from.getRow() + rowIncrement;
        int col = from.getCol() + colIncrement;

        while (row != to.getRow() || col != to.getCol()) {
            Position position = new Position(row, col);
            Piece pieceInBetween = board.getPiece(position.getRow(), position.getCol());
            if (pieceInBetween != null) {
                return false;
            }
            row += rowIncrement;
            col += colIncrement;
        }
        return true;
    }

    @Override
    public Board move(Board board, Position from, Position to) {
        Piece piece = board.getPiece(from.getRow(), from.getCol());
        Position[][] newBoard = board.copyBoard();
        newBoard[from.getRow()][from.getCol()].setPiece(null);
        newBoard[to.getRow()][to.getCol()].setPiece(piece);
        return new Board(newBoard);
    }

    public boolean areObstacles(Board board, Position from, Position to) {
        int minRow = Math.min(from.getRow(), to.getRow());
        int maxRow = Math.max(from.getRow(), to.getRow());
        int minCol = Math.min(from.getCol(), to.getCol());
        int maxCol = Math.max(from.getCol(), to.getCol());

        int row = minRow + 1;
        int col = minCol + 1;

        while (row < maxRow && col < maxCol) {
            Piece pieceInBetween = board.getPiece(row, col);
            if (pieceInBetween != null) {
                return  true;
            }
            row++;
            col++;
        }
        if (to.hasPiece()) return from.getPiece().getColour() == to.getPiece().getColour();
        else return false;
    }
}

