package se.iths.qvist99.rpsgame;

import java.util.Scanner;
import java.util.Set;

public class InputHandler {
    private final Scanner scanner = new Scanner(System.in);

    Choice userSelectChoice() {
        String prompt = "Select Rock, Paper or Scissor";
        String errorMessage = "Incorrect choice. Please choose between Rock, Paper or Scissor";
        Set<String> validInputs = Set.of("ROCK", "PAPER", "SCISSOR");
        String userInput = revalidatedInput(prompt, errorMessage, validInputs);

        return Choice.valueOf(userInput);
    }

    int setGameLength() {
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
    
    private void checkIfTerminateGame(String value) {
        if (value.equals("Q")) {
            System.out.println("Exiting game...");
            System.exit(0);
        }
    }


}
