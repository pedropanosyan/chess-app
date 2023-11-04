package common2.pieceInBetween;

import common2.Board;
import common2.Position;

public interface PieceInBetweenStrategy {

    boolean pieceInBetween(Board board, Position from, Position to);

}
