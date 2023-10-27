package common;

import common.enums.Colour;
import common.enums.PieceType;
import edu.austral.dissis.chess.gui.*;
import edu.austral.dissis.chess.gui.Position;

import java.util.ArrayList;
import java.util.List;

public class Connector {

    public static BoardSize adaptBoard(common.Board board){
        return new BoardSize(board.getLength(), board.getLength());
    }

    public static Position adaptPosition(common.Position position){
        return new Position(position.getRow() +1 , position.getCol() +1 );
    }

    public static common.Position fromHisToMinePosition(Position position){
        return new common.Position(position.getRow() -1, position.getColumn() -1);
    }

    public static PlayerColor adaptColour(Colour colour){
        if (colour == Colour.BLACK) return PlayerColor.BLACK;
        else return PlayerColor.WHITE;
    }

    public static List<ChessPiece> adaptPieces(List<common.Position> positions){

        List<ChessPiece> pieces = new ArrayList<>(positions.size());

        for (common.Position position : positions) {
            common.Piece piece = position.getPiece();
            if (piece != null) {
                pieces.add(new ChessPiece(piece.getId(), adaptColour(piece.getColour()), adaptPosition(position), adaptName(piece.getType())));
            }
        }
        return pieces;
    }

    private static String adaptName(PieceType name){
        return switch (name) {
            case PAWN -> "pawn";
            case KING -> "king";
            case QUEEN -> "queen";
            case BISHOP -> "bishop";
            case KNIGHT -> "knight";
            case ROOK -> "rook";
        };
    }


    public static List<ChessPiece> getPieces(common.Board board) {
        List<common.Position> positions = new ArrayList<>();
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
