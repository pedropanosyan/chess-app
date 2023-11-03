package common.gameCreator;

import chess.basicMovements.*;
import chess.move.BasicMove;
import chess.winningValidator.CheckMateValidator;
import common.*;
import common.enums.Colour;
import common.enums.PieceType;

import java.util.List;
import java.util.Map;

public class ChessGameConstructor implements GameCreatorInterface{

    public static Game createClassicGame(int size) {
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
        ), List.of(new CheckMateValidator()), size);

        Board board = initializeBoard(gameVersion);

        return new Game(gameVersion, board);
    }

    public static Board initializeBoard(GameVersion gameVersion) {
        int size = gameVersion.getBoardSize();

        Position[][] board = new Position[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = new Position(row, col);
            }
        }
        initializeChessPieces(board);
        return new Board(board);
    }

    private static void initializeChessPieces(Position[][] board) {
        PieceType[] initialRow = {
                PieceType.ROOK,
                PieceType.KNIGHT,
                PieceType.BISHOP,
                PieceType.QUEEN,
                PieceType.KING,
                PieceType.BISHOP,
                PieceType.KNIGHT,
                PieceType.ROOK,
        };
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Colour pieceColour = (row < 2) ? Colour.WHITE : Colour.BLACK;
                if (row == 1 || row == 6) {
                    if (pieceColour == Colour.WHITE) {
                        board[row][col].setPiece(new Piece(PieceType.WHITE_PAWN, pieceColour));
                    } else {
                        board[row][col].setPiece(new Piece(PieceType.BLACK_PAWN, pieceColour));
                    }
                } else if (row == 0 || row == 7) {
                    board[row][col].setPiece(new Piece(initialRow[col], pieceColour));
                }
            }
        }
    }
}
