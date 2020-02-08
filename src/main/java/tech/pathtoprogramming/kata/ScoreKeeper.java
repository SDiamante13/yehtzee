package tech.pathtoprogramming.kata;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.Arrays.sort;
import static tech.pathtoprogramming.kata.ScoringCategory.LG_STRAIGHT;
import static tech.pathtoprogramming.kata.ScoringCategory.SM_STRAIGHT;

public class ScoreKeeper {

    public int calculateScore(int[] diceRoll, ScoringCategory category) {
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
                return computeForThreeOfKind(diceRoll);
            case FOUR_OF_A_KIND:
                return computeForThreeOfKind(diceRoll);
            case SM_STRAIGHT:
                return computeStraight(diceRoll, SM_STRAIGHT);
            case LG_STRAIGHT:
                return computeStraight(diceRoll, LG_STRAIGHT);
//            case FULL_HOUSE:
//                 return 0;
//                break;
//            case YAHTZEE:
//                 return 0;
//                break;
//            case CHANCE:
//                 return 0;
//                break;
            default:
                return 0;
        }
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

    private int computeForThreeOfKind(int[] diceRoll) {
        List<BigDecimal> bigDecimals = BasicUtils.toBigDecimalList(diceRoll);
        int[] mode = getMostNumberOfOccurrences(bigDecimals);
        BigDecimal sum = new BigDecimal(0);

        List<BigDecimal> filteredList = bigDecimals.stream().filter(bigDecimal -> mode[0] != bigDecimal.intValue()).collect(Collectors.toList());

        if (!filteredList.isEmpty()) {
            sum = filteredList.stream().reduce(BigDecimal::add).get();
        }

        return mode[0] * mode[1] + sum.intValue();
    }

    public int[] getMostNumberOfOccurrences(List<BigDecimal> diceRoll) {
        Multiset<BigDecimal> multiset = HashMultiset.create(diceRoll);
        BigDecimal maxElement = null;
        int maxCount = 0;
        for (Multiset.Entry<BigDecimal> entry : multiset.entrySet()) {
            if (entry.getCount() > maxCount) {
                maxElement = entry.getElement();
                maxCount = entry.getCount();
            }
        }
        return new int[]{maxElement.intValue(), maxCount};
    }
}
