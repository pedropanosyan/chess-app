package chess2.validator;

import common2.Board;
import common2.Piece;
import common2.Position;
import common2.enums.PieceType;
import common2.validator.MovementValidator;

public class CoronationValidator implements MovementValidator {

    @Override
    public boolean validateMove(Board board, Position from, Position to) {
        int rowDiff = to.getRow() - from.getRow();
        int colDiff = Math.abs(to.getCol() - from.getCol());
        Piece piece = board.getPiece(from);

        if (movesForward(rowDiff, colDiff, piece.getType()) && isEmpty(board, to)) {
            return board.isLastRow(to);
        } else if (movesBackward(rowDiff, colDiff, piece) && isEmpty(board, to)) {
            return board.isFirstRow(to);
        }
        return false;

    }

    private boolean movesForward(int rowDiff, int colDiff, PieceType pieceType) {
        return rowDiff == 1 && colDiff == 0 && pieceType == PieceType.WHITE_PAWN;
    }

    private boolean movesBackward(int rowDiff, int colDiff, Piece piece) {
        return rowDiff == -1 && colDiff == 0 && piece.getType() == PieceType.BLACK_PAWN;
    }

    private static boolean isEmpty(Board board, Position to) {;
        return !board.existsPosition(to);
    }


}
