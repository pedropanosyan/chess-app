package checkers.winningValidator;

import common.Board;
import common.Piece;
import common.Position;
import common.enums.Colour;
import common.enums.PieceType;
import common.move.Move;
import common.movementValidator.MovementValidator;
import common.winningValidator.WinningValidator;

import java.util.List;
import java.util.Map;

public class NoPlaceToMove implements WinningValidator {

    @Override
    public boolean validated(Map<PieceType, Move> moveMap, Board board, Colour colour) {
        for (int row = 0; row < board.getLength(); row++) {
            for (int col = 0; col < board.getLength(); col++) {
                Position position = board.getPosition(row, col);
                if (position.hasPiece() && isOppositeColor(colour, position)) {
                    if (hasValidMoves(moveMap, board, position)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean hasValidMoves(Map<PieceType, Move> moveMap, Board board, Position from) {
        for (int row = 0; row < board.getLength(); row++) {
            for (int col = 0; col < board.getLength(); col++) {
                Position to = board.getPosition(row, col);
                Piece piece = from.getPiece();
                List<MovementValidator> validator = moveMap.get(piece.getType()).getMovementValidators();
                for (MovementValidator movementValidator : validator) {
                    if (movementValidator.validateMove(board, from, to)) {
                        return true;
                    }
                }
            }
        }
        return false;

    }

    private boolean isOppositeColor(Colour colour, Position position) {
        return position.getPiece().getColour() != colour;
    }
}
