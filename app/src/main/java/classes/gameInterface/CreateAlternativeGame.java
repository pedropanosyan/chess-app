//package classes.gameInterface;
//
//import classes.GameVersion;
//import classes.Player;
//import classes.basicMovements.*;
//import classes.enums.PieceType;
//import classes.rules.RuleController;
//import classes.rules.UnderCheck;
//import classes.winningValidator.CheckMateValidator;
//import classes.winningValidator.HasPawn;
//
//import java.util.List;
//import java.util.Map;
//
//public class CreateAlternativeGame {
//
//    public static Game createAlternativeGame(Player p1, Player p2) {
//        DiagonalValidator diagonalValidator = new DiagonalValidator(false);
//        DiagonalValidator diagonalKingValidator = new DiagonalValidator(false, 4);
//        HorizontalValidator horizontalValidator = new HorizontalValidator(false);
//        HorizontalValidator horizontalKingValidator = new HorizontalValidator(false, 4);
//        VerticalValidator verticalValidator = new VerticalValidator(false);
//        VerticalValidator verticalKingValidator = new VerticalValidator(false, 1);
//        KnightValidator knightValidator = new KnightValidator();
//        PawnValidator whitePawnValidator = new PawnValidator(false, 3, 1);
//        PawnValidator blackPawnValidator = new PawnValidator(false, 1, 3);
//        CastlingValidator castlingValidator = new CastlingValidator();
//
//        RuleController underCheck = new UnderCheck();
//
//        GameVersion gameVersion = new GameVersion(Map.of(
//                PieceType.WHITE_PAWN, new BasicMovementValidator[]{whitePawnValidator},
//                PieceType.BLACK_PAWN, new BasicMovementValidator[]{blackPawnValidator},
//                PieceType.CHANCELLOR, new BasicMovementValidator[]{horizontalValidator, verticalValidator, knightValidator},
//                PieceType.KNIGHT, new BasicMovementValidator[]{knightValidator},
//                PieceType.BISHOP, new BasicMovementValidator[]{diagonalValidator},
//                PieceType.QUEEN, new BasicMovementValidator[]{horizontalValidator, verticalValidator, diagonalValidator},
//                PieceType.KING, new BasicMovementValidator[]{horizontalKingValidator, verticalKingValidator, diagonalKingValidator, castlingValidator}
//        ), List.of(underCheck), List.of(new CheckMateValidator(), new HasPawn()) , 8);
//
//        return new Game(gameVersion, new Player[]{p1, p2});
//    }
//
//}
