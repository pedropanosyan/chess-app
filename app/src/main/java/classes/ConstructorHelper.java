//package classes;
//
//import classes.basicMovements.BasicMovementValidator;
//import classes.enums.Colour;
//import classes.enums.PieceType;
//
//import java.util.Map;
//
//public class ConstructorHelper {
//
//
//    public static Position[][] initializeBoard(GameVersion gameVersion) {
//        int size = gameVersion.getBoardSize();
//        Map<PieceType, BasicMovementValidator[]> movements = gameVersion.getBasicMovements();
//
//        Position[][] board = new Position[size][size];
//        for (int row = 0; row < size; row++) {
//            for (int col = 0; col < 8; col++) {
//                board[row][col] = new Position(row, col);
//            }
//        }
//        initializeChessPieces(board, movements);
//        return board;
//    }
//
//    private static void initializeChessPieces(Position[][] board, Map<PieceType, BasicMovementValidator[]> movementsMap) {
//        PieceType[] initialRow = {
//                PieceType.CHANCELLOR,
//                PieceType.KNIGHT,
//                PieceType.BISHOP,
//                PieceType.QUEEN,
//                PieceType.KING,
//                PieceType.BISHOP,
//                PieceType.KNIGHT,
//                PieceType.CHANCELLOR,
//        };
//        for (int row = 0; row < 8; row++) {
//            for (int col = 0; col < 8; col++) {
//                Colour pieceColour = (row < 2) ? Colour.WHITE : Colour.BLACK;
//                if (row == 1 || row == 6) {
//                    if (pieceColour == Colour.WHITE) {
//                        board[row][col].setPiece(new Piece(PieceType.WHITE_PAWN, pieceColour));
//                    } else {
//                        board[row][col].setPiece(new Piece(PieceType.BLACK_PAWN, pieceColour));
//                    }
//                } else if (row == 0 || row == 7) {
//                    board[row][col].setPiece(new Piece(initialRow[col], pieceColour));
//                }
//            }
//        }
//    }
//
//    public static void printBoard(Board board) {
//        System.out.println("   0       1       2       3       4       5       6       7");
//        System.out.println("  ---------------------------------------------------------");
//        for (int row = 7; row >= 0; row--) {
//            System.out.print((row) + "|");
//            for (int col = 0; col < 8; col++) {
//                Piece piece = board.getPiece(row, col);
//                if (piece != null) {
//                    if (piece.getType() == PieceType.BLACK_PAWN || piece.getType() == PieceType.WHITE_PAWN) {
//                        System.out.print("  " + "PAWN" + " ");
//                    }
//                    else System.out.print(" " + piece.getType() + " ");
//                } else {
//                    System.out.print("       ");
//                }
//                System.out.print("|");
//            }
//            System.out.println(" " + (row));
//            System.out.println("  ---------------------------------------------------------");
//        }
//        System.out.println("   0       1       2       3       4       5       6       7");
//    }
//
//
//}
