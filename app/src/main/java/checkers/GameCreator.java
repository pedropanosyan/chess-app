package checkers;

import common.Board;
import common.Game;
import common.GameVersion;
import common.Player;
import common.enums.Colour;
import common.enums.PieceType;

import checkers.move.KingMove;
import checkers.move.PawnMove;
import checkers.movementValidator.KingMovementValidator;
import checkers.movementValidator.PawnMovementValidator;
import checkers.winningValidator.NoPiecesRemaining;
import checkers.winningValidator.NoPlaceToMove;

import java.util.List;
import java.util.Map;

public class GameCreator {

    public static Game createClassicGame(int size) {

        PawnMovementValidator pawnMovementValidator = new PawnMovementValidator();
        KingMovementValidator kingMovementValidator = new KingMovementValidator();

        NoPiecesRemaining noPiecesRemaining = new NoPiecesRemaining();
        NoPlaceToMove noPlaceToMove = new NoPlaceToMove();

        GameVersion gameVersion = new GameVersion(Map.of(
                PieceType.PAWN, new PawnMove(List.of(pawnMovementValidator)),
                PieceType.KING, new KingMove(List.of(kingMovementValidator))
        ), List.of(noPlaceToMove, noPiecesRemaining) , size);
        Board board = CheckersBoardConstructor.initializeBoard(size);

        return new Game(gameVersion, board);
    }

}
