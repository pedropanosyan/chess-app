package common.move;

import common.Board;
import common.Position;
import common.exceptions.InvalidMoveException;
import common.movementValidator.MovementValidator;

import java.util.List;

public interface Move {

    Board move(Board board, Position from, Position to) throws InvalidMoveException;
    List<MovementValidator> getMovementValidators();

}
