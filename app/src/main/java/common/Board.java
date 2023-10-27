package common;

import common.Piece;
import common.Position;
import common.enums.Colour;
import common.enums.PieceType;

public class Board {

    private final Position[][] board;

    public Board(Position[][] board) {
        this.board = board;
    }

    public Piece getPiece(int row, int col) {
        return board[row][col].getPiece();
    }

    public Position getPosition(int row, int col) {
        return board[row][col];
    }

    public Position getPosition(Position position) {
        return board[position.getRow()][position.getCol()];
    }

    public Position searchPiecePosition(Piece piece) {
        for (Position[] positions : board) {
            for (Position position : positions) {
                if (position.hasPiece() && position.getPiece().equals(piece)) {
                    return position;
                }
            }
        }
        return null;
    }

    public int getLength() {
        return this.board[0].length;
    }

    public Position searchPiecePosition(PieceType pieceType, Colour colour) {
        for (Position[] positions : board) {
            for (Position position : positions) {
                Piece piece = position.getPiece();
                if (piece != null && piece.getType() == pieceType && piece.getColour() == colour) {
                    return position;
                }
            }
        }
        return null;
    }

    public Position[][] getBoard() {
        return board;
    }

    public Board copyBoard() {
        int numRows = board.length;
        int numCols = board[0].length;

        Position[][] copy = new Position[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                copy[i][j] = board[i][j].copy();            }
        }
        return new Board(copy);
    }

}