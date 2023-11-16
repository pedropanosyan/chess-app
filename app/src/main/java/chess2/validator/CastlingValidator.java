package chess2.validator;

import chess2.move.Castling;
import common2.Board;
import common2.Move.BasicMove;
import common2.Move.Move;
import common2.Piece;
import common2.Position;
import common2.Result.ValidatorResult;
import common2.enums.PieceType;
import common2.pieceInBetween.HorizontalInBetweenStrategy;
import common2.validator.MovementValidator;

public class CastlingValidator implements MovementValidator {

    private final ValidatorResult falseValidatorResult = new ValidatorResult(false, null);
    private final ValidatorResult trueValidatorResult = new ValidatorResult(true, new Castling());


    @Override
    public ValidatorResult validateMove(Board board, Position from, Position to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        if (board.existsPosition(to)) return falseValidatorResult;

        Piece king = board.getPiece(from);
        Piece rook = findRook(board, from, to);

        if (!(isRightPiece(king, PieceType.KING) && isRightPiece(rook, PieceType.ROOK))) return falseValidatorResult;
        if (king.hasMoved() || rook.hasMoved()) return falseValidatorResult;
        if (!isCastling(colDiff, rowDiff)) return falseValidatorResult;
        if (board.pieceInBetween(from, to, new HorizontalInBetweenStrategy())) return falseValidatorResult;
        return trueValidatorResult;
    }

    private boolean isRightPiece(Piece piece, PieceType expected) {
        return piece != null && piece.getType() == expected;
    }

    private static boolean isCastling(int colDiff, int rowDiff) {
        return (colDiff == 2 || colDiff == 3) && rowDiff == 0;
    }

    private Piece findRook(Board board, Position from, Position to) {
        if (to.getCol() == 0) {
            return board.getPiece(new Position(from.getRow(), 0));
        } else {
            return board.getPiece(new Position(from.getRow(), board.getSize()));
        }
    }

}
