package checkers.movementValidator;

import common.Board;
import common.Piece;
import common.Position;
import common.enums.Colour;
import common.movementValidator.MovementValidator;

import java.util.ArrayList;
import java.util.List;

public class PawnMovementValidator implements MovementValidator {

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
        if (isDoubleDiagonal(rowDiff, colDiff)) { return pieceInTheMiddle(board, from, to); }
        if (!isMovingForward(from, to)) { return false; }

        return true;
    }

    private boolean outOfIndex(int size, Position from, Position to) {
        return from.getRow() < 0 || from.getRow() > size || from.getCol() < 0 || from.getCol() > size ||
                to.getRow() < 0 || to.getRow() > size || to.getCol() < 0 || to.getCol() > size;
    }

    private boolean isPieceNull(Piece piece) {
        return piece == null;
    }

    private boolean isDiagonal(int rowDiff, int colDiff) {
        return rowDiff == colDiff && rowDiff <= 2;
    }

    private boolean isDoubleDiagonal(int rowDiff, int colDiff) {
        return rowDiff == 2 && colDiff == 2;
    }

    private boolean isMovingForward(Position from, Position to) {
        Colour pieceColor = from.getPiece().getColour();
        if (pieceColor == Colour.WHITE) {
            return to.getRow() >= from.getRow();
        } else {
            return to.getRow() <= from.getRow();
        }
    }

    private boolean arePiecesFromDifferentColour(Piece x, Piece y) {
        return x.getColour() != y.getColour();
    }

    private boolean pieceInTheMiddle(Board board, Position from, Position to) {
        int middleRow = (from.getRow() + to.getRow()) / 2;
        int middleCol = (from.getCol() + to.getCol()) / 2;
        Position middlePosition = board.getPosition(middleRow, middleCol);

        Piece middlePiece = board.getPiece(middlePosition.getRow(), middlePosition.getCol());
        Piece fromPiece = board.getPiece(from.getRow(), from.getCol());
        return middlePosition.hasPiece() && arePiecesFromDifferentColour(fromPiece, middlePiece);
    }

    @Override
    public List<Position> getPossiblePositions(Board board, Position from) {
        List<Position> possiblePositions = new ArrayList<>();
        Piece piece = board.getPiece(from.getRow(), from.getCol());
        if (piece == null) return possiblePositions;
        if (piece.getColour() == Colour.WHITE) {
            possiblePositions.add(new Position(from.getRow() + 2, from.getCol() + 2));
            possiblePositions.add(new Position(from.getRow() + 2, from.getCol() - 2));
        } else {
            possiblePositions.add(new Position(from.getRow() - 2, from.getCol() + 2));
            possiblePositions.add(new Position(from.getRow() - 2, from.getCol() - 2));
        }

        possiblePositions.removeIf(position -> !validateMove(board, from, position));
        return possiblePositions;
    }

}
