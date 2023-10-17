package classes;

import classes.basicMovements.BasicMovementValidator;
import classes.enums.Colour;
import classes.rules.RuleController;
import classes.winningValidator.WinningCondition;

import java.util.Stack;

public class Game {

    private final GameVersion version;
    private Stack<Board> moveHistory = new Stack<>();
    private final Board startingBoard;
    private final Player[] players;

    public Game(GameVersion version, Player[] players) {
        this.players = players;
        this.version = version;
        Position[][] initializeBoard = ConstructorHelper.initializeBoard(version);
        this.startingBoard = new Board(initializeBoard);
        this.moveHistory.push(startingBoard);
    }

    public Board move(Position from, Position to) {
        Board board = getLastMove();
        Piece piece = getPieceIn(from, board);
        Colour playerColour = getPlayerTurn();

        Position realFromPosition = board.getPosition(from.getRow(), from.getCol());
        Position realToPosition = board.getPosition(to.getRow(), to.getCol());

        if (piece == null || playerColour != piece.getColour()) return getLastMove();
        if (validateWinningCondition(playerColour, board)) return getLastMove();

        Board newBoard = validateBasicMovement(piece, realFromPosition, realToPosition);

        if (newBoard != null && validateRule(playerColour, newBoard)) {
            return returnBoard(piece, newBoard);
        }
        return getLastMove();
    }

    private Board validateBasicMovement(Piece piece, Position from, Position to) {
        BasicMovementValidator[] movements = this.getVersion().getBasicMovementsByPiece(piece.getType());
        for (BasicMovementValidator movement : movements) {
            if (movement.validateMove(getLastMove(), from, to)) return movement.move(getLastMove(), from, to);
        }
        return null;
    }

    private boolean validateWinningCondition(Colour colour, Board board) {
        for (WinningCondition winningCondition : version.getWinningConditions()) {
            if (winningCondition.validateWinningCondition(this.getVersion().getBasicMovements(), board, colour)) return true;
        }
        return false;
    }

    private boolean validateRule(Colour colour, Board board) {
        for (RuleController rule : version.getRules()) {
            if (!rule.validateRule(this.getVersion().getBasicMovements(), board, colour)) {
                return false;
            }
        }
        return true;
    }

    private Board returnBoard(Piece piece, Board board) {
        addBoardToHistory(board);
        piece.setHasMoved();
        changeTurn();
        return board;
    }

    private Colour getPlayerTurn() {
        for (Player player : players) {
            if (player.isTurn()) {
                return player.getColour();
            }
        }
        return null;
    }

    private void addBoardToHistory(Board nextBoard) {
        moveHistory.push(nextBoard);
    }

    private static Piece getPieceIn(Position from, Board board) { return board.getPiece(from.getRow(), from.getCol()); }

    public Board getLastMove() { return this.moveHistory.peek(); }

    public GameVersion getVersion() {
        return version;
    }

    public Board getStartingBoard() {
        return startingBoard;
    }

    private void changeTurn() {
        for (int i = 0; i < players.length; i++) {
            if (players[i].isTurn()) {
                players[i].setTurn();
                players[(i + 1) % players.length].setTurn();
                break;
            }
        }
    }
}
