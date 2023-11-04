package chess2.validator;

import common2.Board;
import common2.Piece;
import common2.Position;
import common2.enums.Direction;
import common2.enums.PieceType;
import common2.pieceInBetween.HorizontalInBetweenStrategy;
import common2.validator.MovementValidator;

public class CastlingValidator implements MovementValidator {

    @Override
    public boolean validateMove(Board board, Position from, Position to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        Piece king = board.getPiece(from);
        Piece rook = board.getPiece(to);

        if (!(isRightPiece(king, PieceType.KING) && isRightPiece(rook, PieceType.ROOK))) return false;

        return pieceNotMoved(king) && pieceNotMoved(rook) &&
                isCastling(colDiff, rowDiff) && !board.pieceInBetween(from, to, new HorizontalInBetweenStrategy());
    }

    private boolean isRightPiece(Piece piece, PieceType expected) {
        return piece.getType() == expected;
    }

    private static boolean isCastling(int colDiff, int rowDiff) {
        return (colDiff == 2 || colDiff == 3) && rowDiff == 0;
    }

    private static boolean pieceNotMoved(Piece piece) {
        return !piece.hasMoved();
    }


}
