//package classes.rules;
//
//import classes.Board;
//import classes.Position;
//import classes.basicMovements.BasicMovementValidator;
//import classes.enums.Colour;
//import classes.enums.PieceType;
//
//import java.util.Map;
//
//public class UnderCheck implements RuleController {
//
//
//    @Override
//    public boolean validateRule(Map<PieceType, BasicMovementValidator[]> basicMovementValidators, Board board, Colour colour) {
//        Position kingPosition = board.searchPiecePosition(PieceType.KING, colour);
//        return !board.isPieceUnderAttack(basicMovementValidators, kingPosition, colour);
//    }
//
//}
