package common2.Move;

import common2.Board;
import common2.Piece;
import common2.Position;

public class BasicMove implements Move{

    @Override
    public Board move(Board board, Position from, Position to) {
        Piece piece = board.getPiece(from);
        Board board1 = board.removePiece(from);
        return board1.addPiece(to, piece);
    }
}
