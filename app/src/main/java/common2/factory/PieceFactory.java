package common2.factory;

import common2.GameVersion;
import common2.Piece;
import common2.enums.Colour;
import common2.enums.PieceType;

import java.util.ArrayList;
import java.util.List;

public class PieceFactory {

    private final GameVersion gameVersion;

    public PieceFactory(GameVersion gameVersion) {
        this.gameVersion = gameVersion;
    }

    public Piece createRook(Colour colour) {
        return new Piece(gameVersion.getValidatorsByPieceType(PieceType.ROOK), PieceType.ROOK, colour);
    }

    public Piece createBishop(Colour colour) {
        return new Piece(gameVersion.getValidatorsByPieceType(PieceType.BISHOP), PieceType.BISHOP, colour);
    }

    public Piece createQueen(Colour colour) {
        return new Piece(gameVersion.getValidatorsByPieceType(PieceType.QUEEN), PieceType.QUEEN, colour);
    }

    public Piece createKing(Colour colour) {
        return new Piece(gameVersion.getValidatorsByPieceType(PieceType.KING), PieceType.KING, colour);
    }

    public Piece createKnight(Colour colour) {
        return new Piece(gameVersion.getValidatorsByPieceType(PieceType.KNIGHT), PieceType.KNIGHT, colour);
    }

    public Piece createPawn(Colour colour) {
        PieceType pieceType = colour == Colour.WHITE ? PieceType.WHITE_PAWN : PieceType.BLACK_PAWN;
        return new Piece(gameVersion.getValidatorsByPieceType(pieceType), pieceType, colour);
    }

    public Piece createCheckerPawn(Colour colour) {
        return new Piece(gameVersion.getValidatorsByPieceType(PieceType.PAWN), PieceType.PAWN, colour);
    }

    public Piece createCheckerKing(Colour colour) {;
        return new Piece(gameVersion.getValidatorsByPieceType(PieceType.KING), PieceType.KING, colour);
    }

}
