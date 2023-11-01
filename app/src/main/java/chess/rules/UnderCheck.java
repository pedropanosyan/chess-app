package chess.rules;

import common.Board;
import common.Position;
import common.enums.Colour;
import common.enums.PieceType;
import common.move.Move;
import common.winningValidator.WinningValidator;

import java.util.Map;

public class UnderCheck implements WinningValidator {

    @Override
    public boolean validated(Map<PieceType, Move> moveMap, Board board, Colour colour) {
        Position kingPosition = board.searchPiecePosition(PieceType.KING, colour);
        return board.isPieceUnderAttack(moveMap, kingPosition, colour);
    }
}
