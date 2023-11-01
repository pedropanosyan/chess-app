package chess.basicMovements;

import common.Board;
import common.Piece;
import common.Position;
import common.enums.PieceType;
import common.movementValidator.MovementValidator;

import java.util.ArrayList;
import java.util.List;

public class CoronationValidator implements MovementValidator {

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
    public List<Position> getPossiblePositions(Board board, Position from) {
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
