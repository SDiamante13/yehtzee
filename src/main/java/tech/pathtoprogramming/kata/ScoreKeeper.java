package tech.pathtoprogramming.kata;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Arrays.sort;
import static tech.pathtoprogramming.kata.ScoringCategory.LG_STRAIGHT;
import static tech.pathtoprogramming.kata.ScoringCategory.SM_STRAIGHT;

public class ScoreKeeper {

    public int calculateScore(int[] diceRoll, ScoringCategory category) {
        validateDiceRoll(diceRoll);
        switch (category) {
            case ONES:
                return computeForNumbersCategory(diceRoll, 1);
            case TWOS:
                return computeForNumbersCategory(diceRoll, 2);
            case THREES:
                return computeForNumbersCategory(diceRoll, 3);
            case FOURS:
                return computeForNumbersCategory(diceRoll, 4);
            case FIVES:
                return computeForNumbersCategory(diceRoll, 5);
            case SIXES:
                return computeForNumbersCategory(diceRoll, 6);
            case THREE_OF_A_KIND:
            case FOUR_OF_A_KIND:
                return computeForThreeOrFourOfAKind(diceRoll);
            case SM_STRAIGHT:
                return computeStraight(diceRoll, SM_STRAIGHT);
            case LG_STRAIGHT:
                return computeStraight(diceRoll, LG_STRAIGHT);
            case FULL_HOUSE:
                return computeForFullHouse(diceRoll);
            case YAHTZEE:
                return computeForYahtzee(diceRoll);
            case CHANCE:
                 return computeForChance(diceRoll);
            default:
                return 0;
        }
    }

    private int computeForChance(int[] diceRoll) {
        return Arrays.stream(diceRoll).boxed().reduce(0, Integer::sum);
    }

    private void validateDiceRoll(int[] diceRoll) throws IllegalArgumentException {
        for (int roll : diceRoll)
            if (roll < 0 || roll > 6)
                throw new IllegalArgumentException("Valid dice rolls must be between 1-6");
    }

    private int computeForYahtzee(int[] diceRoll) {
        Set<Integer> yahtzee = new HashSet<>();
        Arrays.stream(diceRoll).boxed().forEach(yahtzee::add);

        return yahtzee.size() == 1 ? 50 : 0;
    }

    private int computeForFullHouse(int[] diceRoll) {
        Set<Integer> fullHouse = new HashSet<>();
        Arrays.stream(diceRoll).boxed().forEach(fullHouse::add);

        return fullHouse.size() == 2 ? 25 : 0;
    }

    public int[] getMostNumberOfOccurrences(List<BigDecimal> diceRoll) {
        Multiset<BigDecimal> multiset = HashMultiset.create(diceRoll);
        BigDecimal maxElement = BigDecimal.ZERO;
        int maxCount = 0;
        for (Multiset.Entry<BigDecimal> entry : multiset.entrySet()) {
            if (entry.getCount() > maxCount) {
                maxElement = entry.getElement();
                maxCount = entry.getCount();
            }
        }
        return new int[]{maxElement.intValue(), maxCount};
    }

    private int computeStraight(int[] diceRoll, ScoringCategory category) {
        sort(diceRoll);

        int sequenceCounter = 1;
        int i = 0;
        int j = 1;
        int THRESHOLD = 4;
        int POINTS = 30;

        if (category == LG_STRAIGHT) {
            THRESHOLD = 5;
            POINTS = 40;
        }

        while (j < diceRoll.length) {
            if (diceRoll[j] - diceRoll[i] == 1) sequenceCounter++;
            else sequenceCounter = 1;
            i++;
            j++;
        }

        return sequenceCounter >= THRESHOLD ? POINTS : 0;
    }

    private int computeForNumbersCategory(int[] diceRoll, int number) {
        AtomicInteger score = new AtomicInteger(0);

        for (int die : diceRoll) {
            if (die == number) score.addAndGet(number);
        }

        return score.get();
    }

    private int computeForThreeOrFourOfAKind(int[] diceRoll) {
        List<BigDecimal> bigDecimals = BasicUtils.toBigDecimalList(diceRoll);
        int[] mode = getMostNumberOfOccurrences(bigDecimals);

        return mode[0] * mode[1];
    }
}
