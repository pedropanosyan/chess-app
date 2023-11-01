package common.movementValidator;

import common.Board;
import common.Position;

import java.util.List;

public interface MovementValidator {

    boolean validateMove(Board board, Position from, Position to);
    List<Position> getPossiblePositions(Board board, Position from);

}
