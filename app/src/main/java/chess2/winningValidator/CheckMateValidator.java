package chess2.winningValidator;

import common2.Board;
import common2.Move.BasicMove;
import common2.Piece;
import common2.Position;
import common2.WinningValidator;
import common2.enums.Colour;
import common2.enums.PieceType;


import java.util.ArrayList;

public class CheckMateValidator implements WinningValidator {

    @Override
    public boolean validated(Board board, Colour colour) {
        if (!board.isPieceUnderAttack(PieceType.KING, getOppositeColour(colour))) return false;

        return canEscapeCheck(board, colour);
    }

    private boolean canEscapeCheck(Board board, Colour colour) {
        for (Position from :  board.getBoard().keySet()) {
            Piece piece = board.getPiece(from);
            if (!board.hasValidMovements(piece)) continue;
            if (!sameColourPiece(colour, piece)) continue;
            for (Position to : board.getBoard().keySet()) {
                if (piece.isValid(board, from, to)) {
                    Board tempBoard = new BasicMove().move(board, from, to);
                    if (!tempBoard.isPieceUnderAttack(PieceType.KING, colour)) return true;
                }
            }
        }
        return false;
    }

    private static boolean sameColourPiece(Colour colour, Piece piece) {
        return piece.getColour() == colour;
    }

    private Colour getOppositeColour(Colour colour) {
        return colour == Colour.WHITE ? Colour.BLACK : Colour.WHITE;
    }

}
