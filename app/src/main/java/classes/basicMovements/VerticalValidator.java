package classes.basicMovements;

import classes.Board;
import classes.Piece;
import classes.Position;

import java.util.ArrayList;
import java.util.List;

public class VerticalValidator implements BasicMovementValidator {

    boolean canJump;
    int maxSteps;

    public VerticalValidator(boolean canJump) {
        this.canJump = canJump;
        this.maxSteps = 8;
    }

    public VerticalValidator(boolean canJump, int maxSteps) {
        this.canJump = canJump;
        this.maxSteps = maxSteps;
    }

    @Override
    public boolean validateMove(Board board, Position from, Position to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        if (colDiff == 0 && rowDiff > 0) {
            if (!canJump) {
                return !areObstacles(board, from, to) && rowDiff <= this.maxSteps;
            } else {
                return colDiff <= this.maxSteps;
            }
        } else return false;
    }

    public boolean areObstacles(Board board, Position from, Position to) {
        int minRow = Math.min(from.getRow(), to.getRow())+1;
        int maxRow = Math.max(from.getRow(), to.getRow());

        while (minRow < maxRow) {
            Piece pieceInBetween = board.getPiece(minRow, from.getCol());
            if (pieceInBetween != null) {
                return true;
            }
            minRow++;
        }
        if (to.hasPiece()) return from.getPiece().getColour() == to.getPiece().getColour();
        else return false;
    }

    @Override
    public Board move(Board board, Position from, Position to) {
        Piece piece = board.getPiece(from.getRow(), from.getCol());
        Position[][] newBoard = board.copyBoard();
        newBoard[from.getRow()][from.getCol()].setPiece(null);
        newBoard[to.getRow()][to.getCol()].setPiece(piece);
        return new Board(newBoard);
    }

    @Override
    public List<Position> getValidMoves(Board board, Position from) {
        List<Position> possibleMoves = new ArrayList<>();

        int fromRow = from.getRow();
        int fromCol = from.getCol();

        int maxUpwardSteps = Math.min(fromRow, this.maxSteps);
        int maxDownwardSteps = Math.min(7 - fromRow, this.maxSteps);

        for (int i = 1; i <= maxUpwardSteps; i++) {
            int newRow = fromRow - i;
            Position newPosition = new Position(newRow, fromCol);

            if (validateMove(board, from, newPosition)) {
                possibleMoves.add(newPosition);
            } else { break; }
        }

        for (int i = 1; i <= maxDownwardSteps; i++) {
            int newRow = fromRow + i;
            Position newPosition = new Position(newRow, fromCol);

            if (validateMove(board, from, newPosition)) {
                possibleMoves.add(newPosition);
            } else {
                break;
            }
        }
        return possibleMoves;
    }


}
