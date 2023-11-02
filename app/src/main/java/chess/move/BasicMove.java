package chess.move;

import common.Board;
import common.Piece;
import common.Position;
import common.exceptions.InvalidMoveException;
import common.move.Move;
import common.movementValidator.MovementValidator;

import java.util.List;

public class BasicMove implements Move {

    List<MovementValidator> movementValidators;

    public BasicMove(List<MovementValidator> movementValidators) {
        this.movementValidators = movementValidators;
    }

    @Override
    public Board move(Board board, Position from, Position to) throws InvalidMoveException {
        Piece piece = board.getPiece(from.getRow(), from.getCol());
        if (!validateMovement(board, from, to)) throw new InvalidMoveException("Invalid move");

        Board newBoard = board.copyBoard();
        newBoard.getBoard()[from.getRow()][from.getCol()].setPiece(null);
        newBoard.getBoard()[to.getRow()][to.getCol()].setPiece(piece);
        return newBoard;
    }

    @Override
    public List<MovementValidator> getMovementValidators() {
        return movementValidators;
    }

    private boolean validateMovement(Board board, Position from, Position to) {
        for (MovementValidator movement : movementValidators) {
            if (movement.validateMove(board, from, to)) return true;
        }
        return false;
    }

}
