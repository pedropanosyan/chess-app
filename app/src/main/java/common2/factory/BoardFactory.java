package common2.factory;

import common2.Board;
import common2.GameVersion;
import common2.Piece;
import common2.Position;
import common2.enums.Colour;

import java.util.HashMap;
import java.util.Map;

public class BoardFactory {

    public static Board createClassicChessBoard(GameVersion gameVersion) {
        PieceFactory pieceFactory = new PieceFactory(gameVersion);
        Map<Position, Piece> map = new HashMap<>();

        for (int i = 1; i <= 8; i++) {
            map.put(new Position(1, i - 1), pieceFactory.createPawn(Colour.WHITE));
            map.put(new Position(6, i - 1), pieceFactory.createPawn(Colour.BLACK));
        }

        map.put(new Position(0, 0), pieceFactory.createRook(Colour.WHITE));
        map.put(new Position(0, 7), pieceFactory.createRook(Colour.WHITE));
        map.put(new Position(7, 0), pieceFactory.createRook(Colour.BLACK));
        map.put(new Position(7, 7), pieceFactory.createRook(Colour.BLACK));

        map.put(new Position(0, 1), pieceFactory.createKnight(Colour.WHITE));
        map.put(new Position(0, 6), pieceFactory.createKnight(Colour.WHITE));
        map.put(new Position(7, 1), pieceFactory.createKnight(Colour.BLACK));
        map.put(new Position(7, 6), pieceFactory.createKnight(Colour.BLACK));

        map.put(new Position(0, 2), pieceFactory.createBishop(Colour.WHITE));
        map.put(new Position(0, 5), pieceFactory.createBishop(Colour.WHITE));
        map.put(new Position(7, 2), pieceFactory.createBishop(Colour.BLACK));
        map.put(new Position(7, 5), pieceFactory.createBishop(Colour.BLACK));

        map.put(new Position(0, 3), pieceFactory.createQueen(Colour.WHITE));
        map.put(new Position(7, 3), pieceFactory.createQueen(Colour.BLACK));

        map.put(new Position(0, 4), pieceFactory.createKing(Colour.WHITE));
        map.put(new Position(7, 4), pieceFactory.createKing(Colour.BLACK));

        return new Board(map, 8);
    }

    public static Board createClassicCheckerBoard(GameVersion gameVersion) {
        PieceFactory pieceFactory = new PieceFactory(gameVersion);
        Map<Position, Piece> map = new HashMap<>();
        for (int row = 0; row < 3; row++) {
            for (int col = (row % 2 == 0) ? 1 : 0; col < 8; col += 2) {
                map.put(new Position(row, col), pieceFactory.createCheckerPawn(Colour.WHITE));
            }
        }
        for (int row = 5; row < 8; row++) {
            for (int col = (row % 2 == 0) ? 1 : 0; col < 8; col += 2) {
                map.put(new Position(row, col), pieceFactory.createCheckerPawn(Colour.BLACK));
            }
        }
        return new Board(map, 8);
    }


}
