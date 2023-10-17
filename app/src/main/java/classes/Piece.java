package classes;

import classes.enums.Colour;
import classes.enums.PieceType;

public class Piece {

    private final PieceType type;
    private final Colour colour;
    private final String id;

    private boolean hasMoved = false;

    public Piece(PieceType name, Colour colour) {
        this.type = name;
        this.colour = colour;
        this.id = String.valueOf(hashCode());
    }

    public PieceType getType() {
        return this.type ;
    }

    public Colour getColour() {
        return colour;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved() {
        this.hasMoved = true;
    }

    public String getId() {
        return id;
    }
}
