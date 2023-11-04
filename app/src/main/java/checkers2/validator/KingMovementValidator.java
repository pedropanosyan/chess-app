package checkers2.validator;

import common2.Board;
import common2.Piece;
import common2.Position;
import common2.pieceInBetween.CheckersInBetweenStrategy;
import common2.validator.MovementValidator;

public class KingMovementValidator implements MovementValidator {

    @Override
    public boolean validateMove(Board board, Position from, Position to) {
        if (board.isOutOfIndex(from) || board.isOutOfIndex(to)) return false;
        if (!board.existsPosition(from)) return false;
        if (board.existsPosition(to)) return false;

        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        if (!isDiagonal(rowDiff, colDiff)) return false;
        if (board.pieceInBetween(from, to, new CheckersInBetweenStrategy())) return false;

        return true;
    }

    private boolean isDiagonal(int rowDiff, int colDiff) {
        return rowDiff == colDiff;
    }


}
