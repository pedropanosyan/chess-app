package classes.basicMovements;

import classes.Board;
import classes.Piece;
import classes.Position;
import classes.enums.PieceType;

import java.util.ArrayList;
import java.util.List;

public class CoronationValidator implements BasicMovementValidator {
    @Override
    public boolean validateMove(Board board, Position from, Position to) {
        int rowDiff = to.getRow() - from.getRow();
        int colDiff = Math.abs(to.getCol() - from.getCol());
        Piece piece = board.getPiece(from.getRow(), from.getCol());

        if (movesForward(rowDiff, colDiff, piece) && isEmpty(to)) {
            return to.getRow() == board.getLength() - 1;
        } else if (movesBackward(rowDiff, colDiff, piece) && isEmpty(to)) {
            return to.getRow() == 0;
        }
        return false;

    }

    @Override
    public Board move(Board board, Position from, Position to) {
        Piece piece = board.getPiece(from.getRow(), from.getCol());
        Position[][] newBoard = board.copyBoard();
        newBoard[from.getRow()][from.getCol()].setPiece(null);
        Piece newQueen = new Piece(PieceType.QUEEN, piece.getColour());
        newQueen.setId(piece.getId());
        newBoard[to.getRow()][to.getCol()].setPiece(newQueen);
        return new Board(newBoard);
    }

    @Override
    public List<Position> getValidMoves(Board board, Position from) {
        return new ArrayList<>();
    }

    private boolean movesForward(int rowDiff, int colDiff, Piece piece) {
        return rowDiff == 1 && colDiff == 0 && piece.getType() == PieceType.WHITE_PAWN;
    }

    private boolean movesBackward(int rowDiff, int colDiff, Piece piece) {
        return rowDiff == -1 && colDiff == 0 && piece.getType() == PieceType.BLACK_PAWN;
    }

    private static boolean isEmpty(Position to) {
        return !pieceExists(to);
    }

    private static boolean pieceExists(Position to) {
        return to.hasPiece();
    }

}
