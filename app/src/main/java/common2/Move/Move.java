package common2.Move;

import common2.Board;
import common2.Position;

public interface Move {

    Board move(Board board, Position from, Position to);


}
