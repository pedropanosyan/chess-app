package common;
import com.sun.pisces.PiscesRenderer;
import common.enums.Colour;
import common.enums.PieceType;
import common.move.Move;
import common.movementValidator.MovementValidator;

import java.util.List;
import java.util.Map;

public class Board {

    private final Position[][] board;

    public Board(Position[][] board) {
        this.board = board;
    }

    public Piece getPiece(int row, int col) {
        return board[row][col].getPiece();
    }

    public Piece getPiece(Position position) {
        return board[position.getRow()][position.getCol()].getPiece();
    }

    public Position getPosition(int row, int col) {
        return board[row][col];
    }

    public Position getPosition(Position position) {
        return board[position.getRow()][position.getCol()];
    }

    public Position[] getAllPositions() {
        int size = board.length;
        Position[] positions = new Position[size*size];
        int index = 0;
        for (Position[] row : board) {
            for (Position position : row) {
                positions[index++] = position;
            }
        }
        return positions;
    }

    public boolean isPieceUnderAttack(Map<PieceType, Move> moveMap, Position pieceToAnalyze, Colour colour) {
        for (Position position : this.getAllPositions()) {
            Piece piece = position.getPiece();
            if (piece != null && piece.getColour() != colour) {
                List<MovementValidator> movements = moveMap.get(piece.getType()).getMovementValidators();
                for (MovementValidator movement : movements) {
                    if (movement.validateMove(this, position, pieceToAnalyze)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public int getLength() {
        return this.board[0].length;
    }

    public Position findPiece(PieceType pieceType, Colour colour) {
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