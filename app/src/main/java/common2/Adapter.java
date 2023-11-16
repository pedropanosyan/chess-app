package common2;

import common2.enums.Colour;
import common2.enums.PieceType;
import edu.austral.dissis.chess.gui.BoardSize;
import edu.austral.dissis.chess.gui.ChessPiece;
import edu.austral.dissis.chess.gui.PlayerColor;

import java.util.ArrayList;
import java.util.List;

public class Adapter {

    public static BoardSize adaptBoard(Board board){
        return new BoardSize(board.getSize()+1, board.getSize()+1);
    }


    public static edu.austral.dissis.chess.gui.Position adaptIncreasePosition(Position position){
        return new edu.austral.dissis.chess.gui.Position(position.getRow() + 1, position.getCol() + 1);
    }

    public static Position adaptDecreasePosition(edu.austral.dissis.chess.gui.Position position){
        return new Position(position.getRow() - 1, position.getColumn() - 1);
    }

    public static PlayerColor adaptColour(Colour colour){
        if (colour == Colour.BLACK) return PlayerColor.BLACK;
        else return PlayerColor.WHITE;
    }

    private static String adaptName(PieceType name){
        return switch (name) {
            case WHITE_PAWN, BLACK_PAWN, PAWN -> "pawn";
            case KING -> "king";
            case QUEEN -> "queen";
            case BISHOP -> "bishop";
            case KNIGHT -> "knight";
            case ROOK -> "rook";
            case ARCHBISHOP -> "archbishop";
            case CHANCELLOR -> "chancellor";
        };
    }

    public static List<ChessPiece> getPieces(common2.Board board) {
        List<Position> positions = board.getBoard().keySet().stream().toList();
        List<ChessPiece> pieces = new ArrayList<>();
        for (Position position: positions) {
            Piece piece = board.getPiece(position);
            pieces.add(new ChessPiece(piece.getId(), adaptColour(piece.getColour()), adaptIncreasePosition(position), adaptName(piece.getType())));
        }
        return pieces;
    }
}
