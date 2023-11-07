package chess2.validator;

import common2.Board;
import common2.Move.BasicMove;
import common2.Move.Coronation;
import common2.Piece;
import common2.Position;
import common2.Result.ValidatorResult;
import common2.enums.PieceType;
import common2.validator.MovementValidator;

public class CoronationValidator implements MovementValidator {

    private final ValidatorResult falseValidatorResult = new ValidatorResult(false, null);
    private final ValidatorResult trueValidatorResult = new ValidatorResult(true, new Coronation(PieceType.KING));

    @Override
    public ValidatorResult validateMove(Board board, Position from, Position to) {
        int rowDiff = to.getRow() - from.getRow();
        int colDiff = Math.abs(to.getCol() - from.getCol());
        Piece piece = board.getPiece(from);

        if (movesForward(rowDiff, colDiff, piece.getType()) && !board.existsPosition(to)) {
            if (board.isLastRow(to)) return trueValidatorResult;
        } else if (movesBackward(rowDiff, colDiff, piece) && !board.existsPosition(to)) {
            if (board.isFirstRow(to)) return trueValidatorResult;
        }
        return falseValidatorResult;
    }

    private boolean movesForward(int rowDiff, int colDiff, PieceType pieceType) {
        return rowDiff == 1 && colDiff == 0 && pieceType == PieceType.WHITE_PAWN;
    }

    private boolean movesBackward(int rowDiff, int colDiff, Piece piece) {
        return rowDiff == -1 && colDiff == 0 && piece.getType() == PieceType.BLACK_PAWN;
    }


}
