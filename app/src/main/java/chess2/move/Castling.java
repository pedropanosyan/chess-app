package chess2.move;

import common2.Board;
import common2.Move.Move;
import common2.Piece;
import common2.Position;
import common2.enums.Colour;

import java.util.List;

public class Castling implements Move {

    @Override
    public Board move(Board board, Position from, Position to) {
        int colDiff = (to.getCol() - from.getCol());

        Piece king = board.getPiece(from);

        if (colDiff == -2) {
            Piece rook = board.getPiece(new Position(from.getRow(), 0));
            Board board1 = board.removePiece(new Position(from.getRow(), 0));
            Board board2 = board1.removePiece(from);
            Board board3 = board2.addPiece(new Position(from.getRow(), 2), king);
            return board3.addPiece(new Position(from.getRow(), 3), rook);
        }
        else if (colDiff == 2){
            Piece rook = board.getPiece(new Position(from.getRow(), board.getSize()));
            Board board1 = board.removePiece(new Position(from.getRow(), board.getSize()));
            Board board2 = board1.removePiece(from);
            Board board3 = board2.addPiece(new Position(from.getRow(), board.getSize()-1), king);
            return board3.addPiece(new Position(from.getRow(), board.getSize()-2), rook);
        }
        return board;
    }


}
