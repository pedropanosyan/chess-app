package common2.turn;

import common2.enums.Colour;

public class TwoPlayersTurn implements Turn {

    private final Colour currentTurn;

    public TwoPlayersTurn(Colour currentTurn) {
        this.currentTurn = currentTurn;
    }

    @Override
    public Turn nextTurn() {
        Colour nextTurnPlayer = (currentTurn == Colour.WHITE) ? Colour.BLACK : Colour.WHITE;
        return new TwoPlayersTurn(nextTurnPlayer);
    }

    @Override
    public Colour getCurrentTurn() {
        return currentTurn;
    }
}
