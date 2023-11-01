package chess.basicMovements;

import common.Board;
import common.Position;
import common.movementValidator.MovementValidator;

import java.util.ArrayList;
import java.util.List;

public class KnightValidator implements MovementValidator {

    @Override
    public boolean validateMove(Board board, Position from, Position to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        boolean isValid = (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
        if (isValid && to.hasPiece()) return to.getPiece().getColour() != from.getPiece().getColour();
        return isValid;
    }

    @Override
    public List<Position> getPossiblePositions(Board board, Position from) {
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
