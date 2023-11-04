package chess2.validator;

import common2.Board;
import common2.Piece;
import common2.Position;
import common2.enums.Direction;
import common2.pieceInBetween.DiagonalInBetweenStrategy;
import common2.validator.MovementValidator;

public class DiagonalValidator implements MovementValidator {

    int maxSteps;

    public DiagonalValidator(int maxSteps) {
        this.maxSteps = maxSteps;
    }

    @Override
    public boolean validateMove(Board board, Position from, Position to) {
        int rowDiff = to.getRow() - from.getRow();
        int colDiff = to.getCol() - from.getCol();

        if (Math.abs(rowDiff) != Math.abs(colDiff) ) return false;
        if (rowDiff == 0 || colDiff == 0) return false;

        return !board.pieceInBetween(from, to, new DiagonalInBetweenStrategy()) && Math.abs(rowDiff) <= this.maxSteps;
    }

}

