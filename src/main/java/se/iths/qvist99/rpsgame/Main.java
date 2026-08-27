package se.iths.qvist99.rpsgame;


/*
* The computer randomly selects Rock, paper or scissors
* User selects its own option
* Compare the choices and write who won the game
* If user wins display that they won + what the computer selected
* If computer wins display that the user lost and what the computer selected
*
*
* */

public class Main {
    public static void main(String[] args){
        GameController game = new GameController();

        game.startGame();

    }
}
