package chess.basicMovements;

import common.Board;
import common.Piece;
import common.Position;
import common.movementValidator.MovementValidator;

import java.util.ArrayList;
import java.util.List;

public class HorizontalValidator implements MovementValidator {

    boolean canJump;
    int maxSteps;

    public HorizontalValidator(boolean canJump) {
        this.canJump = canJump;
        this.maxSteps = 8;
    }

    public HorizontalValidator(boolean canJump, int maxSteps) {
        this.canJump = canJump;
        this.maxSteps = maxSteps;
    }

    @Override
    public boolean validateMove(Board board, Position from, Position to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        if (rowDiff == 0 && colDiff > 0) {
            if (!canJump) {
                return !areObstacles(board, from, to) && colDiff <= this.maxSteps;
            } else {
                return colDiff <= this.maxSteps;
            }
        }
        return false;
    }

    @Override
    public List<Position> getPossiblePositions(Board board, Position from) {
        List<Position> possibleMoves = new ArrayList<>();

        int fromRow = from.getRow();
        int fromCol = from.getCol();

        int maxLeftSteps = Math.min(fromCol, this.maxSteps);
        int maxRightSteps = Math.min(7 - fromCol, this.maxSteps);

        for (int i = 1; i <= maxLeftSteps; i++) {
            int newCol = fromCol - i;
            Position newPosition = new Position(fromRow, newCol);

            if (validateMove(board, from, newPosition)) {
                possibleMoves.add(newPosition);
            } else {
                break;
            }
        }

        for (int i = 1; i <= maxRightSteps; i++) {
            int newCol = fromCol + i;
            Position newPosition = new Position(fromRow, newCol);

            if (validateMove(board, from, newPosition)) {
                possibleMoves.add(newPosition);
            } else {
                break;
            }
        }

        return possibleMoves;
    }

    public boolean areObstacles(Board board, Position from, Position to){
        int minCol = Math.min(from.getCol(), to.getCol())+1;
        int maxCol = Math.max(from.getCol(), to.getCol());

        while (minCol < maxCol) {
            Piece pieceInBetween = board.getPiece(from.getRow(), minCol);
            if (pieceInBetween != null) {
                return true;
            }
            minCol++;
        }
        Piece piece = board.getPiece(to.getRow(), to.getCol());
        if (piece != null) return from.getPiece().getColour() == piece.getColour();
        else return false;
    }
}
