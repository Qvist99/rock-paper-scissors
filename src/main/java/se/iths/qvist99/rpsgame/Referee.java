package se.iths.qvist99.rpsgame;

public class Referee {
    String determineOutcome(Choice user, Choice computer) {
        if (user == computer) return "Draw";

        return user.beats(computer) ? "User" : "Computer";
    }
}
