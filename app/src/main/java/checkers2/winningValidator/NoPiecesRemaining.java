package checkers2.winningValidator;

import common2.Board;
import common2.Piece;
import common2.Position;
import common2.WinningValidator;
import common2.enums.Colour;

import java.util.Map;

public class NoPiecesRemaining implements WinningValidator {

    @Override
    public boolean validated(Board board, Colour colour) {
        for (Piece piece : board.getBoard().values()) {
            if (isSameColour(getOppositeColour(colour), piece)) return false;
        }
        return true;
    }

    private Colour getOppositeColour(Colour colour) {
        return colour == Colour.WHITE ? Colour.BLACK : Colour.WHITE;
    }

    private boolean isSameColour(Colour colour, Piece piece) {
        return piece.getColour() == colour;
    }
}
