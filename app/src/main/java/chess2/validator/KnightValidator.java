package chess2.validator;

import common2.Board;
import common2.Position;
import common2.enums.Colour;
import common2.validator.MovementValidator;

public class KnightValidator implements MovementValidator {

    @Override
    public boolean validateMove(Board board, Position from, Position to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        if (positionFromEmpty(board, from)) return false;
        if (!isLShape(rowDiff, colDiff)) return false;

        Colour colour = board.getPiece(from).getColour();
        return !positionToOccupied(board, to, colour);
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
