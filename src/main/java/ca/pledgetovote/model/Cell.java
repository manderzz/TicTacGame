package ca.pledgetovote.model;

import ca.pledgetovote.enumClasses.Piece;

/**
 * this class contains the coordinates of the each move and their piece
 *
 * */
public class Cell {

    private int row;
    private int col;
    private Piece piece;



    public Cell() {
    }

    public Cell(int row, int col, Piece piece) {
        this.row = row;
        this.col = col;
        this.piece = piece;
    }


    public int getRow() {

        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {

        if(piece == Piece.O || piece == Piece.o){

            this.piece = Piece.O;
        }
         if(piece == Piece.X || piece == Piece.x){

            this.piece = Piece.X;
        }

    }

    @Override
    public String toString() {
        return "Cell{" +
                "piece=" + piece +
                ", row=" + row +
                ", col=" + col +
                '}';
    }
}
