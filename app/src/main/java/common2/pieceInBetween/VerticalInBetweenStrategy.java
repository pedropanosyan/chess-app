package common2.pieceInBetween;

import common2.Board;
import common2.Piece;
import common2.Position;

public class VerticalInBetweenStrategy implements PieceInBetweenStrategy{

    @Override
    public boolean pieceInBetween(Board board, Position from, Position to) {
        int minRow = Math.min(from.getRow(), to.getRow())+1;
        int maxRow = Math.max(from.getRow(), to.getRow());
        while (minRow < maxRow) {
            Piece pieceInBetween = board.getPiece(new Position(minRow, from.getCol()));
            if (pieceInBetween != null) {
                return true;
            }
            minRow++;
        }
        if (board.existsPosition(to)) return board.getPiece(from).getColour() == board.getPiece(to).getColour();
        else return false;
    }
}
