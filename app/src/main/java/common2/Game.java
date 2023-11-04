package common2;

public class Game {

    private final Board board;
    private final GameVersion gameVersion;

    public Game(Board board, GameVersion gameVersion) {
        this.board = board;
        this.gameVersion = gameVersion;
    }

    public Board getBoard() {
        return board;
    }

    public GameVersion getGameVersion() {
        return gameVersion;
    }

}
