package checkers2.winningValidator;

import common2.Board;
import common2.Piece;
import common2.Position;
import common2.WinningValidator;
import common2.enums.Colour;
import common2.enums.PieceType;


import java.util.List;
import java.util.Map;

public class NoPlaceToMove implements WinningValidator {

    @Override
    public boolean validated(Board board, Colour colour) {
        Map<Position, Piece> boardMap = board.getBoard();
        for (Piece piece : boardMap.values()) {
            if (!isSameColour(colour, piece)) continue;
            if (board.hasValidMovements(piece)) return false;
        }
        return false;
    }

    private boolean isSameColour(Colour colour, Piece piece) {
        return piece.getColour() == colour;
    }
}
