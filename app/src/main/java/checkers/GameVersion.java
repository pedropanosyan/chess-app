package checkers;

import java.util.List;
import java.util.Map;
import checkers.enums.PieceType;
import checkers.move.Move;
import checkers.movementValidator.MovementValidator;
import checkers.winningValidator.WinningValidator;

public class GameVersion {

    private final Map<PieceType, Move> move;
    private final List<WinningValidator> winningConditions;
    private final int boardSize;

    public  GameVersion(Map<PieceType, Move> move,
                        List<WinningValidator> winningConditions,
                        int boardSize) {
        this.move = move;
        this.winningConditions = winningConditions;
        this.boardSize = boardSize;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public Move getBasicMovementsByPiece(PieceType pieceType) {
        return move.get(pieceType);
    }

    public List<WinningValidator> getWinningConditions() {
        return winningConditions;
    }

    public Map<PieceType, Move> getMovements() {
        return move;
    }

}
