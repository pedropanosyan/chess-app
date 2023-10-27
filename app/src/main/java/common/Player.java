package common;


import common.enums.Colour;

public class Player {

    private String name;
    private Colour colour;
    private boolean isTurn;

    public Player(String name, Colour colour) {
        this.name = name;
        this.colour = colour;
    }

    public Colour getColour() {
        return colour;
    }

    public void setTurn() {
        this.isTurn = !this.isTurn;
    }

    public boolean isTurn() {
        return isTurn;
    }
}