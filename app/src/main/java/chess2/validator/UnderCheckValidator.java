package chess2.validator;

import common2.Board;
import common2.Move.BasicMove;
import common2.Piece;
import common2.Position;
import common2.Result.ValidatorResult;
import common2.enums.Colour;
import common2.enums.PieceType;
import common2.validator.MovementValidator;

import java.util.Map;

public class UnderCheckValidator implements MovementValidator {

    BasicMove basicMove = new BasicMove();
    private final ValidatorResult falseValidatorResult = new ValidatorResult(false, null);
    private final ValidatorResult trueValidatorResult = new ValidatorResult(true, null);

    @Override
    public ValidatorResult validateMove(Board board, Position from, Position to) {
        Colour colour = board.getPiece(from).getColour();
        if (basicMove.move(board, from, to).isPieceUnderAttack(PieceType.KING, colour)) return falseValidatorResult;
        return trueValidatorResult;
    }
}
