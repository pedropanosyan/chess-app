package checkers2.move;

import common2.Board;
import common2.Move.BasicMove;
import common2.Move.Coronation;
import common2.Move.Move;
import common2.Piece;
import common2.Position;
import common2.enums.Colour;
import common2.enums.PieceType;
import common2.pieceInBetween.CheckersInBetweenStrategy;
import common2.validator.MovementValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CheckersMove implements Move {

    private final Coronation coronation = new Coronation(PieceType.KING);
    private final BasicMove basicMove = new BasicMove();

    @Override
    public Board move(Board board, Position from, Position to) {

        int colDiff = Math.abs(to.getCol() - from.getCol());
        boolean pieceInTheMiddle = board.pieceInBetween(from, to, new CheckersInBetweenStrategy());

        if (isCoronation(board.getSize(), to)) return coronate(board, from, to);
        if (colDiff == 1) return basicMove.move(board, from, to);
        if (!pieceInTheMiddle && colDiff == 2) {
            if (middlePieceColor(board, from, to) == null) return deleteAndMove(board, from, to);
        }

        Board afterMovingBoard = deleteAndMove(board, from, to);
        List<Position> possiblePositions = fetchPossiblePositions(afterMovingBoard, to);
        if (possiblePositions.isEmpty()) return afterMovingBoard;
        return this.move(afterMovingBoard, to, possiblePositions.get(0));
    }

    private Board deleteAndMove(Board board, Position from, Position to) {

        int rowDirection = (to.getRow() - from.getRow() > 0) ? 1 : -1;
        int colDirection = (to.getCol() - from.getCol() > 0) ? 1 : -1;

        int currentRow = from.getRow() + rowDirection;
        int currentCol = from.getCol() + colDirection;

        Board returnable = board;

        while (currentRow != to.getRow() && currentCol != to.getCol()) {
            returnable = returnable.removePiece(new Position(currentRow, currentCol));
            currentRow += rowDirection;
            currentCol += colDirection;
        }
        return basicMove.move(returnable, from, to);

    }

    private boolean isCoronation(int boardSize, Position to) {
        return to.getRow() == boardSize || to.getRow() == 0;
    }

    private List<Position> fetchPossiblePositions(Board board, Position from) {
        return board.getValidMovements(board.getPiece(from));
    }

    private Board coronate(Board board, Position from, Position to) {
        int middleRow = (from.getRow() + to.getRow()) / 2;
        int middleCol = (from.getCol() + to.getCol()) / 2;;
        return coronation.move(board.removePiece(new Position(middleRow, middleCol)), from, to);
    }

    private Colour middlePieceColor(Board board, Position from, Position to) {
        int middleRow = (from.getRow() + to.getRow()) / 2;
        int middleCol = (from.getCol() + to.getCol()) / 2;
        Piece piece = board.getPiece(new Position(middleRow, middleCol));
        return piece.getColour();
    }
}
