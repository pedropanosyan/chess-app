package checkers2.validator;

import common2.Board;
import common2.Piece;
import common2.Position;
import common2.enums.Colour;
import common2.pieceInBetween.CheckersInBetweenStrategy;
import common2.validator.MovementValidator;

public class CheckerPawnMovementValidator implements MovementValidator {

    @Override
    public boolean validateMove(Board board, Position from, Position to) {
        Piece fromPiece = board.getPiece(from);
        Piece toPiece = board.getPiece(to);

        if (isPieceNull(fromPiece))  return false;
        if (!isPieceNull(toPiece))  return false;

        if (board.isOutOfIndex(from) || board.isOutOfIndex(to)) return false;

        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        if (!isDiagonal(rowDiff, colDiff)) return false;
        if (!isMovingForward(from, to, fromPiece.getColour())) return false;

        if (isDoubleDiagonal(rowDiff, colDiff)) return board.pieceInBetween(from, to, new CheckersInBetweenStrategy());
        return true;
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

    private boolean isMovingForward(Position from, Position to, Colour colour) {
        if (colour == Colour.WHITE) {
            return to.getRow() >= from.getRow();
        } else {
            return to.getRow() <= from.getRow();
        }
    }
}
