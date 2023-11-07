package chess2.validator;


import common2.Board;
import common2.Move.BasicMove;
import common2.Position;
import common2.Result.ValidatorResult;
import common2.pieceInBetween.VerticalInBetweenStrategy;
import common2.validator.MovementValidator;

public class VerticalValidator implements MovementValidator {

    int maxSteps;
    private final ValidatorResult falseValidatorResult = new ValidatorResult(false, null);
    private final ValidatorResult trueValidatorResult = new ValidatorResult(true, new BasicMove());

    public VerticalValidator(int maxSteps) {
        this.maxSteps = maxSteps;
    }

    @Override
    public ValidatorResult validateMove(Board board, Position from, Position to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        if (!isVertical(colDiff, rowDiff)) return falseValidatorResult;
        if (board.pieceInBetween(from, to, new VerticalInBetweenStrategy())) return falseValidatorResult;
        if (rowDiff > this.maxSteps) return falseValidatorResult;
        return trueValidatorResult;
    }

    private static boolean isVertical(int colDiff, int rowDiff) {
        return colDiff == 0 && rowDiff > 0;
    }

}
