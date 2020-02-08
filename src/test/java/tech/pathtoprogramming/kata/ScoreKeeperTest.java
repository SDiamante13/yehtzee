package tech.pathtoprogramming.kata;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.valueOf;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ScoreKeeperTest {

    private BasicUtils basicUtils = new BasicUtils();

    private ScoreKeeper scoreKeeper;

    @Before
    public void setUp() throws Exception {
        scoreKeeper = new ScoreKeeper(basicUtils);
    }

    @Test
    public void calculateScore_returnstheScoreForTheOnesCategory() {
        int[] diceRoll = new int[]{1, 1, 2, 2, 3};
        ScoringCategory category = ScoringCategory.ONES;
        int score = scoreKeeper.calculateScore(diceRoll, category);

        assertThat(score).isEqualTo(2);
    }

    @Test
    public void calculateScore_returnstheScoreForTheTwosCategory() {
        int[] diceRoll = new int[]{1, 1, 2, 2, 3};
        ScoringCategory category = ScoringCategory.TWOS;
        int score = scoreKeeper.calculateScore(diceRoll, category);

        assertThat(score).isEqualTo(4);
    }

    @Test
    public void calculateScore_returnstheScoreForTheThreesCategory() {
        int[] diceRoll = new int[]{1, 1, 2, 2, 3};
        ScoringCategory category = ScoringCategory.THREES;
        int score = scoreKeeper.calculateScore(diceRoll, category);

        assertThat(score).isEqualTo(3);
    }

    @Test
    public void calculateScore_returnstheScoreForTheFoursCategory() {
        int[] diceRoll = new int[]{4, 4, 5, 4, 6};
        ScoringCategory category = ScoringCategory.FOURS;
        int score = scoreKeeper.calculateScore(diceRoll, category);

        assertThat(score).isEqualTo(12);
    }

    @Test
    public void calculateScore_returnstheScoreForTheFivesCategory() {
        int[] diceRoll = new int[]{4, 4, 5, 4, 6};
        ScoringCategory category = ScoringCategory.FIVES;
        int score = scoreKeeper.calculateScore(diceRoll, category);

        assertThat(score).isEqualTo(5);
    }

    @Test
    public void calculateScore_returnstheScoreForTheSixesCategory() {
        int[] diceRoll = new int[]{4, 4, 5, 4, 6};
        ScoringCategory category = ScoringCategory.SIXES;
        int score = scoreKeeper.calculateScore(diceRoll, category);

        assertThat(score).isEqualTo(6);
    }

    @Test
    public void calculateScore_returnstheScoreForTheThreeOfAKindCategory() {
        int[] diceRoll = new int[] {4, 4, 5, 4, 6 };
        ScoringCategory category = ScoringCategory.THREE_OF_A_KIND;
        int score = scoreKeeper.calculateScore(diceRoll, category);

        assertThat(score).isEqualTo(23);
    }

    @Test
    public void calculateScore_returnstheScoreForTheThreeOfAKindCategoryForObviousYahtzee() {
        int[] diceRoll = new int[] {5, 5, 5, 5, 5 };
        ScoringCategory category = ScoringCategory.THREE_OF_A_KIND;
        int score = scoreKeeper.calculateScore(diceRoll, category);

        assertThat(score).isEqualTo(25);
    }

    @Test
    public void calculateScore_returnstheScoreForTheFourOfAKindCategory() {
        int[] diceRoll = new int[] {4, 4, 4, 4, 1 };
        ScoringCategory category = ScoringCategory.FOUR_OF_A_KIND;
        int score = scoreKeeper.calculateScore(diceRoll, category);

        assertThat(score).isEqualTo(17);
    }

    @Test
    public void getMost_returnsTheDieAndTheAmountOfOccurences() {
        List<BigDecimal> bigDecimals = Arrays.asList(
                valueOf(1),
                valueOf(1),
                valueOf(2),
                valueOf(1),
                valueOf(3));

        int[] result = scoreKeeper.getMostNumberOfOccurences(bigDecimals);

        assertThat(result[0]).isEqualTo(1);
        assertThat(result[1]).isEqualTo(3);

    }

    @Test
    public void getNumberOfOccurences_returnsTheNumberThatOccursTheMost_andTheNumberOfTimes() {
        List<BigDecimal> numbers = Arrays.asList(valueOf(5), valueOf(4), valueOf(5). valueOf(5), valueOf(5));
        int[] expectedArray = new int[] {5, 20};

        int[] results = scoreKeeper.getMostNumberOfOccurences(numbers);
        assertThat(results).isEqualTo(expectedArray);
    }
}
