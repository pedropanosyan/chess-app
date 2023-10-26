package checkers.move;

import checkers.Board;
import checkers.Piece;
import checkers.Position;
import common.exceptions.InvalidMoveException;
import checkers.movementValidator.MovementValidator;

import java.util.ArrayList;
import java.util.List;

public class KingMove implements Move {

    List<MovementValidator> movementValidators;

    public KingMove(List<MovementValidator> movementValidators) {
        this.movementValidators = movementValidators;
    }

    @Override
    public Board move(Board board, Position from, Position to) throws InvalidMoveException {

        if (!validateMovement(board, from, to)) throw new InvalidMoveException("Invalid move");

        Board newBoard = board.copyBoard();

        Board afterMovingBoard = kingMovement(newBoard, board.getPosition(from), to);
        if (!otherColourPieceInTheMiddle(board, board.getPosition(from), to)) return afterMovingBoard;
        List<Position> possiblePositions = getPossiblePositions(afterMovingBoard, to);
        if (possiblePositions.isEmpty()) return afterMovingBoard;
        return this.move(afterMovingBoard, board.getPosition(to), board.getPosition(possiblePositions.get(0)));
    }

    public List<MovementValidator> getMovementValidators() {
        return movementValidators;
    }

    private boolean validateMovement(Board board, Position from, Position to) {
        for (MovementValidator movement : movementValidators) {
            if (!movement.validateMove(board, from, to)) return false;
        }
        return true;
    }

    private Board kingMovement(Board board, Position from, Position to) {
        Piece piece = from.getPiece();
        int rowDirection = (to.getRow() - from.getRow() > 0) ? 1 : -1;
        int colDirection = (to.getCol() - from.getCol() > 0) ? 1 : -1;

        int currentRow = from.getRow() + rowDirection;
        int currentCol = from.getCol() + colDirection;
        while (currentRow != to.getRow() && currentCol != to.getCol()) {
            board.getPosition(currentRow, currentCol).setPiece(null);
            currentRow += rowDirection;
            currentCol += colDirection;
        }
        board.getPosition(from.getRow(), from.getCol()).setPiece(null);
        board.getPosition(to.getRow(), to.getCol()).setPiece(piece);

        return board;
    }

    private List<Position> getPossiblePositions(Board board, Position from) {
        List<Position> possiblePositions = new ArrayList<>();

        int fromRow = from.getRow();
        int fromCol = from.getCol();

        int[] rowOffsets = { -1, -1, 1, 1 };
        int[] colOffsets = { -1, 1, -1, 1 };

        for (int i = 0; i < 4; i++) {
            for (int step = 1; step <= board.getLength(); step++) {
                int newRow = fromRow + rowOffsets[i] * step;
                int newCol = fromCol + colOffsets[i] * step;
                if (!isValidPosition(newRow, newCol, board.getLength())) break;
                possiblePositions.add(new Position(newRow, newCol));
            }
        }
        possiblePositions.removeIf(position -> !validateMovement(board, from, position));
        possiblePositions.removeIf(position -> !otherColourPieceInTheMiddle(board, board.getPosition(from), board.getPosition(position)));
        return possiblePositions;
    }

    private boolean isValidPosition(int row, int col, int size) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }



    private boolean otherColourPieceInTheMiddle(Board board, Position from, Position to) {
        int rowDirection = (to.getRow() - from.getRow()) > 0 ? 1 : -1;
        int colDirection = (to.getCol() - from.getCol()) > 0 ? 1 : -1;

        int currentRow = from.getRow() + rowDirection;
        int currentCol = from.getCol() + colDirection;

        while (currentRow != to.getRow() && currentCol != to.getCol()) {
            Position currentPosition = board.getPosition(currentRow, currentCol);
            if (currentPosition.hasPiece() && currentPosition.getPiece().getColour() != from.getPiece().getColour()) {
                return true;
            }

            currentRow += rowDirection;
            currentCol += colDirection;
        }
        return false;
    }
}
