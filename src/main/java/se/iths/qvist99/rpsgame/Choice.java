package se.iths.qvist99.rpsgame;

public enum Choice {
    ROCK, PAPER, SCISSOR;

    boolean beats(Choice other) {
        return (this == ROCK && other == SCISSOR) ||
                (this == PAPER && other == ROCK) ||
                (this == SCISSOR && other == PAPER);
    }
}
