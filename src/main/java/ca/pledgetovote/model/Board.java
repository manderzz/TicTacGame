package ca.pledgetovote.model;

import ca.pledgetovote.enumClasses.GameState;
import ca.pledgetovote.enumClasses.Piece;


import java.util.ArrayList;
/**
 * for displaying the board and also updating the board as the game continues
 * */
public class Board {
    private GameState state ;
    private ArrayList<String> row1;
    private ArrayList<String> row2;
    private ArrayList<String> row3;
    private int moveCounter = 0;


    // ~~~~~~~~~~~ initialization ~~~~~~~~~~~~
    public Board() {
        row1 =new ArrayList<String>();
        row2 =new ArrayList<String>();
        row3 =new ArrayList<String>();
        for (int i = 0; i < 3 ; i++) {
            row1.add(" ");
            row2.add(" ");
            row3.add(" ");
        }
    }

    public String[][] putInArray(){
        String[][] arr = new String[3][3];
        for (int i = 0; i <3; i++) {
            arr[0][i] = row1.get(i);
        }
        for (int i = 0; i <3; i++) {
            arr[1][i] = row2.get(i);
        }
        for (int i = 0; i <3; i++) {
            arr[2][i] = row3.get(i);
        }
        return arr;

    }


    // ~~~~~~~~~~~~ game state calculations ~~~~~~~~~~~~~~~~~~~

    public  GameState checkDiagnol(){
        String[][] arr = new String[3][3];
        arr = putInArray();
        state = GameState.PLAYING;
        for (int w = 0; w < 3; w++) {

            if (!arr[w][w].equals( "X"))
                break;
            if (w == 3 - 1) {
                return state = GameState.X_PLAYER_WON;
            }

        }

        for (int w = 0; w < 3; w++) {

            if (!arr[w][((3-1)-w)].equals( "X"))
                break;
            if (w == 3 - 1) {
                return state = GameState.X_PLAYER_WON;
            }

        }
        for (int w = 0; w < 3; w++) {

            if (!arr[w][w].equals("O"))
                break;
            if (w == 3 - 1) {
                return state = GameState.Y_PLAYER_WON;
            }

        }

        for (int w = 0; w < 3; w++) {

            if (!arr[w][((3-1)-w)].equals( "O"))
                break;
            if (w == 3 - 1) {
                return state = GameState.Y_PLAYER_WON;
            }

        }
        return state;
    }

    public  GameState checkVertical(){
        String[][] arr = new String[3][3];
        arr = putInArray();
        state = GameState.PLAYING;
        for (int w = 0; w < 3; w++) {
            for (int i = 0; i < 3; i++) {
                if (!arr[i][w].equals( "X"))
                    break;
                if (i == 3 - 1) {
                    return state = GameState.X_PLAYER_WON;
                }
            }
        }
        for (int w = 0; w < 3; w++) {
            for (int i = 0; i < 3; i++) {
                if (!arr[i][w].equals( "O"))
                    break;
                if (i == 3 - 1) {
                    return state = GameState.Y_PLAYER_WON;
                }
            }
        }
        return state;
    }

    public GameState checkHorizontal(){
        String[][] arr = new String[3][3];
        arr = putInArray();
        state = GameState.PLAYING;
        //check row for O
        for (int w = 0; w < 3; w++) {
            for (int i = 0; i < 3; i++) {
                if (!arr[w][i].equals( "O"))
                    break;
                if (i == 3 - 1) {
                    return state = GameState.Y_PLAYER_WON;
                }
            }
        }
        for (int w = 0; w < 3; w++) {
            for (int i = 0; i < 3; i++) {
                if (!arr[w][i].equals( "X"))
                    break;
                if (i == 3 - 1) {
                    return state = GameState.X_PLAYER_WON;
                }
            }
        }

        return  state;

    }

    public GameState checkGameState(){
        GameState horizontalState =  checkHorizontal();
        GameState VerticalState  = checkVertical();
        GameState diagnolState  = checkDiagnol();

        if( horizontalState != GameState.PLAYING  ){
            state = checkHorizontal();
        }
        if( VerticalState != GameState.PLAYING){
            state = checkVertical();
        }
        if(diagnolState != GameState.PLAYING){
            state = checkDiagnol();
        }
        if(moveCounter == (Math.pow(3,2)-1)){
            return state = GameState.TIE;
        }
        if(     VerticalState == GameState.PLAYING   &&
                horizontalState == GameState.PLAYING &&
                diagnolState ==  GameState.PLAYING)
        {
            state = GameState.PLAYING;
        }

        return state;

    }


    // ~~~~~~~~~~~~ adding move + out of bounce error

    private void addMoveToRows(int col , Piece piece, ArrayList<String> chosenRow){
        for (int i = 0; i < 3 ; i++) {
            if(col == i){

                if(piece.equals(Piece.O) || piece.equals(Piece.o)){
                    chosenRow.set(i,"O");

                }
                if(piece.equals(Piece.X) || piece.equals(Piece.x)){
                    chosenRow.set(i,"X");

                }
            }
        }
    }

    public boolean checkInPut(int row, int col){
        boolean check = row >= 3 || row < 0;
        boolean check2 = col >= 3 || col <0;
        if(check || check2){
            return false;
        }
        return true;
    }


    public void addMove(int row, int col , Piece piece){

            moveCounter++;


            if (row == 0) {
                addMoveToRows(col   , piece , row1);
            }
            if (row == 1) {
                addMoveToRows(col   , piece , row2);
            }
            if (row == 2) {
                addMoveToRows(col   , piece , row3);
            }



    }






    @Override
    public String toString() {
        return "Board{" +
                "row1=" + row1 +
                ", row2=" + row2 +
                ", row3=" + row3 +
                '}';
    }




    // ~~~~~~~~~~ Board getter and setters ~~~~~~~~~~~~~~~~~~~
    public ArrayList<String> getRow1() {
        return row1;
    }

    public void setRow1(ArrayList<String> row1) {
        this.row1 = row1;
    }

    public ArrayList<String> getRow2() {
        return row2;
    }

    public void setRow2(ArrayList<String> row2) {
        this.row2 = row2;
    }

    public ArrayList<String> getRow3() {
        return row3;
    }

    public void setRow3(ArrayList<String> row3) {
        this.row3 = row3;
    }


}
