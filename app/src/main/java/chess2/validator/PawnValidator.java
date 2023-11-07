package chess2.validator;

import common2.Board;
import common2.Move.BasicMove;
import common2.Position;
import common2.Result.ValidatorResult;
import common2.enums.Colour;
import common2.enums.PieceType;
import common2.validator.MovementValidator;

public class PawnValidator implements MovementValidator {

    int maxSteps;
    private final ValidatorResult falseValidatorResult = new ValidatorResult(false, null);
    private final ValidatorResult trueValidatorResult = new ValidatorResult(true, new BasicMove());

    public PawnValidator(int maxSteps) {
        this.maxSteps = maxSteps;
    }

    @Override
    public ValidatorResult validateMove(Board board, Position from, Position to) {
        int rowDiff = to.getRow() - from.getRow();
        int colDiff = Math.abs(to.getCol() - from.getCol());

        if (!board.existsPosition(from)) return falseValidatorResult;
        if (isVerticalMovementValid(board.getPiece(from).getType(), rowDiff, colDiff, hasMoved(board, from))) return trueValidatorResult;

        if (!board.existsPosition(to)) return falseValidatorResult;
        if (!isDiagonalMovementValid(board, from, to)) return falseValidatorResult;
        return trueValidatorResult;
    }

    private static boolean hasMoved(Board board, Position from) {
        return board.existsPosition(from) && board.getPiece(from).hasMoved();
    }

    private boolean isVerticalMovementValid(PieceType pieceType, int rowDiff, int colDiff, Boolean hasMoved) {
        if (movesBackward(pieceType, rowDiff, colDiff)) return true;
        if (movesForward(pieceType, rowDiff, colDiff)) return true;
        if (isDoubleMoveForward(pieceType, rowDiff, colDiff, hasMoved)) return true;
        if (isDoubleBackward(pieceType, rowDiff, colDiff, hasMoved)) return true;
        else return false;
    }

    private boolean isDiagonalMovementValid(Board board, Position from, Position to) {
        int rowDiff = to.getRow() - from.getRow();
        int colDiff = Math.abs(to.getCol() - from.getCol());

        if (!board.existsPosition(to)) return false;

        Colour fromColour = board.getPiece(from).getColour();
        Colour toColour = board.getPiece(to).getColour();

        if (!areDifferentColors(fromColour, toColour)) return false;
        if (movesDiagonalForward(rowDiff, colDiff)) return true;
        if (movesDiagonalBackward(rowDiff, colDiff)) return true;
        else return false;
    }

    private static boolean movesDiagonalForward(int rowDiff, int colDiff) {
        return rowDiff == 1 && colDiff == 1;
    }

    private static boolean movesDiagonalBackward(int rowDiff, int colDiff) {
        return rowDiff == -1 && colDiff == 1;
    }

    private boolean movesForward(PieceType pieceType, int rowDiff, int colDiff) {
        return rowDiff == 1 && colDiff == 0 && pieceType == PieceType.WHITE_PAWN;
    }

    private boolean movesBackward(PieceType pieceType, int rowDiff, int colDiff) {
        return rowDiff == -1 && colDiff == 0 && pieceType == PieceType.BLACK_PAWN;
    }

    private boolean isDoubleMoveForward(PieceType pieceType, int rowDiff, int colDiff, boolean hasMoved) {
        return rowDiff == 2 && colDiff == 0 && pieceType == PieceType.WHITE_PAWN && !hasMoved;
    }

    private boolean isDoubleBackward(PieceType pieceType, int rowDiff, int colDiff, Boolean hasMoved) {
        return rowDiff == -2 && colDiff == 0 && pieceType == PieceType.BLACK_PAWN && !hasMoved;
    }

    private static boolean areDifferentColors(Colour from, Colour to) {
        return from != to;
    }



}

