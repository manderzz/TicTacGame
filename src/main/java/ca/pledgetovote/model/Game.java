package ca.pledgetovote.model;

import ca.pledgetovote.controllers.GameController;
import ca.pledgetovote.enumClasses.GameState;
import ca.pledgetovote.enumClasses.Piece;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * this class takes care of the game logic
 */


public class Game {

    private AtomicLong moveNumber = new AtomicLong();
    private long id;
    private String description;
    private GameState status;
    private ArrayList<Move> moves;
    private Board board;
    private Move move;

    //~~~~~~~~~ board related helper methods ~~~~~~~~~~~~~~
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }



    //~~~~~~~~~~ Games methods ~~~~~~~~~~~~~~
    public Game(){}

    public Game(String description, GameState status, ArrayList<Move> moves, Board board, Move move) {
        this.description = description;
        this.status = status;
        this.moves = moves;
        this.board = board;
        this.move = move;
    }

    public GameState getStatus() {
        return status;
    }

    public void setStatus(GameState status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.board = new Board();
        this.moves = new ArrayList<>();
        status = board.checkGameState();

    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {

        return id;
    }



    // ~~~~~~~~ move helper Methods ~~~~~~~~~~~~
    public ArrayList<Move> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Move> moves) {
        this.moves = moves;
    }

    public boolean checkDuplication( Cell cell  , long moveNumber){
        for (int i = 0; i < moves.size() ; i++) {
            Cell cellTemp = moves.get(i).getCell();
            if(cellTemp.getRow() == cell.getRow() && cellTemp.getCol() == cell.getCol()){
                return false;
            }
        }

        return true;
    }

    public void checkStarting(Cell cell , AtomicLong moveNumber){
        if(moveNumber.get() == 1 && cell.getPiece() != Piece.X){
            moveNumber.set(moveNumber.decrementAndGet());
            throw new GameController.InvalidMoveException(" must start with X");
        }
    }

    public void checkTurn(Cell cell , AtomicLong moveNumber){
        if(moveNumber.get() %2 == 0 && cell.getPiece() != Piece.O){
            moveNumber.set(moveNumber.decrementAndGet());
            throw new GameController.ResourceNotFoundException(" Wrong move it is 'O' turn");
        }
        if(moveNumber.get() %2 != 0 && cell.getPiece() != Piece.X){
            moveNumber.set(moveNumber.decrementAndGet());
            throw new GameController.ResourceNotFoundException(" Wrong move it is 'X' turn");
        }
    }

    public Move makeMove(Cell cell )  {

        if(checkDuplication(cell,moveNumber.incrementAndGet())){
            if(status != GameState.PLAYING){
                throw new GameController.InvalidMoveException("game is over cannot go again ");
            }
            checkStarting(cell, moveNumber);
            checkTurn(cell, moveNumber);
            moveNumber.set(moveNumber.decrementAndGet());
            if(board.checkInPut(cell.getRow(),cell.getCol())){

                move = new Move(cell);
                move.setMoveNumber(moveNumber.incrementAndGet());
                moves.add(move);
                setMoves(moves);
                board.addMove(cell.getRow(), cell.getCol(), cell.getPiece());
                status = board.checkGameState();

            }else{
                moveNumber.set(moveNumber.decrementAndGet());
                throw new GameController.InvalidMoveException("Row: "+ cell.getRow()
                        + " Col: "+ cell.getCol() +" Invalid move location (must be 0-2 for row and column)" );
            }

        }else {
            moveNumber.set(moveNumber.decrementAndGet());
            throw new GameController.InvalidMoveException("Row: "+ cell.getRow()
                    + " Col: "+ cell.getCol() +" Invalid move location (duplicate of earlier move)" );
        }
        return move;

    }






}
