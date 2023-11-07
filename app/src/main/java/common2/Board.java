package common2;

import common2.enums.Colour;
import common2.enums.PieceType;
import common2.pieceInBetween.PieceInBetweenStrategy;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;
    private final int size;

    public Board(Map<Position, Piece> board, int size) {
        this.board = board;
        this.size = size;
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public Piece getPiece(Position position) {
        return board.get(position);
    }

    public boolean existsPosition(Position position) {
        return board.containsKey(position);
    }

    public boolean isLastRow(Position position) {
        return position.getRow() == size - 1;
    }

    public boolean isFirstRow(Position position) {
        return position.getRow() == 0;
    }

    public int getSize() {
        return size - 1;
    }

    public boolean pieceInBetween(Position from, Position to, PieceInBetweenStrategy pieceInBetweenStrategy) {
        return pieceInBetweenStrategy.pieceInBetween(this, from, to);
    }

    public boolean isOutOfIndex(Position position) {
        return position.getRow() < 0 || position.getRow() > size-1 || position.getCol() < 0 || position.getCol() > size-1;
    }

    private Position findKingPosition(PieceType pieceType, Colour colour) {
        return board.entrySet().stream()
                .filter(entry -> entry.getValue().getType() == pieceType && entry.getValue().getColour() == colour)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    public boolean isPieceUnderAttack(PieceType pieceType, Colour colour) {
        Position piecePosition = findKingPosition(pieceType, colour);
         return board.entrySet().stream()
                .filter(entry -> entry.getValue().getColour() != colour)
                .anyMatch(entry -> entry.getValue().isValid(this, entry.getKey(), piecePosition));
    }

    public Position findPositionById(String id) {
        return board.entrySet().stream()
                .filter(entry -> entry.getValue().getId().equals(id))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    public boolean hasValidMovements(Piece piece) {
        Position position = findPositionById(piece.getId());
        return board.keySet().stream()
                .anyMatch(to -> piece.isValid(this, position, to));
    }

    public Board removePiece(Position position) {
        Map<Position, Piece> newBoard = new HashMap<>(this.board);
        newBoard.remove(position);
        return new Board(newBoard, this.size);
    }

    public Board addPiece(Position position, Piece piece) {
        Map<Position, Piece> newBoard = new HashMap<>(this.board);
        newBoard.put(new Position(position.getRow(), position.getCol()), new Piece(piece.getValidators(), piece.getType(), piece.getColour(), piece.getId()));
        return new Board(newBoard, this.size);
    }

    public void print() {
        for (Map.Entry<Position, Piece> entry : board.entrySet()) {
            Position position = entry.getKey();
            Piece piece = entry.getValue();
            System.out.println("Position: " + position.getRow() + ", " + position.getCol() + ", Piece: " + piece.getType() + ", " + piece.getColour());
        }
    }

}
