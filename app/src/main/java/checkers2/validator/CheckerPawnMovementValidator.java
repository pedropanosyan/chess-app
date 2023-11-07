package checkers2.validator;

import common2.Board;
import common2.Move.BasicMove;
import common2.Piece;
import common2.Position;
import common2.Result.ValidatorResult;
import common2.enums.Colour;
import common2.pieceInBetween.CheckersInBetweenStrategy;
import common2.validator.MovementValidator;

public class CheckerPawnMovementValidator implements MovementValidator {

    private final ValidatorResult falseValidatorResult = new ValidatorResult(false, new BasicMove());
    private final ValidatorResult trueValidatorResult = new ValidatorResult(true, new BasicMove());

    @Override
    public ValidatorResult validateMove(Board board, Position from, Position to) {
        Piece fromPiece = board.getPiece(from);
        Piece toPiece = board.getPiece(to);

        if (isPieceNull(fromPiece))  return falseValidatorResult;
        if (!isPieceNull(toPiece))  return falseValidatorResult;

        if (board.isOutOfIndex(from) || board.isOutOfIndex(to)) return falseValidatorResult;

        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        if (!isDiagonal(rowDiff, colDiff)) return falseValidatorResult;
        if (!isMovingForward(from, to, fromPiece.getColour())) return falseValidatorResult;

        if (board.pieceInBetween(from, to, new CheckersInBetweenStrategy())) return falseValidatorResult;
        if (isDoubleDiagonal(rowDiff, colDiff) && !pieceInTheMiddle(board, from, to)) return falseValidatorResult;
        return trueValidatorResult;
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

    private boolean pieceInTheMiddle(Board board, Position from, Position to) {
        int middleRow = (from.getRow() + to.getRow()) / 2;
        int middleCol = (from.getCol() + to.getCol()) / 2;

        Piece middlePiece = board.getPiece(new Position(middleRow, middleCol));
        Piece fromPiece = board.getPiece(from);

        return middlePiece != null && middlePiece.getColour() != fromPiece.getColour();
    }
}
