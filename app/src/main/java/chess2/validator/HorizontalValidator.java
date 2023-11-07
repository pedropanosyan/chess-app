package chess2.validator;

import common2.Board;
import common2.Move.BasicMove;
import common2.Position;
import common2.Result.ValidatorResult;
import common2.pieceInBetween.HorizontalInBetweenStrategy;
import common2.validator.MovementValidator;


public class HorizontalValidator implements MovementValidator {

    int maxSteps;
    private final ValidatorResult falseValidatorResult = new ValidatorResult(false, null);
    private final ValidatorResult trueValidatorResult = new ValidatorResult(true, new BasicMove());

    public HorizontalValidator(int maxSteps) {
        this.maxSteps = maxSteps;
    }

    @Override
    public ValidatorResult validateMove(Board board, Position from, Position to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        if (!(rowDiff == 0 && colDiff > 0)) return falseValidatorResult;
        if (board.pieceInBetween(from, to, new HorizontalInBetweenStrategy())) return falseValidatorResult;
        if (colDiff > this.maxSteps) return falseValidatorResult;
        return trueValidatorResult;
    }

}
