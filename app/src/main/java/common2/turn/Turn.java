package common2.turn;

import common2.enums.Colour;

public interface Turn {

    Turn nextTurn();
    Colour getCurrentTurn();

}
