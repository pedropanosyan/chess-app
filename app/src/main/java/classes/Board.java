package classes;


import classes.basicMovements.BasicMovementValidator;
import classes.enums.Colour;
import classes.enums.PieceType;

import java.util.Map;

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

    public boolean isPieceUnderAttack(Map<PieceType, BasicMovementValidator[]> basicMovementValidators, Position kingPosition, Colour colour) {
        for (Position[] positions : board) {
            for (Position position : positions) {
                Piece piece = position.getPiece();
                if (piece != null && piece.getColour() != colour) {
                    BasicMovementValidator[] movements = basicMovementValidators.get(piece.getType());
                    for (BasicMovementValidator movement : movements) {
                        if (movement.validateMove(this, position, kingPosition)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean kingUnderAttack(Map<PieceType, BasicMovementValidator[]> basicMovementValidators, Position kingPosition, Colour colour) {
        for (Position[] positions : board) {
            for (Position position : positions) {
                Piece piece = position.getPiece();
                if (piece != null && piece.getColour() != colour) {
                    BasicMovementValidator[] movements = basicMovementValidators.get(piece.getType());
                    for (BasicMovementValidator movement : movements) {
                        if (movement.validateMove(this, position, kingPosition)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public Position[][] copyBoard() {
        int numRows = board.length;
        int numCols = board[0].length;

        Position[][] copy = new Position[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                copy[i][j] = board[i][j].copy();            }
        }
        return copy;
    }

}