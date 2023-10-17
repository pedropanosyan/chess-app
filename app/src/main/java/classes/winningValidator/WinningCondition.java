package classes.winningValidator;

import classes.Board;
import classes.basicMovements.BasicMovementValidator;
import classes.enums.Colour;
import classes.enums.PieceType;

import java.util.Map;

public interface WinningCondition {

    boolean validateWinningCondition(Map<PieceType, BasicMovementValidator[]> basicMovementValidators , Board board, Colour colour);

}
