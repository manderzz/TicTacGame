package ca.pledgetovote.model;

import ca.pledgetovote.enumClasses.Piece;
/**
 * this class is similiar to cell class however it does the checking for any invalid characters
 */

public class checkInput {

    private int row;
    private int col;
    private String piece;


    public checkInput() {
    }

    public checkInput(int row, int col, String piece) {

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

    public String getPiece() {
        return piece;
    }

    public Piece getEnumPiece(){

        if(piece.equals("O") || piece.equals("o")){
            return Piece.O;
        }
        if(piece.equals("X") || piece.equals("x")){
            return Piece.X ;
        }
        throw new IllegalArgumentException("please enter X or O");
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }
}
