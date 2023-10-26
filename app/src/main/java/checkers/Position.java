package checkers;

public class Position {

    private final int row;
    private final int col;
    private Piece piece;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
        this.piece = null;
    }

    public Position(int row, int col, Piece piece) {
        this.row = row;
        this.col = col;
        this.piece = piece;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public Position copy() {
        return new Position(this.row, this.col, this.piece);
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public boolean hasPiece() {
        return this.piece != null;
    }

    public boolean isEqual(Position position) {
        return this.row == position.getRow() && this.col == position.getCol();
    }
}