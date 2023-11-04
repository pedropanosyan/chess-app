package chess2.validator;

import common2.Board;
import common2.Position;
import common2.enums.Direction;
import common2.pieceInBetween.HorizontalInBetweenStrategy;
import common2.validator.MovementValidator;


public class HorizontalValidator implements MovementValidator {

    int maxSteps;

    public HorizontalValidator(int maxSteps) {
        this.maxSteps = maxSteps;
    }

    @Override
    public boolean validateMove(Board board, Position from, Position to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        if (!(rowDiff == 0 && colDiff > 0)) return false;
        return !board.pieceInBetween(from, to, new HorizontalInBetweenStrategy()) && colDiff <= this.maxSteps;
    }

}
