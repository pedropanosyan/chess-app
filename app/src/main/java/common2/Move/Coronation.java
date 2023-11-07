package common2.Move;

import common2.Board;
import common2.Piece;
import common2.Position;
import common2.enums.PieceType;

import java.util.List;

public class Coronation implements Move {

    private final PieceType pieceType;

    public Coronation(PieceType piece) {
        this.pieceType = piece;
    }

    @Override
    public Board move(Board board, Position from, Position to) {
        Piece fromPiece = board.getPiece(from);
        Piece newPiece = new Piece(List.of(), pieceType, fromPiece .getColour(), fromPiece .getId());
        Board board1 = board.removePiece(from);
        return board1.addPiece(to, newPiece);
    }

}
