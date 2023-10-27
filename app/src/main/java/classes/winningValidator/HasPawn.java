//package classes.winningValidator;
//
//import classes.Board;
//import classes.Position;
//import classes.basicMovements.BasicMovementValidator;
//import classes.enums.Colour;
//import classes.enums.PieceType;
//
//import java.util.Map;
//
//public class HasPawn implements WinningCondition{
//    @Override
//    public boolean validateWinningCondition(Map<PieceType, BasicMovementValidator[]> basicMovementValidators, Board board, Colour colour) {
//        return absenceOfPawns(board, colour);
//    }
//
//    private boolean absenceOfPawns(Board board, Colour colour) {
//        PieceType pieceTypeToCompare = colour == Colour.WHITE ? PieceType.WHITE_PAWN : PieceType.BLACK_PAWN;
//        for (Position[] positions : board.copyBoard()) {
//            for (Position position : positions) {
//                if (position.hasPiece() && position.getPiece().getColour() == colour && position.getPiece().getType() == pieceTypeToCompare) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//
//}
