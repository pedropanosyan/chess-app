package chess2.validator;

import common2.Board;
import common2.Move.BasicMove;
import common2.Position;
import common2.Result.ValidatorResult;
import common2.pieceInBetween.DiagonalInBetweenStrategy;
import common2.validator.MovementValidator;

public class DiagonalValidator implements MovementValidator {

    int maxSteps;
    private final ValidatorResult falseValidatorResult = new ValidatorResult(false, null);
    private final ValidatorResult trueValidatorResult = new ValidatorResult(true, new BasicMove());

    public DiagonalValidator(int maxSteps) {
        this.maxSteps = maxSteps;
    }

    @Override
    public ValidatorResult validateMove(Board board, Position from, Position to) {
        int rowDiff = to.getRow() - from.getRow();
        int colDiff = to.getCol() - from.getCol();

        if (Math.abs(rowDiff) != Math.abs(colDiff) ) return falseValidatorResult;
        if (rowDiff == 0 || colDiff == 0) return falseValidatorResult;
        if (board.pieceInBetween(from, to, new DiagonalInBetweenStrategy())) return falseValidatorResult;
        if (Math.abs(rowDiff) > this.maxSteps) return falseValidatorResult;
        return trueValidatorResult;
    }

}

