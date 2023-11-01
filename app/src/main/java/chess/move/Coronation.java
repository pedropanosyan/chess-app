package chess.move;

import common.Board;
import common.Piece;
import common.Position;
import common.enums.PieceType;
import common.move.Move;
import common.movementValidator.MovementValidator;

import java.util.List;

public class Coronation implements Move {

    List<MovementValidator> movementValidators;
    public Coronation(List<MovementValidator> movementValidators) {
        this.movementValidators = movementValidators;
    }

    @Override
    public Board move(Board board, Position from, Position to) {
        Piece piece = board.getPiece(from.getRow(), from.getCol());
        Board newBoard = board.copyBoard();
        newBoard.getBoard()[from.getRow()][from.getCol()].setPiece(null);
        Piece newQueen = new Piece(PieceType.QUEEN, piece.getColour());
        newQueen.setId(piece.getId());
        newBoard.getBoard()[to.getRow()][to.getCol()].setPiece(newQueen);
        return newBoard;
    }

    @Override
    public List<MovementValidator> getMovementValidators() {
        return null;
    }

}
