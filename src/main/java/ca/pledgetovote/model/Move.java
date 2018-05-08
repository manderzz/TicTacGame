package ca.pledgetovote.model;

import ca.pledgetovote.enumClasses.Piece;

public class Move {
    private long moveNumber;

    private Cell cell;
    private Piece piece;

    public long getMoveNumber() {
        return moveNumber;
    }

    public void setMoveNumber(long moveNumber) {
        this.moveNumber = moveNumber;
    }

    public Move(){}

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {

        this.cell = cell;
        this.cell.setPiece(piece);
    }


    public Move(Cell cell) {

        this.cell = cell;
        this.cell.setPiece(piece);
    }

    public Move(Piece piece , int row , int col) {
        this(new Cell(row,col,piece));

    }
    public Move(Cell cell, Piece piece){
        this.cell = cell;
        this.piece = piece;
    }

    @Override
    public String toString() {
        return "Move{" +
                "cell=" + cell +
                ", piece=" + piece +
                '}';
    }
}
