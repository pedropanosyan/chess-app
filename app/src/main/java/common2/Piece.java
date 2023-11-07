package common2;


import common2.enums.PieceType;
import common2.enums.Colour;
import common2.validator.MovementValidator;

import java.util.List;

public class Piece {

    private final List<MovementValidator> validators;
    private final PieceType type;
    private final Colour colour;
    private final boolean hasMoved;
    private final String id;

    public Piece(List<MovementValidator> validators, PieceType name, Colour colour) {
        this.validators = validators;
        this.type = name;
        this.colour = colour;
        this.id = String.valueOf(hashCode());
        this.hasMoved = false;
    }

    public Piece(List<MovementValidator> validators, PieceType name, Colour colour, boolean hasMoved) {
        this.validators = validators;
        this.type = name;
        this.colour = colour;
        this.id = String.valueOf(hashCode());
        this.hasMoved = hasMoved;
    }

    public Piece(List<MovementValidator> validators, PieceType name, Colour colour, String id) {
        this.validators = validators;
        this.type = name;
        this.colour = colour;
        this.id = id;
        this.hasMoved = false;
    }

    public boolean isValid(Board board, Position from, Position to) {
        return this.validators.stream().anyMatch(movementValidator -> movementValidator.validateMove(board, from, to).getKey());
    }

    public List<MovementValidator> getValidators() {
        return validators;
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

    public String getId() {
        return id;
    }

}
