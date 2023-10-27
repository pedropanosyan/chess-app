package checkers.move;

import common.Board;
import common.Piece;
import common.Position;
import common.enums.Colour;
import common.enums.PieceType;
import common.move.Move;
import common.movementValidator.MovementValidator;
import common.exceptions.InvalidMoveException;

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
        if (!pieceInTheMiddle && colDiff == 1) return normalMovement(newBoard, from, to, false);

        Board afterMovingBoard = normalMovement(newBoard, from, to, true);
        List<Position> possiblePositions = getPossiblePositions(afterMovingBoard, to);
        if (possiblePositions.isEmpty()) return afterMovingBoard;
        return this.move(afterMovingBoard, to, possiblePositions.get(0));
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
        deleteMiddlePiece(board, from, to, pieceInTheMiddle);
        board.getPosition(from.getRow(), from.getCol()).setPiece(null);
        board.getPosition(to.getRow(), to.getCol()).setPiece(from.getPiece());
        return board;
    }

    private void deleteMiddlePiece(Board board, Position from, Position to, boolean pieceInTheMiddle) {
        if (pieceInTheMiddle) {
            int middleRow = (from.getRow() + to.getRow()) / 2;
            int middleCol = (from.getCol() + to.getCol()) / 2;
            board.getPosition(middleRow, middleCol).setPiece(null);
        }
    }

    private List<Position> getPossiblePositions(Board board, Position from) {
        List<Position> possiblePositions = new ArrayList<>();
        Piece piece = board.getPiece(from.getRow(), from.getCol());
        if (piece == null) return possiblePositions;
        if (piece.getColour() == Colour.WHITE) {
            possiblePositions.add(new Position(from.getRow() + 2, from.getCol() + 2));
            possiblePositions.add(new Position(from.getRow() + 2, from.getCol() - 2));
        } else {
            possiblePositions.add(new Position(from.getRow() - 2, from.getCol() + 2));
            possiblePositions.add(new Position(from.getRow() - 2, from.getCol() - 2));
        }

        possiblePositions.removeIf(position -> !validateMovement(board, from, position));
        return possiblePositions;
    }

}
