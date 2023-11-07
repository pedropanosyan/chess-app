package checkers2.validator;

import common2.Board;
import common2.Move.BasicMove;
import common2.Position;
import common2.Result.ValidatorResult;
import common2.pieceInBetween.CheckersInBetweenStrategy;
import common2.validator.MovementValidator;

public class CheckerKingMovementValidator implements MovementValidator {

    private final ValidatorResult falseValidatorResult = new ValidatorResult(false, null);
    private final ValidatorResult trueValidatorResult = new ValidatorResult(true, new BasicMove());

    @Override
    public ValidatorResult validateMove(Board board, Position from, Position to) {
        if (board.isOutOfIndex(from) || board.isOutOfIndex(to)) return falseValidatorResult;
        if (!board.existsPosition(from)) return falseValidatorResult;
        if (board.existsPosition(to)) return falseValidatorResult;

        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        if (!isDiagonal(rowDiff, colDiff)) return falseValidatorResult;
        if (board.pieceInBetween(from, to, new CheckersInBetweenStrategy())) return falseValidatorResult;

        return trueValidatorResult;
    }

    private boolean isDiagonal(int rowDiff, int colDiff) {
        return rowDiff == colDiff;
    }


}
