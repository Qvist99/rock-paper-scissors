package se.iths.qvist99.rpsgame;

import java.util.Random;

public class GameController {
    int firstTo, userScore, computerScore, currentRound;
    private final Random random = new Random();


    Referee referee = new Referee();

    public void startGame() {
        /*firstTo = inputHandler.setGameLength();
        runGame();*/
    }


    private void runGame() {
        do {
            currentRound++;
            System.out.println("Round: " + currentRound);

            Choice computerChoice = randomizeComputerChoice();
            Choice userChoice = Choice.ROCK; //inputHandler.userSelectChoice();

            System.out.println("User selects: " + userChoice + " PC selects: " + computerChoice);

            String outcome = referee.determineOutcome(userChoice, computerChoice);

            if (outcome.equals("Draw")) {
                System.out.println("Round ends in a " + outcome);
            } else if (outcome.equals("User")) {
                System.out.println(outcome + " wins the round");
                userScore++;
            } else {
                System.out.println(outcome + " wins the round");
                computerScore++;
            }

            System.out.println("Current score is user: " + userScore + " Computer: " + computerScore);

        } while (userScore < firstTo && computerScore < firstTo);

        if (userScore >= firstTo) {
            System.out.println("User won the game!");
        } else {
            System.out.println("Computer won the game!");
        }

    }

    //create methods for updating userchoice, gameLength


    public void setFirstTo(int firstTo) {
        this.firstTo = firstTo;
        IO.println("First to set to" + firstTo);
    }

    private Choice randomizeComputerChoice() {
        Choice[] values = Choice.values();
        return values[random.nextInt(values.length)];
    }
}
