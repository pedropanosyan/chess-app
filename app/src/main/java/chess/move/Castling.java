package chess.move;

import common.Board;
import common.Piece;
import common.Position;
import common.enums.Colour;
import common.move.Move;
import common.movementValidator.MovementValidator;

import java.util.List;

public class Castling implements Move {

    @Override
    public Board move(Board board, Position from, Position to) {
        int size = board.getLength()-1;
        int colDiff = (to.getCol() - from.getCol());
        Piece king = board.getPiece(from.getRow(), from.getCol());
        Piece rook = getRook(board, colDiff, king.getColour());
        Board newBoard = board.copyBoard();
        if (colDiff == -2) {
            newBoard.getBoard()[from.getRow()][0].setPiece(null);
            newBoard.getBoard()[from.getRow()][from.getCol()].setPiece(null);
            newBoard.getBoard()[from.getRow()][2].setPiece(king);
            newBoard.getBoard()[from.getRow()][3].setPiece(rook);
        }
        else if (colDiff == 2){
            newBoard.getBoard()[from.getRow()][size].setPiece(null);
            newBoard.getBoard()[from.getRow()][from.getCol()].setPiece(null);
            newBoard.getBoard()[from.getRow()][size-1].setPiece(king);
            newBoard.getBoard()[from.getRow()][size-2].setPiece(rook);
        }
        return newBoard;
    }

    @Override
    public List<MovementValidator> getMovementValidators() {
        return null;
    }

    private Piece getRook(Board board, int colDiff, Colour colour) {
        int size = board.getLength()-1;
        if (colDiff == 2) {
            if (colour == Colour.WHITE) {
                return board.getPiece(0, size);
            }
            else {
                return board.getPiece(size, size);
            }
        }
        else if (colDiff == -2) {
            if (colour == Colour.WHITE) {
                return board.getPiece(0, 0);
            }
            else {
                return board.getPiece(size, 0);
            }
        }
        return null;
    }
}
