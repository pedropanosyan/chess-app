package common2.pieceInBetween;
import common2.Board;
import common2.Position;

public class DiagonalInBetweenStrategy implements PieceInBetweenStrategy{

    @Override
    public boolean pieceInBetween(Board board, Position from, Position to) {
        int rowDiff = to.getRow() - from.getRow();
        int colDiff = to.getCol() - from.getCol();

        int rowIncrement = rowDiff > 0 ? 1 : -1;
        int colIncrement = colDiff > 0 ? 1 : -1;

        int row = from.getRow() + rowIncrement;
        int col = from.getCol() + colIncrement;

        while ((row != to.getRow() || (col != to.getCol()))) {
            if (board.existsPosition(new Position(row, col))) return true;
            row += rowIncrement;
            col += colIncrement;
        }
        return board.existsPosition(to) && board.getPiece(from).getColour() == board.getPiece(to).getColour();
    }
}
