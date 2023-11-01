package chess.gameInterface;

import chess.rules.UnderCheck;
import common.ConstructorHelper;
import chess.basicMovements.*;

import chess.move.BasicMove;
import chess.winningValidator.CheckMateValidator;
import common.Board;
import common.GameVersion;
import common.Player;
import common.enums.PieceType;
import common.Game;

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
        CoronationValidator coronationValidator = new CoronationValidator();

        BasicMove whitePawnMove = new BasicMove(List.of(whitePawnValidator, coronationValidator));
        BasicMove blackPawnMove = new BasicMove(List.of(blackPawnValidator, coronationValidator));
        BasicMove rookMove = new BasicMove(List.of(horizontalValidator, verticalValidator));
        BasicMove knightMove = new BasicMove(List.of(knightValidator));
        BasicMove bishopMove = new BasicMove(List.of(diagonalValidator));
        BasicMove queenMove = new BasicMove(List.of(horizontalValidator, verticalValidator, diagonalValidator));
        BasicMove kingMove = new BasicMove(List.of(horizontalKingValidator, verticalKingValidator, diagonalKingValidator, castlingValidator));

        GameVersion gameVersion = new GameVersion(Map.of(
                PieceType.WHITE_PAWN, whitePawnMove,
                PieceType.BLACK_PAWN, blackPawnMove,
                PieceType.ROOK, rookMove,
                PieceType.KNIGHT, knightMove,
                PieceType.BISHOP, bishopMove,
                PieceType.QUEEN, queenMove,
                PieceType.KING, kingMove
        ), List.of(new UnderCheck(), new CheckMateValidator()), 8);

        return new Game(gameVersion, new Board(ConstructorHelper.initializeBoard(gameVersion)), new Player[]{p1, p2});
    }
}
