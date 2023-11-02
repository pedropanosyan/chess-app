package common;

import common.enums.PieceType;
import common.move.Move;
import common.winningValidator.WinningValidator;

import java.util.List;
import java.util.Map;


public class GameVersion {

    private final Map<PieceType, Move> move;
    private final List<WinningValidator> winningConditions;
    private final int boardSize;

    public  GameVersion(Map<PieceType, Move> move, List<WinningValidator> winningConditions, int boardSize) {
        this.move = move;
        this.winningConditions = winningConditions;
        this.boardSize = boardSize;
    }

    public Move getBasicMovementsByPiece(PieceType pieceType) {
        return this.move.get(pieceType);
    }

    public List<WinningValidator> getWinningConditions() {
        return this.winningConditions;
    }

    public Map<PieceType, Move> getMove() {
        return this.move;
    }

    public int getBoardSize() {
        return this.boardSize;
    }

}
