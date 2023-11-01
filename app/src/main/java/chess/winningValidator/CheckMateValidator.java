package chess.winningValidator;

import common.Board;
import common.Position;
import common.enums.Colour;
import common.enums.PieceType;
import common.exceptions.InvalidMoveException;
import common.move.Move;
import common.movementValidator.MovementValidator;
import common.winningValidator.WinningValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckMateValidator implements WinningValidator {


    @Override
    public boolean validated(Map<PieceType, Move> moveMap, Board board, Colour colour) {
        Position kingPosition = board.searchPiecePosition(PieceType.KING, colour);
        if (!isBeingChecked(moveMap, kingPosition, board, colour)) return false;
        try {
            return stillUnderCheck(moveMap, kingPosition, board, colour) && !canMove(moveMap, kingPosition, board, colour);
        }
        catch (InvalidMoveException e) {
            return false;
        }
    }

    private boolean isBeingChecked(Map<PieceType, Move> moveMap, Position king, Board board, Colour colour) {
        return board.isPieceUnderAttack(moveMap, king, colour);
    }

    private boolean stillUnderCheck(Map<PieceType, Move> moveMap, Position king, Board board, Colour colour) throws InvalidMoveException {
        Board copyBoard = board.copyBoard();
        for (Position[] positions : copyBoard.getBoard()) {
            for (Position position : positions) {
                if (position.hasPiece() && position.getPiece().getColour() == colour) {
                    PieceType pieceType = position.getPiece().getType();
                    List<MovementValidator> movements = moveMap.get(pieceType).getMovementValidators();
                    for (MovementValidator movement : movements) {
                        List<Position> validPositions = movement.getPossiblePositions(board, position);
                        for (Position validPosition : validPositions) {
                            if (movement.validateMove(board, position, validPosition)) {
                                Board tempBoard = moveMap.get(pieceType).move(board, position, validPosition);
                                Position newKing = tempBoard.searchPiecePosition(PieceType.KING, colour);
                                if (newKing == null) continue;
                                System.out.println(newKing);
                                if (!isBeingChecked(moveMap, newKing, tempBoard, colour)) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean canMove(Map<PieceType, Move> moveMap, Position king, Board board, Colour colour) {
        Position[] positions = getAdjacentPositions(board, king);
        for (Position position : positions) {
            if (isBeingChecked(moveMap, position, board, colour)) {
                return false;
            }
        }
        return true;
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

}
