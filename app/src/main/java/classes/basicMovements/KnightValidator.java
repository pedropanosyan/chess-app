package classes.basicMovements;

import classes.Board;
import classes.Piece;
import classes.Position;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Position> getValidMoves(Board board, Position from) {
        List<Position> validMoves = new ArrayList<>();

        int fromRow = from.getRow();
        int fromCol = from.getCol();

        int[][] knightMoves = {
                {-2, -1}, {-2, 1},
                {-1, -2}, {-1, 2},
                {1, -2}, {1, 2},
                {2, -1}, {2, 1}
        };

        for (int[] move : knightMoves) {
            int newRow = fromRow + move[0];
            int newCol = fromCol + move[1];

            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                Position newPosition = new Position(newRow, newCol);

                if (validateMove(board, from, newPosition)) {
                    validMoves.add(newPosition);
                }
            }
        }

        return validMoves;
    }


}
