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
        Position kingPosition = board.findPiece(PieceType.KING, colour);
        if (!isBeingChecked(moveMap, kingPosition, board, colour)) return false;
        return underCheck(moveMap, board, colour) && !canMove(moveMap, kingPosition, board, colour);
    }

    private boolean isBeingChecked(Map<PieceType, Move> moveMap, Position king, Board board, Colour colour) {
        return board.isPieceUnderAttack(moveMap, king, colour);
    }

    private boolean underCheck(Map<PieceType, Move> moveMap, Board board, Colour colour) {
        Board copyBoard = board.copyBoard();
        for (Position position : copyBoard.getAllPositions()) {
            if (!sameColourPiece(colour, position)) continue;
            PieceType pieceType = position.getPiece().getType();
            if (escapesCheck(moveMap, board, colour, position, copyBoard, pieceType)) return false;
        }
        return true;
    }

    private boolean escapesCheck(Map<PieceType, Move> moveMap, Board board, Colour colour, Position position, Board copyBoard, PieceType pieceType) {
        for (Position targetPosition : copyBoard.getAllPositions()) {
            if (samePosition(position, targetPosition)) continue;
            try {
                Board tempBoard = moveMap.get(pieceType).move(board, position, targetPosition);
                Position newKing = tempBoard.findPiece(PieceType.KING, colour);
                if (!isBeingChecked(moveMap, newKing, tempBoard, colour)) {
                    return true;
                }
            } catch (InvalidMoveException e) {
                continue;
            }
        }
        return false;
    }

    private static boolean samePosition(Position position, Position targetPosition) {
        return position == targetPosition;
    }

    private static boolean sameColourPiece(Colour colour, Position position) {
        return position.hasPiece() && position.getPiece().getColour() == colour;
    }

    private boolean canPieceMoveToPosition(List<MovementValidator> movementValidators, Position from, Position to, Board board) {
        for (MovementValidator movement : movementValidators) {
            if (movement.validateMove(board, from, to)) {
                return true;
            }
        }
        return false;
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
