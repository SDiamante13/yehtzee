package tech.pathtoprogramming.kata;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ScoreKeeper {

    private final BasicUtils basicUtils;

    public ScoreKeeper(BasicUtils basicUtils) {
        this.basicUtils = basicUtils;
    }

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
            case FOUR_OF_A_KIND:
                return computeForThreeOfKind(diceRoll);
            default:
                return 0;
        }
    }

    private int computeForNumbersCategory(int[] diceRoll, int number) {
        AtomicInteger score = new AtomicInteger(0);

        for (int die : diceRoll) {
            if (die == number) score.addAndGet(number);
        }

        return score.get();
    }

    private int computeForThreeOfKind(int[] diceRoll) {
        List<BigDecimal> bigDecimals = basicUtils.toBigDecimalList(diceRoll);
        int[] mode = getMostNumberOfOccurences(bigDecimals);
        BigDecimal sum = new BigDecimal(0);

        List<BigDecimal> filteredList = bigDecimals.stream().filter(bigDecimal -> mode[0] != bigDecimal.intValue()).collect(Collectors.toList());

        if (!filteredList.isEmpty()) {
            sum = filteredList.stream().reduce(BigDecimal::add).get();
        }

        return mode[0] * mode[1] + sum.intValue();
    }

    public int[] getMostNumberOfOccurences(List<BigDecimal> diceRoll) {
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
