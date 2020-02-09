package tech.pathtoprogramming.kata;

import tech.pathtoprogramming.kata.model.ScoringCategory;
import tech.pathtoprogramming.kata.service.ScoreService;
import tech.pathtoprogramming.kata.utils.Dice;


public class YehtzeeApplication {

    public static void main(String[] args) {
        ScoreService scoreService = new ScoreService();

        for (int numberOfRolls = 0; numberOfRolls < ScoringCategory.values().length; numberOfRolls++) {
            System.out.println("\nDICE ROLL " + numberOfRolls);
            int[] randomRoll = Dice.roll();
            for (int die : randomRoll) {
                System.out.print(die + " ");
            }
            ScoringCategory randomCategory = ScoringCategory.getRandomCategory();
            System.out.println("\nCategory: " + randomCategory + " Score: " + scoreService.calculateScore(randomRoll, randomCategory));
        }
    }
}
