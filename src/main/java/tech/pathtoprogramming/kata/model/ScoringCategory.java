package tech.pathtoprogramming.kata.model;

import java.util.Random;

public enum ScoringCategory {
    ONES, TWOS, THREES, FOURS, FIVES, SIXES, THREE_OF_A_KIND,
    FOUR_OF_A_KIND, SM_STRAIGHT, LG_STRAIGHT, FULL_HOUSE, YAHTZEE, CHANCE;

    public static ScoringCategory getRandomCategory() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
