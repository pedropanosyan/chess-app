package checkers.movementValidator;

import checkers.Board;
import checkers.Piece;
import checkers.Position;
import checkers.enums.Colour;

public class KingMovementValidator implements MovementValidator {

    @Override
    public boolean validateMove(Board board, Position from, Position to) {
        if (outOfIndex(board.getLength()-1, from, to)) { return false; }

        Piece fromPiece = board.getPiece(from.getRow(), from.getCol());
        Piece toPiece = board.getPiece(to.getRow(), to.getCol());

        if (isPieceNull(fromPiece)) { return false; }
        if (!isPieceNull(toPiece)) { return false; }


        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        if (!isDiagonal(rowDiff, colDiff)) { return false; }
        if (sameColourPieceInTheMiddle(board, board.getPosition(from), board.getPosition(to))) { return false; }

        return true;
    }

    private boolean nullPieces(Position from, Position to) {
        return from.getPiece() != null && to.getPiece() != null;
    }

    private boolean isDiagonal(int rowDiff, int colDiff) {
        return rowDiff == colDiff;
    }

    private boolean arePiecesFromDifferentColour(Position x, Position y) {
        return x.getPiece().getColour() != y.getPiece().getColour();
    }

    private boolean pieceInTheMiddle(Board board, Position from, Position to) {
        int middleRow = (from.getRow() + to.getRow()) / 2;
        int middleCol = (from.getCol() + to.getCol()) / 2;
        Position middlePosition = board.getPosition(middleRow, middleCol);
        return middlePosition.hasPiece() && arePiecesFromDifferentColour(from, middlePosition);
    }

    private boolean sameColourPieceInTheMiddle(Board board, Position from, Position to) {
        int rowDirection = (to.getRow() - from.getRow()) > 0 ? 1 : -1;
        int colDirection = (to.getCol() - from.getCol()) > 0 ? 1 : -1;

        int currentRow = from.getRow() + rowDirection;
        int currentCol = from.getCol() + colDirection;

        while (currentRow != to.getRow() && currentCol != to.getCol()) {
            Position currentPosition = board.getPosition(currentRow, currentCol);
            if (currentPosition.hasPiece() && currentPosition.getPiece().getColour() == from.getPiece().getColour()) {
                return true;
            }

            currentRow += rowDirection;
            currentCol += colDirection;
        }

        return false;
    }

    private boolean outOfIndex(int size, Position from, Position to) {
        return from.getRow() < 0 || from.getRow() > size || from.getCol() < 0 || from.getCol() > size ||
                to.getRow() < 0 || to.getRow() > size || to.getCol() < 0 || to.getCol() > size;
    }

    private boolean isPieceNull(Piece piece) {
        return piece == null;
    }

}
