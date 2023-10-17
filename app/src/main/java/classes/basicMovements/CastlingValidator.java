package classes.basicMovements;

import classes.Board;
import classes.Piece;
import classes.Position;
import classes.enums.PieceType;
public class CastlingValidator implements BasicMovementValidator {

    @Override
    public boolean validateMove(Board board, Position from, Position to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());
        Piece king = from.getPiece();
        Piece rook = getRook(board, colDiff);
        return kingNotMoved(king) && rookNotMoved(rook) &&
                isCastling(king, colDiff, rowDiff) && !areObstacles(board, from, to);
    }

    @Override
    public Board move(Board board, Position from, Position to) {
        int colDiff = Math.abs(to.getCol() - from.getCol());
        Piece king = board.getPiece(from.getRow(), from.getCol());
        Piece rook = getRook(board, colDiff);

        Position[][] newBoard = board.copyBoard();

        if (colDiff == 2) {
            newBoard[from.getRow()][7].setPiece(null);
            newBoard[from.getRow()][from.getCol()].setPiece(null);
            newBoard[from.getRow()][6].setPiece(king);
            newBoard[from.getRow()][5].setPiece(rook);
        }
        else {
            newBoard[from.getRow()][0].setPiece(null);
            newBoard[from.getRow()][1].setPiece(king);
            newBoard[from.getRow()][2].setPiece(rook);
        }
        return new Board(newBoard);
    }

    private Piece getRook(Board board, int colDiff) {
        if (colDiff == 2) {
            return board.getPiece(0, 7);
        }
        else if (colDiff == 3) {
            return board.getPiece(0, 0);
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
