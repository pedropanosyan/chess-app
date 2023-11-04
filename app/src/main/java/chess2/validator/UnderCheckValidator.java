package chess2.validator;

import common.Board;
import common.Position;
import common.enums.Colour;
import common.enums.PieceType;
import common.move.Move;
import common.movementValidator.MovementValidator;

import java.util.Map;

public class UnderCheckValidator implements MovementValidator {

    Map<PieceType, Move> moveMap;

    public UnderCheckValidator(Map<PieceType, Move> moveMap) {
        this.moveMap = moveMap;
    }

    @Override
    public boolean validateMove(Board board, Position from, Position to) {
        Colour colour = from.getPiece().getColour();
        Position kingPosition = board.findPiece(PieceType.KING, colour);
        return board.isPieceUnderAttack(moveMap, kingPosition, colour);
    }
}
