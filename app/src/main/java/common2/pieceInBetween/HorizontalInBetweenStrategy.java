package common2.pieceInBetween;

import common2.Board;
import common2.Piece;
import common2.Position;

public class HorizontalInBetweenStrategy implements PieceInBetweenStrategy{

    @Override
    public boolean pieceInBetween(Board board, Position from, Position to) {
        int minCol = Math.min(from.getCol(), to.getCol())+1;
        int maxCol = Math.max(from.getCol(), to.getCol());
        while (minCol < maxCol) {
            Piece pieceInBetween = board.getPiece(new Position(from.getRow(), minCol));
            if (pieceInBetween != null) {
                return true;
            }
            minCol++;
        }
        if (board.existsPosition(to)) return board.getPiece(from).getColour() == board.getPiece(to).getColour();
        else return false;
    }
}
