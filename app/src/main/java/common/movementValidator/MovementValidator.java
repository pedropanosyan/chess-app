package common.movementValidator;

import common.Board;
import common.Position;

public interface MovementValidator {

    boolean validateMove(Board board, Position from, Position to);
}
