package tech.pathtoprogramming.kata;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

public class ScoreKeeperTest {

    private ScoreKeeper scoreKeeper;

    @Before
    public void setUp() throws Exception {
        scoreKeeper = new ScoreKeeper();
    }

    @Test
    public void calculateScore_returnsTheScoreForTheOnesCategory() {
        int[] diceRoll = new int[]{1, 1, 2, 2, 3};
        ScoringCategory category = ScoringCategory.ONES;
        int score = scoreKeeper.calculateScore(diceRoll, category);

        assertThat(score).isEqualTo(2);
    }

    @Test
    public void calculateScore_returnsTheScoreForTheTwosCategory() {
        int[] diceRoll = new int[]{1, 1, 2, 2, 3};
        ScoringCategory category = ScoringCategory.TWOS;
        int score = scoreKeeper.calculateScore(diceRoll, category);

        assertThat(score).isEqualTo(4);
    }

    @Test
    public void calculateScore_returnsTheScoreForTheThreesCategory() {
        int[] diceRoll = new int[]{1, 1, 2, 2, 3};
        ScoringCategory category = ScoringCategory.THREES;
        int score = scoreKeeper.calculateScore(diceRoll, category);

        assertThat(score).isEqualTo(3);
    }

    @Test
    public void calculateScore_returnsTheScoreForTheFoursCategory() {
        int[] diceRoll = new int[]{4, 4, 5, 4, 6};
        ScoringCategory category = ScoringCategory.FOURS;
        int score = scoreKeeper.calculateScore(diceRoll, category);

        assertThat(score).isEqualTo(12);
    }

    @Test
    public void calculateScore_returnsTheScoreForTheFivesCategory() {
        int[] diceRoll = new int[]{4, 4, 5, 4, 6};
        ScoringCategory category = ScoringCategory.FIVES;
        int score = scoreKeeper.calculateScore(diceRoll, category);

        assertThat(score).isEqualTo(5);
    }

    @Test
    public void calculateScore_returnsTheScoreForTheSixesCategory() {
        int[] diceRoll = new int[]{4, 4, 5, 4, 6};
        ScoringCategory category = ScoringCategory.SIXES;
        int score = scoreKeeper.calculateScore(diceRoll, category);

        assertThat(score).isEqualTo(6);
    }

    @Test
    public void calculateScore_returnsTheScoreForTheThreeOfAKindCategory() {
        int[] diceRoll = new int[]{4, 4, 5, 4, 6};
        ScoringCategory category = ScoringCategory.THREE_OF_A_KIND;
        int score = scoreKeeper.calculateScore(diceRoll, category);

        assertThat(score).isEqualTo(23);
    }

    @Test
    public void calculateScore_returnsTheScoreForTheThreeOfAKindCategoryForObviousYahtzee() {
        int[] diceRoll = new int[]{5, 5, 5, 5, 5};
        ScoringCategory category = ScoringCategory.THREE_OF_A_KIND;
        int score = scoreKeeper.calculateScore(diceRoll, category);

        assertThat(score).isEqualTo(25);
    }

    @Test
    public void calculateScore_returnsTheScoreForTheFourOfAKindCategory() {
        int[] diceRoll = new int[]{4, 4, 4, 4, 1};
        ScoringCategory category = ScoringCategory.FOUR_OF_A_KIND;
        int score = scoreKeeper.calculateScore(diceRoll, category);

        assertThat(score).isEqualTo(17);
    }

    @Test
    public void calculateScore_returnsTheScoreForTheSmStraightCategory() {
        int[] diceRoll = new int[]{1, 2, 3, 4, 1};
        ScoringCategory category = ScoringCategory.SM_STRAIGHT;
        int score = scoreKeeper.calculateScore(diceRoll, category);

        assertThat(score).isEqualTo(30);
    }

    @Test
    public void calculateScore_returnsTheScoreForTheSmStraightCategoryForZero() {
        int[] diceRoll = new int[]{1, 1, 1, 4, 1};
        ScoringCategory category = ScoringCategory.SM_STRAIGHT;
        int score = scoreKeeper.calculateScore(diceRoll, category);

        assertThat(score).isEqualTo(0);
    }

    @Test
    public void calculateScore_returnsTheScoreForTheLgStraightCategory() {
        int[] diceRoll = new int[]{1, 2, 3, 4, 5};
        ScoringCategory category = ScoringCategory.LG_STRAIGHT;
        int score = scoreKeeper.calculateScore(diceRoll, category);

        assertThat(score).isEqualTo(40);
    }

    @Test
    public void calculateScore_returnsTheScoreForTheLgStraightCategoryOtherCombo() {
        int[] diceRoll = new int[]{2, 3, 4, 5, 6};
        ScoringCategory category = ScoringCategory.LG_STRAIGHT;
        int score = scoreKeeper.calculateScore(diceRoll, category);

        assertThat(score).isEqualTo(40);
    }

    @Test
    public void calculateScore_returnsTheScoreForTheLgStraightCategoryForZero() {
        int[] diceRoll = new int[]{1, 2, 2, 1, 5};
        ScoringCategory category = ScoringCategory.LG_STRAIGHT;
        int score = scoreKeeper.calculateScore(diceRoll, category);

        assertThat(score).isEqualTo(0);
    }

    @Test
    public void getMost_returnsTheDieAndTheAmountOfOccurences() {
        List<BigDecimal> bigDecimals = Arrays.asList(
                valueOf(1),
                valueOf(1),
                valueOf(2),
                valueOf(1),
                valueOf(3));

        int[] result = scoreKeeper.getMostNumberOfOccurrences(bigDecimals);

        assertThat(result[0]).isEqualTo(1);
        assertThat(result[1]).isEqualTo(3);
    }

    @Test
    public void getNumberOfOccurences_returnsTheNumberThatOccursTheMost_andTheNumberOfTimes() {
        List<BigDecimal> numbers = Arrays.asList(valueOf(5), valueOf(4), valueOf(5), valueOf(5), valueOf(5));
        int[] expectedArray = new int[]{5, 4};

        int[] results = scoreKeeper.getMostNumberOfOccurrences(numbers);
        assertThat(results).isEqualTo(expectedArray);
    }
}
