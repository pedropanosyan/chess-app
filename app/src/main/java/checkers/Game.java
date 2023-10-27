//package checkers;
//
//import checkers.enums.Colour;
//import common.exceptions.EndGameException;
//import common.exceptions.InvalidMoveException;
//import checkers.move.Move;
//import checkers.winningValidator.WinningValidator;
//import classes.Player;
//
//import java.util.Stack;
//
//public class Game {
//
//    private final GameVersion version;
//    private Stack<Board> movesHistory = new Stack<>();
//    private final Player[] players;
//
//
//    public Game(GameVersion version, Board board, Player[] players) {
//        this.players = players;
//        this.version = version;
//        this.movesHistory.push(board);
//    }
//
//    public Board move(Position from, Position to) throws InvalidMoveException, EndGameException {
//        if (!from.hasPiece()) throw new InvalidMoveException("Trying to move from an empty position");
//        Piece piece = from.getPiece();
//        Board newBoard = checkIfValidMovement(piece, from, to);
//        if (checkWinningConditions(newBoard, piece.getColour())) throw new EndGameException();
//        return submitMove(newBoard);
//    }
//
//    private Board checkIfValidMovement(Piece piece, Position from, Position to) throws InvalidMoveException {
//        try {
//            Move move = this.version.getBasicMovementsByPiece(piece.getType());
//            return move.move(getLastMove(), from, to);
//        } catch (InvalidMoveException e) {
//            throw new InvalidMoveException(e.getMessage());
//        }
//    }
//
//    private boolean checkWinningConditions(Board board, Colour colour) {
//        for (WinningValidator winningCondition : this.version.getWinningConditions()) {
//            if (winningCondition.validated(this.version.getMovements(), board, colour)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private Board submitMove(Board board) {
//        this.movesHistory.push(board);
//        return board;
//    }
//
//    public Board getLastMove() {
//        return this.movesHistory.peek();
//    }
//
//}
