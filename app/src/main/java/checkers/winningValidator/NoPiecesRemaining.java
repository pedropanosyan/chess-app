package checkers.winningValidator;

import checkers.Board;
import checkers.Position;
import checkers.enums.Colour;
import checkers.enums.PieceType;
import checkers.move.Move;

import java.util.Map;

public class NoPiecesRemaining implements WinningValidator {

    @Override
    public boolean validated(Map<PieceType, Move> moveMap, Board board, Colour colour) {
        for (int row = 0; row < board.getLength(); row++) {
            for (int col = 0; col < board.getLength(); col++) {
                Position position = board.getPosition(row, col);
                if (position.hasPiece() && isOppositeColour(colour, position)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isOppositeColour(Colour colour, Position position) {
        return position.getPiece().getColour() != colour;
    }


}
