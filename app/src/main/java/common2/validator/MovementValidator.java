package common2.validator;

import common2.Board;
import common2.Position;
import common2.Result.ValidatorResult;

public interface MovementValidator {

    ValidatorResult validateMove(Board board, Position from, Position to);

}
