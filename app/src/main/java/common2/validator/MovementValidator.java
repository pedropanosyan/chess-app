package common2.validator;

import common2.Board;
import common2.Position;

public interface MovementValidator {

    boolean validateMove(Board board, Position from, Position to);

}
