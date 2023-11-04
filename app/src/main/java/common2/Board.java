package common2;

import common2.enums.Direction;
import common2.pieceInBetween.PieceInBetweenStrategy;

import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;
    private final int size;

    public Board(Map<Position, Piece> board, int size) {
        this.board = board;
        this.size = size;
    }

    public Piece getPiece(Position position) {
        return board.get(position);
    }

    public boolean existsPosition(Position position) {
        return board.containsKey(position);
    }

    public boolean isLastRow(Position position) {
        return position.getRow() == size - 1;
    }

    public boolean isFirstRow(Position position) {
        return position.getCol() == 0;
    }

    public boolean pieceInBetween(Position from, Position to, PieceInBetweenStrategy pieceInBetweenStrategy) {
        return pieceInBetweenStrategy.pieceInBetween(this, from, to);
    }

    public boolean isOutOfIndex(Position position) {
        return position.getRow() < 0 || position.getRow() > size-1 || position.getCol() < 0 || position.getCol() > size-1;
    }

}
