package ca.pledgetovote.controllers;

import ca.pledgetovote.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;


/**
 * game controller class allows the server and the program to communicate
 * */

@RestController
public class GameController {

    private AtomicLong nextId = new AtomicLong();
    private ArrayList<Game> games = new ArrayList<>();





    @GetMapping("/about")
    public String returnName(){
        return "Awesome Tic Tac Toe game written by <Faraz Fazlalizadeh>!\n";
    }

    @GetMapping("/games")
    public ArrayList<Game> returnGames(){
       return games;
    }

    @PostMapping("/games")
    public Game creatNewGame(@RequestBody Game game){

        game.setId(nextId.incrementAndGet());
        games.add(game);
        return game;
    }


    @GetMapping("/games/{id}")
    public Game getGameData(@PathVariable ("id") long gameId){
        for(Game game:games){
            if(game.getId()==gameId){
                return game;
            }
        }
        throw new ResourceNotFoundException("System Was not able to find game with the id:"+ gameId  );
    }

    @GetMapping("/games/{id}/moves")
    public ArrayList<Move> getMoves(@PathVariable("id") long gameId){
        for(Game game:games){
            if(game.getId()==gameId){
                return game.getMoves();
            }
        }
        throw new ResourceNotFoundException("System Was not able to find game with the id:"+ gameId  );

    }

    @PostMapping("/games/{id}/moves")
    public Move postMoves(@PathVariable("id") long gameId ,
                                     @RequestBody checkInput checkInput
                                     ) {
        for(Game game:games){
            if(game.getId()==gameId){
                Cell cell = new Cell(checkInput.getRow(),checkInput.getCol(),checkInput.getEnumPiece());

                return game.makeMove(cell);
            }
        }
        throw new ResourceNotFoundException("System Was not able to find game with the id:"+ gameId  );

    }

    @GetMapping("/games/{id}/board")
    public Board getBoard(@PathVariable("id") long boardId ){

        for(Game game:games){
            if(game.getId() == boardId){
                 return game.getBoard();
            }
        }
        throw new ResourceNotFoundException("System Was not able to find game with the id:"+ boardId  );
    }





    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String Msg) {
            super(Msg);
        }
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public static class InvalidMoveException extends RuntimeException {
        public InvalidMoveException(String Msg)
        {
            super(Msg);
        }
    }

}
