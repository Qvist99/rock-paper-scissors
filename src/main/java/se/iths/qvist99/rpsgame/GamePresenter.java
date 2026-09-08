package se.iths.qvist99.rpsgame;

import java.util.Scanner;
import java.util.Set;

public class GamePresenter {
    private final Scanner scanner = new Scanner(System.in);
    private GameFrame gameFrame;
    private final GameController gameController;

    public GamePresenter(GameController gameController) {
        this.gameController = gameController;
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public void onGameLengthSelected(int firstTo) {
        gameController.setFirstTo(firstTo);
        gameFrame.setFirstToLabel(firstTo);
        IO.println("firstTo" + firstTo);
        // Show next panel
        gameFrame.selectPanel("PANEL_TWO");
    }

    public void onUserChoiceSelected(Choice userChoice) {
        int firstTo = gameController.getFirstTo();
        // Set user choice for curr round > set random computer choice

        gameController.setUserChoice(userChoice);

        gameController.randomizeComputerChoice();

        //Display the choice the user made and what the computer made either in new panel or in the same panel somehow

        // Determine to outcome of the round which in turn updates the score.
        String outcome = gameController.determineOutcome();

        Choice computerChoice = gameController.getComputerChoice();

        gameFrame.showRoundOutcome(outcome, computerChoice, userChoice);


        //Update the score in the gameframe to represent the new values

        int userScore = gameController.getUserScore();
        int computerScore = gameController.getComputerScore();

        //Need to display in some way what the computer picked and who is the winner of the round if there is one


        gameFrame.setCurrScore(userScore, computerScore);

        //Swap to win screen and display winner of the game and give option to rematch or change game length
        if (userScore >= firstTo || computerScore >= firstTo) {
            IO.println("Netanyahu we have an winner");
        }

    }


    public Choice userSelectChoice() {
        String prompt = "Select Rock, Paper or Scissor";
        String errorMessage = "Incorrect choice. Please choose between Rock, Paper or Scissor";
        Set<String> validInputs = Set.of("ROCK", "PAPER", "SCISSOR");
        String userInput = revalidatedInput(prompt, errorMessage, validInputs);

        return Choice.valueOf(userInput);
    }

    public int setGameLength() {
        String prompt = "Select a difficulty: Short(First to 3), Normal(First to 5), Long(First to 7)";
        String errorMessage = "Incorrect game length selected. Please select Short,Normal or Long";
        Set<String> validValues = Set.of("SHORT", "NORMAL", "LONG");
        String userInput = revalidatedInput(prompt, errorMessage, validValues);

        return switch (userInput) {
            case "SHORT" -> 3;
            case "NORMAL" -> 5;
            case "LONG" -> 7;
            default -> throw new IllegalStateException("Unreachable " + userInput);
        };
    }

    private String revalidatedInput(String prompt, String invalidMessage, Set<String> validValues) {
        System.out.println(prompt);
        String input;
        boolean valid;

        do {
            input = scanner.nextLine().toUpperCase();
            checkIfTerminateGame(input);
            valid = validValues.contains(input);

            if (!valid) {
                System.out.println(invalidMessage);
            }

        } while (!valid);

        return input;
    }

    private static void checkIfTerminateGame(String value) {
        if (value.equals("Q")) {
            System.out.println("Exiting game...");
            System.exit(0);
        }
    }


}
