package common2.factory;

import checkers2.validator.CheckerKingMovementValidator;
import checkers2.validator.CheckerPawnMovementValidator;
import checkers2.winningValidator.NoPiecesRemaining;
import checkers2.winningValidator.NoPlaceToMove;
import chess2.validator.*;
import chess2.winningValidator.CheckMateValidator;
import chess2.winningValidator.HasPawn;
import common2.Board;
import common2.Game;
import common2.GameVersion;
import common2.Move.Move;
import common2.WinningValidator;
import common2.enums.Colour;
import common2.enums.PieceType;
import common2.turn.TwoPlayersTurn;
import common2.validator.MovementValidator;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GameFactory {

    public Game createClassicChessGame() {
        List<WinningValidator> winningValidators = List.of(new CheckMateValidator());
        List<MovementValidator> globalValidators = List.of(new UnderCheckValidator());

        DiagonalValidator diagonalValidator = new DiagonalValidator(8);
        DiagonalValidator diagonalKingValidator = new DiagonalValidator(1);
        HorizontalValidator horizontalValidator = new HorizontalValidator(8);
        HorizontalValidator horizontalKingValidator = new HorizontalValidator(1);
        VerticalValidator verticalValidator = new VerticalValidator(8);
        VerticalValidator verticalKingValidator = new VerticalValidator(1);
        KnightValidator knightValidator = new KnightValidator();
        PawnValidator pawnValidator = new PawnValidator(1);
        CastlingValidator castlingValidator = new CastlingValidator();
        CoronationValidator coronationValidator = new CoronationValidator();

        GameVersion gameVersion = new GameVersion(winningValidators, globalValidators, Map.of(
                PieceType.WHITE_PAWN, List.of(coronationValidator, pawnValidator),
                PieceType.BLACK_PAWN, List.of(coronationValidator, pawnValidator),
                PieceType.ROOK, List.of(horizontalValidator, verticalValidator),
                PieceType.KNIGHT, List.of(knightValidator),
                PieceType.BISHOP, List.of(diagonalValidator),
                PieceType.QUEEN, List.of(horizontalValidator, verticalValidator, diagonalValidator),
                PieceType.KING, List.of(horizontalKingValidator, verticalKingValidator, diagonalKingValidator, castlingValidator)
        ));

        Board board = BoardFactory.createClassicChessBoard(gameVersion);
        TwoPlayersTurn turn = new TwoPlayersTurn(Colour.WHITE);
        return new Game(board, turn, gameVersion);
    }

    public Game createClassicCheckersGame() {
        List<MovementValidator> globalValidators = List.of();
        List<WinningValidator> winningValidators = List.of(new NoPiecesRemaining(), new NoPlaceToMove());

        CheckerPawnMovementValidator checkerPawnMovementValidator = new CheckerPawnMovementValidator();
        CheckerKingMovementValidator checkerKingMovementValidator = new CheckerKingMovementValidator();

        GameVersion gameVersion = new GameVersion(winningValidators, globalValidators, Map.of(
                PieceType.PAWN, List.of(checkerPawnMovementValidator),
                PieceType.KING, List.of(checkerKingMovementValidator)
        ));

        TwoPlayersTurn turn = new TwoPlayersTurn(Colour.WHITE);
        Board board = BoardFactory.createClassicCheckerBoard(gameVersion);
        return new Game(board, turn, gameVersion);
    }
}
