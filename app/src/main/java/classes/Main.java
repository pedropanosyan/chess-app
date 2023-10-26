package classes;

import classes.enums.Colour;
import classes.gameInterface.CreateClassicGame;
import common.Game;

public class Main {

    public static void main(String[] args) {

        Player pedro = new Player("Pedro", Colour.WHITE, true);
        Player facundo = new Player("Facundo", Colour.BLACK, false);
        Game game = CreateClassicGame.createClassicGame(pedro, facundo);

        game.move(game.getLastMove().getPosition(1, 4), game.getLastMove().getPosition(3, 4));
        game.move(game.getLastMove().getPosition(6, 4), game.getLastMove().getPosition(4, 4));
        game.move(game.getLastMove().getPosition(0, 5), game.getLastMove().getPosition(2, 3));
        game.move(game.getLastMove().getPosition(6, 0), game.getLastMove().getPosition(5, 0));
        game.move(game.getLastMove().getPosition(0, 3), game.getLastMove().getPosition(4, 7));
        game.move(game.getLastMove().getPosition(5, 0), game.getLastMove().getPosition(4, 0));
        game.move(game.getLastMove().getPosition(4, 7), game.getLastMove().getPosition(6, 5));
        game.move(game.getLastMove().getPosition(7, 4), game.getLastMove().getPosition(6, 5));

        ConstructorHelper.printBoard(game.getLastMove());

    }
}