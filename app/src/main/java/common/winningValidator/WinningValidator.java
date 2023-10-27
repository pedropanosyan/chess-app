package common.winningValidator;

import java.util.Map;

import common.move.Move;
import common.Board;
import common.enums.Colour;
import common.enums.PieceType;

public interface WinningValidator {

    boolean validated(Map<PieceType, Move> moveMap, Board board, Colour colour);

}
