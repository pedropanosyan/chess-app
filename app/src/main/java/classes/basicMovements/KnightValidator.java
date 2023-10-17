package classes.basicMovements;

import classes.Board;
import classes.Piece;
import classes.Position;

public class KnightValidator implements BasicMovementValidator {

    @Override
    public boolean validateMove(Board board, Position from, Position to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        boolean isValid = (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
        if (isValid && to.hasPiece()) return to.getPiece().getColour() != from.getPiece().getColour();
        return isValid;
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
