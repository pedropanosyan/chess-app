package chess2.validator;

import common2.Board;
import common2.Move.BasicMove;
import common2.Position;
import common2.Result.ValidatorResult;
import common2.enums.Colour;
import common2.validator.MovementValidator;

public class KnightValidator implements MovementValidator {

    private final ValidatorResult falseValidatorResult = new ValidatorResult(false, null);
    private final ValidatorResult trueValidatorResult = new ValidatorResult(true, new BasicMove());

    @Override
    public ValidatorResult validateMove(Board board, Position from, Position to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        if (positionFromEmpty(board, from)) return falseValidatorResult;
        if (!isLShape(rowDiff, colDiff)) return falseValidatorResult;

        Colour colour = board.getPiece(from).getColour();
        if (positionToOccupied(board, to, colour)) return falseValidatorResult;
        return trueValidatorResult;
    }

    private static boolean isLShape(int rowDiff, int colDiff) {
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }

    private boolean positionFromEmpty(Board board, Position from) {
        return !board.existsPosition(from);
    }

    private boolean positionToOccupied(Board board, Position to, Colour colour) {
        return board.existsPosition(to) && board.getPiece(to).getColour() == colour;
    }


}
