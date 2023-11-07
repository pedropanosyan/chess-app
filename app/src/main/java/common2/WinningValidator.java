package common2;

import common2.enums.Colour;
import common2.enums.PieceType;

import java.util.Map;

public interface WinningValidator {

    boolean validated(Board board, Colour colour);

}
