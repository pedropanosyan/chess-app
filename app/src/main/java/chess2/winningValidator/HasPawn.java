package chess2.winningValidator;

import common2.Board;
import common2.Position;
import common2.WinningValidator;
import common2.enums.Colour;
import common2.enums.PieceType;

import java.util.Map;

public class HasPawn implements WinningValidator {

    @Override
    public boolean validated(Board board, Colour colour) {
        return absenceOfPawns(board, colour);
    }

    private boolean absenceOfPawns(Board board, Colour colour) {
        PieceType pieceTypeToCompare = colour == Colour.WHITE ? PieceType.WHITE_PAWN : PieceType.BLACK_PAWN;
        return board.getBoard().values().stream()
                .noneMatch(piece -> piece.getType() == pieceTypeToCompare);
    }


}
