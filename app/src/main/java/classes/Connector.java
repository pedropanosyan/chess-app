package classes;

import classes.Piece;
import classes.enums.Colour;
import classes.enums.PieceType;
import edu.austral.dissis.chess.gui.*;
import edu.austral.dissis.chess.gui.Position;

import java.util.ArrayList;
import java.util.List;

public class Connector {

    public static BoardSize adaptBoard(Board board){
        return new BoardSize(board.getLength(), board.getLength());
    }

    public static Position adaptPosition(classes.Position position){
        return new Position(position.getRow() +1 , position.getCol() +1 );
    }

    public static classes.Position fromHisToMinePosition(Position position){
        return new classes.Position(position.getRow() -1, position.getColumn() -1);
    }

    public static PlayerColor adaptColour(Colour colour){
        if (colour == Colour.BLACK) return PlayerColor.BLACK;
        else return PlayerColor.WHITE;
    }

    public static List<ChessPiece> adaptPieces(List<classes.Position> positions){

        List<ChessPiece> pieces = new ArrayList<>(positions.size());

        for (classes.Position position : positions) {
            Piece piece = position.getPiece();
            if (piece != null) {
                pieces.add(new ChessPiece(piece.getId(), adaptColour(piece.getColour()), adaptPosition(position), adaptName(piece.getType())));
            }
        }
        return pieces;
    }

    private static String adaptName(PieceType name){
        return switch (name) {
            case BLACK_PAWN, WHITE_PAWN -> "pawn";
            case ROOK -> "rook";
            case KNIGHT -> "knight";
            case BISHOP -> "bishop";
            case QUEEN -> "queen";
            case KING -> "king";
        };
    }


    public static List<ChessPiece> getPieces(classes.Board board) {
        List<classes.Position> positions = new ArrayList<>();
        for (int x = 0; x < board.getLength(); x++) {
            for (int y = 0; y < board.getLength(); y++) {
                if (board.getPosition(x, y).hasPiece()) {
                    positions.add(board.getPosition(x, y));
                }
            }
        }
        return adaptPieces(positions);
    }
}
