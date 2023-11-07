package checkers.move;

import common.move.Move;
import common.Board;
import common.Piece;
import common.Position;
import common.enums.PieceType;
import common.exceptions.InvalidMoveException;
import common.movementValidator.MovementValidator;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

public class PawnMove implements Move {

    List<MovementValidator> movementValidators;

    public PawnMove(List<MovementValidator> movementValidators) {
        this.movementValidators = movementValidators;
    }

    @Override
    public Board move(Board board, Position from, Position to) throws InvalidMoveException {

        int colDiff = Math.abs(to.getCol() - from.getCol());
        if (!validateMovement(board, from, to)) throw new InvalidMoveException("Invalid move");

        Board newBoard = board.copyBoard();
        boolean pieceInTheMiddle = pieceInTheMiddle(board, from, to);

        if (isCoronation(board, to)) return coronationMovement(newBoard, from, to, pieceInTheMiddle);
        if (colDiff == 1) return normalMovement(newBoard, from, to, pieceInTheMiddle);
        if (!pieceInTheMiddle && colDiff == 2) return normalMovement(newBoard, from, to, false);

        Board afterMovingBoard = normalMovement(newBoard, from, to, true);
        List<Position> possiblePositions = fetchPossiblePositions(afterMovingBoard, to);
        if (possiblePositions.isEmpty()) return afterMovingBoard;
        return this.move(afterMovingBoard, to, possiblePositions.get(0));
    }

    @Override
    public List<MovementValidator> getMovementValidators() {
        return this.movementValidators;
    }

    private boolean validateMovement(Board board, Position from, Position to) {
        for (MovementValidator movement : movementValidators) {
            if (!movement.validateMove(board, from, to)) return false;
        }
        return true;
    }

    private boolean isCoronation(Board board, Position to) {
        return to.getRow() == board.getLength()-1 || to.getRow() == 0;
    }

    private boolean pieceInTheMiddle(Board board, Position from, Position to) {
        int middleRow = (from.getRow() + to.getRow()) / 2;
        int middleCol = (from.getCol() + to.getCol()) / 2;
        Position middlePosition = board.getPosition(middleRow, middleCol);
        return middlePosition.hasPiece();
    }

    private Board coronationMovement(Board board, Position from, Position to, boolean pieceInTheMiddle) {
        deleteMiddlePiece(board, from, to, pieceInTheMiddle);
        Piece fromPiece = board.getPiece(from.getRow(), from.getCol());
        Piece piece = new Piece(PieceType.KING, fromPiece.getColour());
        piece.setId(fromPiece.getId());
        board.getPosition(from.getRow(), from.getCol()).setPiece(null);
        board.getPosition(to.getRow(), to.getCol()).setPiece(piece);
        return board;
    }

    private Board normalMovement(Board board, Position from, Position to, boolean pieceInTheMiddle) {
        Board copyBoard = board.copyBoard();
        Piece piece = copyBoard.getPiece(from);
        deleteMiddlePiece(copyBoard, from, to, pieceInTheMiddle);
        copyBoard.getPosition(from).setPiece(null);
        copyBoard.getPosition(to).setPiece(piece);
        return copyBoard;
    }

    private void deleteMiddlePiece(Board board, Position from, Position to, boolean pieceInTheMiddle) {
        if (pieceInTheMiddle) {
            int middleRow = (from.getRow() + to.getRow()) / 2;
            int middleCol = (from.getCol() + to.getCol()) / 2;
            board.getPosition(middleRow, middleCol).setPiece(null);
        }
    }

    private List<Position> fetchPossiblePositions(Board board, Position from) {
        List<Position> possiblePositions = new ArrayList<>();
        for (MovementValidator validator : movementValidators) {
            for (Position position : board.getAllPositions()) {
                if (validator.validateMove(board, from, position)) {
                    if (colDiffEqual2(from, position) && pieceInTheMiddle(board, from, position)) possiblePositions.add(position);
                }
            }
        }
        return possiblePositions;
    }

    private static boolean colDiffEqual2(Position from, Position position) {
        return Math.abs(from.getCol() - position.getCol()) == 2;
    }


}
