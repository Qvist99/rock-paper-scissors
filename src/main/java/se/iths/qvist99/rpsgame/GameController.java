package se.iths.qvist99.rpsgame;

import java.util.Random;
import java.util.Scanner;

public class GameController {
    enum Choice {
        ROCK, PAPER, SCISSOR;

        boolean beats(Choice other) {
            return (this == ROCK && other == SCISSOR) ||
                    (this == PAPER && other == ROCK) ||
                    (this == SCISSOR && other == PAPER);
        }
    }

    int firstTo, userScore, computerScore, currentRound;
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();


    public void startGame() {
        setGameLength();
        runGame();
    }

    private Choice randomizeComputerChoice() {
        Choice[] values = Choice.values();
        return values[random.nextInt(values.length)];
    }

    private Choice userSelectChoice() {
        String userInput;
        boolean valid;
        System.out.println("Select Rock, Paper or Scissor");
        do {
            userInput = scanner.nextLine().toUpperCase();

            checkIfTerminateGame(userInput);

            valid = userInput.equals("ROCK") || userInput.equals("PAPER") || userInput.equals("SCISSOR");

            if (!valid) {
                System.out.println("Incorrect choice. Please choose between Rock, Paper or Scissor");
            }

        } while (!valid);

        int indexPos = convertChoiceToInt(userInput);

        Choice[] values = Choice.values();

        return values[indexPos];
    }

    private void runGame() {
        do {
            currentRound++;
            System.out.println("Round: " + currentRound);

            Choice computerChoice = randomizeComputerChoice();
            Choice userChoice = userSelectChoice();

            System.out.println("User selects: " + userChoice + " PC selects: " + computerChoice);

            String outcome = determineOutcome(userChoice, computerChoice);

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
        } else if (computerScore == firstTo) {
            System.out.println("Computer won the game!");
        }

    }

    private String determineOutcome(Choice user, Choice computer) {
        if (user == computer) return "Draw";

        return user.beats(computer) ? "User" : "Computer";
    }

    private int convertChoiceToInt(String value) {
        //0: ROCK, PAPER 1, SCISSOR 2

        return switch (value) {
            case "ROCK" -> 0;
            case "PAPER" -> 1;
            case "SCISSOR" -> 2;
            default -> 0; // Should never happen once again
        };
    }

    private void setGameLength() {
        String userInput;
        boolean valid;

        System.out.println("Select a difficulty: Short(First to 3), Normal(First to 5), Long(First to 7)");

        do {
            userInput = scanner.nextLine().toUpperCase();

            checkIfTerminateGame(userInput);

            valid = userInput.equals("SHORT") || userInput.equals("NORMAL") || userInput.equals("LONG");

            if (!valid) {
                System.out.println("Incorrect game length selected. Please select Short,Normal or Long");
            }

        } while (!valid);

        switch (userInput) {
            case "SHORT" -> {
                firstTo = 3;
            }
            case "NORMAL" -> {
                firstTo = 5;
            }
            case "LONG" -> {
                firstTo = 7;
            }
        }
    }

    private void checkIfTerminateGame(String value) {
        if (value.equals("Q")) {
            System.out.println("Exiting game...");
            System.exit(0);
        }
    }
}
