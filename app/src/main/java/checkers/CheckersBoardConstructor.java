package checkers;

import common.Board;
import common.Piece;
import common.Position;
import common.enums.Colour;
import common.enums.PieceType;

public class CheckersBoardConstructor {

    public static Board initializeBoard(int boardSize) {
        Position[][] board = new Position[boardSize][boardSize];
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if ((row + col) % 2 == 1) {
                    Colour pieceColour = null;
                    if (row < boardSize / 2 - 1) {
                        if (row == 2) {
                            board[row][col] = new Position(row, col, new Piece(PieceType.KING, Colour.WHITE));
                            continue;
                        }
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


