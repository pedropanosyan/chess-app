//package classes;
//
//import classes.basicMovements.BasicMovementValidator;
//import classes.enums.PieceType;
//import classes.rules.RuleController;
//import classes.winningValidator.WinningCondition;
//
//import java.util.List;
//import java.util.Map;
//
//public class GameVersion {
//
//    private final Map<PieceType, BasicMovementValidator[]> basicMovements;
//    private final List<WinningCondition> winningConditions;
//
//    private final List<RuleController> rules;
//    private final int boardSize;
//
//    public  GameVersion(Map<PieceType, BasicMovementValidator[]> movements, List<RuleController> rules,
//                        List<WinningCondition> winningConditions ,int boardSize) {
//        this.basicMovements = movements;
//        this.winningConditions = winningConditions;
//        this.rules = rules;
//        this.boardSize = boardSize;
//    }
//
//
//    public int getBoardSize() {
//        return boardSize;
//    }
//
//    public List<RuleController> getRules() {
//        return this.rules;
//    }
//
//    public BasicMovementValidator[] getBasicMovementsByPiece(PieceType pieceType) {
//        return basicMovements.get(pieceType);
//    }
//    public List<WinningCondition> getWinningConditions() {
//        return winningConditions;
//    }
//
//    public Map<PieceType, BasicMovementValidator[]> getBasicMovements() {
//        return basicMovements;
//    }
//}