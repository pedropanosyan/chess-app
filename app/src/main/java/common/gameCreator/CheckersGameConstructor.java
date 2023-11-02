package common.gameCreator;

import checkers.move.KingMove;
import checkers.move.PawnMove;
import checkers.movementValidator.KingMovementValidator;
import checkers.movementValidator.PawnMovementValidator;
import checkers.winningValidator.NoPiecesRemaining;
import checkers.winningValidator.NoPlaceToMove;
import common.*;
import common.enums.Colour;
import common.enums.PieceType;

import java.util.List;
import java.util.Map;

public class CheckersGameConstructor implements GameCreatorInterface{

    public static Game createClassicGame(int size) {

        PawnMovementValidator pawnMovementValidator = new PawnMovementValidator();
        KingMovementValidator kingMovementValidator = new KingMovementValidator();

        NoPiecesRemaining noPiecesRemaining = new NoPiecesRemaining();
        NoPlaceToMove noPlaceToMove = new NoPlaceToMove();

        GameVersion gameVersion = new GameVersion(Map.of(
                PieceType.PAWN, new PawnMove(List.of(pawnMovementValidator)),
                PieceType.KING, new KingMove(List.of(kingMovementValidator))
        ), List.of(noPlaceToMove, noPiecesRemaining) , size);

        Board board = initializeBoard(size);

        return new Game(gameVersion, board);
    }

    public static Board initializeBoard(int boardSize) {
        Position[][] board = new Position[boardSize][boardSize];
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if ((row + col) % 2 == 1) {
                    Colour pieceColour = null;
                    if (row < boardSize / 2 - 1) {
                        pieceColour = Colour.WHITE;
                    } else if (row >= boardSize / 2 + 1) {
                        pieceColour = Colour.BLACK;
                    }
                    if (pieceColour != null) {
                        board[row][col] = new Position(row, col, new Piece(PieceType.PAWN, pieceColour));
                    } else {
                        board[row][col] = new Position(row, col, null);
                    }
                } else {
                    board[row][col] = new Position(row, col, null);
                }
            }
        }
        return new Board(board);
    }
}


