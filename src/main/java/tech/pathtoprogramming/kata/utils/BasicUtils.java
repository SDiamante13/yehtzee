package tech.pathtoprogramming.kata.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BasicUtils {

    public static List<BigDecimal> toBigDecimalList(int[] diceRoll) {
        return Arrays.stream(diceRoll)
                .boxed()
                .map(BigDecimal::valueOf)
                .collect(Collectors.toList());
    }
}
