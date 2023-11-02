package chess.basicMovements;

import common.Board;
import common.Position;
import common.movementValidator.MovementValidator;

import java.util.ArrayList;
import java.util.List;

public class KnightValidator implements MovementValidator {

    @Override
    public boolean validateMove(Board board, Position from, Position to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        boolean isValid = (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
        if (isValid && to.hasPiece()) return to.getPiece().getColour() != from.getPiece().getColour();
        return isValid;
    }

}
