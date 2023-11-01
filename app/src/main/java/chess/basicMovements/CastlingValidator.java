package chess.basicMovements;

import common.Board;
import common.Piece;
import common.Position;
import common.enums.Colour;
import common.enums.PieceType;
import common.movementValidator.MovementValidator;

import java.util.ArrayList;
import java.util.List;

public class CastlingValidator implements MovementValidator {

    @Override
    public boolean validateMove(Board board, Position from, Position to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());
        Piece king = from.getPiece();
        Piece rook = getRook(board, colDiff, king.getColour());
        return kingNotMoved(king) && rookNotMoved(rook) &&
                isCastling(king, colDiff, rowDiff) && !areObstacles(board, from, to);
    }

    @Override
    public List<Position> getPossiblePositions(Board board, Position from) {
        return new ArrayList<>();
    }

    private Piece getRook(Board board, int colDiff, Colour colour) {
        int size = board.getLength()-1;
        if (colDiff == 2) {
            if (colour == Colour.WHITE) {
                return board.getPiece(0, size);
            }
            else {
                return board.getPiece(size, size);
            }
        }
        else if (colDiff == -2) {
            if (colour == Colour.WHITE) {
                return board.getPiece(0, 0);
            }
            else {
                return board.getPiece(size, 0);
            }
        }
        return null;
    }

    private static boolean isCastling(Piece piece, int colDiff, int rowDiff) {
        return piece.getType() == PieceType.KING && (colDiff == 2 || colDiff == 3) && rowDiff == 0;
    }

    private static boolean kingNotMoved(Piece king) {
        return king != null && king.getType() == PieceType.KING && !king.hasMoved();
    }

    private static boolean rookNotMoved(Piece rook) {
        return rook != null && rook.getType() == PieceType.ROOK && !rook.hasMoved();
    }

    public boolean areObstacles(Board board, Position from, Position to){
        int minCol = Math.min(from.getCol(), to.getCol());
        int maxCol = Math.max(from.getCol(), to.getCol());

        for (int col = minCol + 1; col < maxCol; col++) {
            Position actualPosition = new Position(from.getRow(), col);
            Piece pieceInBetween = board.getPiece(from.getRow(), col);
            if (pieceInBetween != null) {
                if (actualPosition.isEqual(to) && to.hasPiece()) {
                    return from.getPiece().getColour() != to.getPiece().getColour();
                } else return false;
            }
        }
        return false;
    }




}
