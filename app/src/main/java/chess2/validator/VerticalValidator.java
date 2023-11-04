package chess2.validator;


import common2.Board;
import common2.Position;
import common2.enums.Direction;
import common2.pieceInBetween.VerticalInBetweenStrategy;
import common2.validator.MovementValidator;

public class VerticalValidator implements MovementValidator {

    int maxSteps;

    public VerticalValidator(int maxSteps) {
        this.maxSteps = maxSteps;
    }

    @Override
    public boolean validateMove(Board board, Position from, Position to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        if (!isVertical(colDiff, rowDiff)) return false;
        return !board.pieceInBetween(from, to, new VerticalInBetweenStrategy()) && rowDiff <= this.maxSteps;
    }

    private static boolean isVertical(int colDiff, int rowDiff) {
        return colDiff == 0 && rowDiff > 0;
    }

}
