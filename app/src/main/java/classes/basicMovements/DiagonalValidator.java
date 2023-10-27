//package classes.basicMovements;
//
//import classes.Board;
//import classes.Piece;
//import classes.Position;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DiagonalValidator implements BasicMovementValidator {
//
//    boolean canJump;
//    int maxSteps;
//
//    public DiagonalValidator(boolean canJump) {
//        this.canJump = canJump;
//        this.maxSteps = 8;
//    }
//
//    public DiagonalValidator(boolean canJump, int maxSteps) {
//        this.canJump = canJump;
//        this.maxSteps = maxSteps;
//    }
//
//    @Override
//    public boolean validateMove(Board board, Position from, Position to) {
//        int rowDiff = to.getRow() - from.getRow();
//        int colDiff = to.getCol() - from.getCol();
//        if (Math.abs(rowDiff) != Math.abs(colDiff) ) return false;
//        if (rowDiff == 0 && colDiff == 0) return false;
//
//        int rowIncrement = rowDiff > 0 ? 1 : -1;
//        int colIncrement = colDiff > 0 ? 1 : -1;
//
//        int row = from.getRow() + rowIncrement;
//        int col = from.getCol() + colIncrement;
//        int counter = 1;
//
//        while ((row != to.getRow()|| (col != to.getCol())) && counter <= this.maxSteps) {
//            counter++;
//            Piece pieceInBetween = board.getPiece(row, col);
//            if (pieceInBetween != null) {
//                return false;
//            }
//            row += rowIncrement;
//            col += colIncrement;
//        }
//        return counter <= this.maxSteps;
//    }
//
//    @Override
//    public Board move(Board board, Position from, Position to) {
//        Piece piece = board.getPiece(from.getRow(), from.getCol());
//        Position[][] newBoard = board.copyBoard();
//        newBoard[from.getRow()][from.getCol()].setPiece(null);
//        newBoard[to.getRow()][to.getCol()].setPiece(piece);
//        return new Board(newBoard);
//    }
//
//    @Override
//    public List<Position> getValidMoves(Board board, Position from) {
//        List<Position> possibleMoves = new ArrayList<>();
//
//        int fromRow = from.getRow();
//        int fromCol = from.getCol();
//
//        int[] rowOffsets = { -1, -1, 1, 1 };
//        int[] colOffsets = { -1, 1, -1, 1 };
//
//        for (int i = 0; i < 4; i++) {
//            for (int step = 1; step <= this.maxSteps; step++) {
//                int newRow = fromRow + rowOffsets[i] * step;
//                int newCol = fromCol + colOffsets[i] * step;
//
//                if (isValidPosition(newRow, newCol)) {
//                    Position newPosition = board.getPosition(newRow, newCol);
//
//                    if (!newPosition.hasPiece() || newPosition.getPiece().getColour() != from.getPiece().getColour()) {
//                        possibleMoves.add(newPosition);
//                    }
//                    if (newPosition.hasPiece()) {
//                        break;
//                    }
//                } else {
//                    break;
//                }
//            }
//        }
//        return possibleMoves;
//    }
//
//    private boolean isValidPosition(int row, int col) {
//        return row >= 0 && row < 8 && col >= 0 && col < 8;
//    }
//
//
//    public boolean areObstacles(Board board, Position from, Position to) {
//        int minRow = Math.min(from.getRow(), to.getRow());
//        int maxRow = Math.max(from.getRow(), to.getRow());
//        int minCol = Math.min(from.getCol(), to.getCol());
//        int maxCol = Math.max(from.getCol(), to.getCol());
//
//        int row = minRow + 1;
//        int col = minCol + 1;
//
//        while (row < maxRow && col < maxCol) {
//            Piece pieceInBetween = board.getPiece(row, col);
//            if (pieceInBetween != null) {
//                return  true;
//            }
//            row++;
//            col++;
//        }
//        if (to.hasPiece()) return from.getPiece().getColour() == to.getPiece().getColour();
//        else return false;
//    }
//}
//
