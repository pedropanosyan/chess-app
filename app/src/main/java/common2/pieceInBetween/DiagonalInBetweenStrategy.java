package common2.pieceInBetween;

import common2.Board;
import common2.Piece;
import common2.Position;

public class DiagonalInBetweenStrategy implements PieceInBetweenStrategy{

    @Override
    public boolean pieceInBetween(Board board, Position from, Position to) {
        int minRow = Math.min(from.getRow(), to.getRow())+1;
        int maxRow = Math.max(from.getRow(), to.getRow());
        int minCol = Math.min(from.getCol(), to.getCol())+1;
        int maxCol = Math.max(from.getCol(), to.getCol());
        while (minRow < maxRow && minCol < maxCol) {
            Piece pieceInBetween = board.getPiece(new Position(minRow, minCol));
            if (pieceInBetween != null) {
                return true;
            }
            minRow++;
            minCol++;
        }
        if (board.existsPosition(to)) return board.getPiece(from).getColour() == board.getPiece(to).getColour();
        else return false;
    }
}
