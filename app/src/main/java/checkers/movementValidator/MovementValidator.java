package checkers.movementValidator;


import checkers.Board;
import checkers.Position;

public interface MovementValidator {

    boolean validateMove(Board board, Position from, Position to);


}
