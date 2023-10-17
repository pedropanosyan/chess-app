package classes.rules;

import classes.Board;
import classes.Position;
import classes.basicMovements.BasicMovementValidator;
import classes.enums.Colour;
import classes.enums.PieceType;

import java.util.ArrayList;
import java.util.Map;

public class CheckMate implements RuleController{

    @Override
    public boolean validateRule(Map<PieceType, BasicMovementValidator[]> basicMovementValidators, Board board, Colour colour) {
        Position kingPosition = board.searchPiecePosition(PieceType.KING, colour);
        if (isBeingChecked(basicMovementValidators, kingPosition, board, colour)) return false;
        return canMove(basicMovementValidators, kingPosition, board, colour);
    }

    private boolean isBeingChecked(Map<PieceType, BasicMovementValidator[]> basicMovementValidators, Position king, Board board, Colour colour) {
        return board.isPieceUnderAttack(basicMovementValidators, king, colour);
    }

    private boolean canMove(Map<PieceType, BasicMovementValidator[]> basicMovementValidators, Position king, Board board, Colour colour) {
        Position[] positions = getAdjacentPositions(board, king);
        for (Position position : positions) {
            if (!isBeingChecked(basicMovementValidators, position, board, colour)) {
                return true;
            }
        }
        return false;
    }

    private Position[] getAdjacentPositions(Board board, Position king) {
        int row = king.getRow();
        int col = king.getCol();
        int numRows = 8;
        int numCols = 8;

        ArrayList<Position> adjacentPositions = new ArrayList<>();

        int[] rowOffsets = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] colOffsets = { -1, 0, 1, -1, 1, -1, 0, 1 };

        for (int i = 0; i < 8; i++) {
            int newRow = row + rowOffsets[i];
            int newCol = col + colOffsets[i];

            if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols) {
                adjacentPositions.add(board.getPosition(newRow, newCol));
            }
        }
        return adjacentPositions.toArray(new Position[0]);
    }

    private boolean canCover(Map<PieceType, BasicMovementValidator[]> basicMovementValidators, Position king, Board board, Colour colour) {
        Position[] positions = getAdjacentPositions(board, king);
        for (Position position : positions) {
            if (!isBeingChecked(basicMovementValidators, position, board, colour)) {
                return true;
            }
        }
        return false;
    }
}
