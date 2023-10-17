package classes;

import java.util.Stack;

public class MovesHistory {

    private final Stack<Board> movesHistory;

    public MovesHistory(Stack<Board> movesHistory) {
        this.movesHistory = movesHistory;
    }

    public Stack<Board> getMovesHistory() {
        return movesHistory;
    }
}
