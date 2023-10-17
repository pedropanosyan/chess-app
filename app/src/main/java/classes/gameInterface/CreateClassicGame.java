package classes.gameInterface;

import classes.Game;
import classes.GameVersion;
import classes.Player;
import classes.basicMovements.*;
import classes.enums.PieceType;
import classes.rules.RuleController;
import classes.rules.UnderCheck;
import classes.winningValidator.CheckMateValidator;

import java.util.List;
import java.util.Map;

public class CreateClassicGame {

    public static Game createClassicGame(Player p1, Player p2) {
        DiagonalValidator diagonalValidator = new DiagonalValidator(false);
        DiagonalValidator diagonalKingValidator = new DiagonalValidator(false, 1);
        HorizontalValidator horizontalValidator = new HorizontalValidator(false);
        HorizontalValidator horizontalKingValidator = new HorizontalValidator(false, 1);
        VerticalValidator verticalValidator = new VerticalValidator(false);
        VerticalValidator verticalKingValidator = new VerticalValidator(false, 1);
        KnightValidator knightValidator = new KnightValidator();
        PawnValidator whitePawnValidator = new PawnValidator(false, 1, 0);
        PawnValidator blackPawnValidator = new PawnValidator(false, 0, 1);
        CastlingValidator castlingValidator = new CastlingValidator();

        RuleController underCheck = new UnderCheck();

        GameVersion gameVersion = new GameVersion(Map.of(
                PieceType.WHITE_PAWN, new BasicMovementValidator[]{whitePawnValidator},
                PieceType.BLACK_PAWN, new BasicMovementValidator[]{blackPawnValidator},
                PieceType.ROOK, new BasicMovementValidator[]{horizontalValidator, verticalValidator},
                PieceType.KNIGHT, new BasicMovementValidator[]{knightValidator},
                PieceType.BISHOP, new BasicMovementValidator[]{diagonalValidator},
                PieceType.QUEEN, new BasicMovementValidator[]{horizontalValidator, verticalValidator, diagonalValidator},
                PieceType.KING, new BasicMovementValidator[]{horizontalKingValidator, verticalKingValidator, diagonalKingValidator, castlingValidator}
        ), List.of(underCheck), List.of(new CheckMateValidator()) , 8);

        return new Game(gameVersion, new Player[]{p1, p2});
    }
}
