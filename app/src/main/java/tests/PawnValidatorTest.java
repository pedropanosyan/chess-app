package tests;

import classes.*;
import classes.basicMovements.PawnValidator;
import classes.enums.Colour;
import classes.enums.PieceType;
import classes.gameInterface.CreateClassicGame;
import common.Game;
import org.junit.Test;

import static org.junit.Assert.*;

public class PawnValidatorTest {

    Player[] players = new Player[]{new Player("Pedro", Colour.WHITE, true), new Player("Facundo", Colour.BLACK, false)};

    Game game = CreateClassicGame.createClassicGame(players[0], players[1]);

    @Test
    public void testValidSingleMove() {
        Board board = game.getLastMove();
        Position from = board.getPosition(1, 2);
        Position to = board.getPosition(2,2);

        PawnValidator validator = new PawnValidator(false,1, 0);
        assertTrue(validator.validateMove(board, from, to));
    }

    @Test
    public void testValidDoubleMove() {
        Board board = game.getLastMove();
        Position from = board.getPosition(1, 2);
        Position to = board.getPosition(3, 2);

        PawnValidator validator = new PawnValidator(false, 1, 0);
        assertTrue(validator.validateMove(board, from, to));
    }

    @Test
    public void testValidCapture() {
        Board board = game.getLastMove();
        Position from = board.getPosition(1, 2);
        board.getPosition(2,3).setPiece(new Piece(PieceType.BLACK_PAWN, Colour.BLACK));
        Position to = board.getPosition(2, 3);

        PawnValidator validator = new PawnValidator(false, 1, 0);
        assertTrue(validator.validateMove(board, from, to));
    }

    @Test
    public void testInvalidCaptureOwnPiece() {
        Board board = game.getLastMove();
        Position from = board.getPosition(1, 2);
        Position to = board.getPosition(2, 3);

        PawnValidator validator = new PawnValidator(false, 1, 0);
        assertFalse(validator.validateMove(board, from, to));
    }

    @Test
    public void testInvalidCaptureEmptySquare() {
        Board board = game.getLastMove();
        Position from = board.getPosition(1, 2);
        Position to = board.getPosition(2, 3);

        PawnValidator validator = new PawnValidator(false, 1, 0);
        assertFalse(validator.validateMove(board, from, to));
    }

    @Test
    public void testInvalidDoubleMoveAfterMoving() {
        Board board = game.getLastMove();
        Position from = board.getPosition(1, 2);
        Position to1 = board.getPosition(3, 2);

        PawnValidator validator = new PawnValidator(false, 1, 0);
        assertTrue(validator.validateMove(board, from, to1));

        Position to2 = board.getPosition(4, 2);
        assertFalse(validator.validateMove(board, from, to2));
    }

    @Test
    public void testInvalidDoubleMoveOverPiece() {
        Board board = game.getLastMove();
        Position from = board.getPosition(1, 2);
        Position to = board.getPosition(3, 2);

        PawnValidator validator = new PawnValidator(false, 1, 0);
        assertTrue(validator.validateMove(board, from, to));
    }

}
