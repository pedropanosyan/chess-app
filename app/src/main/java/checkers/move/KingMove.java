package checkers.move;

import common.Board;
import common.Piece;
import common.Position;
import common.exceptions.InvalidMoveException;
import common.movementValidator.MovementValidator;
import common.move.Move;

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
        List<Position> possiblePositions = fetchPossiblePositions(afterMovingBoard, to);
        if (possiblePositions.isEmpty()) return afterMovingBoard;
        return this.move(afterMovingBoard, board.getPosition(to), board.getPosition(possiblePositions.get(0)));
    }

    public List<MovementValidator> getMovementValidators() {
        return this.movementValidators;
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

    private List<Position> fetchPossiblePositions(Board board, Position from) {
        List<Position> possiblePositions = new ArrayList<>();
        for (MovementValidator validator : movementValidators) {
            possiblePositions.addAll(validator.getPossiblePositions(board, from));
        }
        return possiblePositions;
    }

}
