package common2.pieceInBetween;

import common2.Board;
import common2.Piece;
import common2.Position;
import common2.enums.Colour;

public class CheckersInBetweenStrategy implements PieceInBetweenStrategy {

    @Override
    public boolean pieceInBetween(Board board, Position from, Position to) {
        int rowDirection = (to.getRow() - from.getRow()) > 0 ? 1 : -1;
        int colDirection = (to.getCol() - from.getCol()) > 0 ? 1 : -1;

        Colour colour = board.getPiece(from).getColour();

        int currentRow = from.getRow() + rowDirection;
        int currentCol = from.getCol() + colDirection;

        while (currentRow != to.getRow() && currentCol != to.getCol()) {
            Position currentPosition = new Position(currentRow, currentCol);
            if (!board.existsPosition(currentPosition)) return false;
            Piece piece = board.getPiece(currentPosition);
            if (piece.getColour() != colour) {
                return false;
            }

            currentRow += rowDirection;
            currentCol += colDirection;
        }
        return true;
    }
}
