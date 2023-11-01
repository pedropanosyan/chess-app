package chess.winningValidator;

import common.Board;
import common.Position;
import common.enums.Colour;
import common.enums.PieceType;
import common.move.Move;
import common.winningValidator.WinningValidator;

import java.util.Map;

public class HasPawn implements WinningValidator {

    @Override
    public boolean validated(Map<PieceType, Move> moveMap, Board board, Colour colour) {
        return absenceOfPawns(board, colour);
    }

    private boolean absenceOfPawns(Board board, Colour colour) {
        PieceType pieceTypeToCompare = colour == Colour.WHITE ? PieceType.WHITE_PAWN : PieceType.BLACK_PAWN;
        Board copyBoard = board.copyBoard();
        for (Position[] positions : copyBoard.getBoard()) {
            for (Position position : positions) {
                if (position.hasPiece() && position.getPiece().getColour() == colour && position.getPiece().getType() == pieceTypeToCompare) {
                    return false;
                }
            }
        }
        return true;
    }


}
