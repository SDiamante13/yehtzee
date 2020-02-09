package tech.pathtoprogramming.kata.utils;

import java.util.Random;

public class Dice {

    private static final int MAX = 6;
    private static final int MIN = 1;

    public static int[] roll() {
        int[] rolledDice = new int[5];
        Random random = new Random();
        for (int index = 0; index < rolledDice.length; index++) {
            rolledDice[index] = random.nextInt((MAX - MIN) + 1) + MIN;
        }
        return rolledDice;
    }
}
