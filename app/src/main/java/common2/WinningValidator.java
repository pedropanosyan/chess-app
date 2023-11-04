package common2;

import common2.enums.Colour;
import common2.enums.PieceType;

import java.util.Map;

public interface WinningValidator {

    boolean validated(GameVersion version, Board board, Colour colour);

}
