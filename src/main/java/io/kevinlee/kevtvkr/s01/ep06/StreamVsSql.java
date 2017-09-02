package io.kevinlee.kevtvkr.s01.ep06;

import lombok.Data;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BinaryOperator;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;

/**
 * @author Kevin Lee
 * @since 2015-10-10
 */
public class StreamVsSql {
  public static BigDecimal $(String value) {
    return new BigDecimal(value);
  }

  public static void main(String[] args) {

    final List<Coin> coinList = Arrays.asList(
        new Coin("Won", $("20000")),
        new Coin("Dollar", $("639")),
        new Coin("Euro", $("1111")),
        new Coin("Yen", $("200")),
        new Coin("Yen", $("3200")),
        new Coin("Won", $("5000")),
        new Coin("Dollar", $("75")),
        new Coin("Dollar", $("100")),
        new Coin("Euro", $("800")),
        new Coin("Won", $("340")),
        new Coin("Euro", $("999"))
    );

    final Map<String, List<Coin>> currencyToCoin = new HashMap<>();
    for (final Coin coin : coinList) {
      final List<Coin> coins;
      if (currencyToCoin.containsKey(coin.getCurrencyName())) {
        coins = currencyToCoin.get(coin.getCurrencyName());
      }
      else {
        coins = new ArrayList<>();
        currencyToCoin.put(coin.getCurrencyName(), coins);
      }

      coins.add(coin);
    }

//    System.out.println(
//        mapToString(currencyToCoin)
//    );

    final StringBuilder stringBuilder = new StringBuilder();
    for (Map.Entry<String, List<Coin>> coins : currencyToCoin.entrySet()) {
      BigDecimal sum = BigDecimal.ZERO;
      for (Coin coin : coins.getValue()) {
        sum = sum.add(coin.getAmount());
      }
      stringBuilder.append("(" + coins.getKey() + "=" + sum + ")");
    }
    System.out.println(stringBuilder.toString());

    System.out.println("==================================");

    System.out.println(
        mapToStringWithBigDecimalSum(
          coinList.stream()
                  .collect(groupingBy(Coin::getCurrencyName)),
            (coin1, coin2) ->
                new Coin(coin1.getCurrencyName(),
                         coin1.getAmount().add(coin2.getAmount()))
        )
    );


    System.out.println(
      coinList.stream()
          .collect(groupingBy(Coin::getCurrencyName))
          .entrySet()
          .stream()
          .map(entry -> entry.getKey() + "=" + entry.getValue().stream()
              .map(Coin::getAmount)
              .reduce(BigDecimal.ZERO, (bd1, bd2) -> bd1.add(bd2)))
          .collect(joining("\n"))
    );
//              .map(coin -> coin.getAmount())
//              .reduce(BigDecimal.ZERO, BigDecimal::add))

  }

  private static <K, V> String mapToString(Map<K, V> map) {
    return map.entrySet().stream()
        .map(entry -> "(" + entry.getKey() + "=" + entry.getValue() + ")")
        .collect(joining("\n"));
  }
  private static <K, E, V extends List<E>> String mapToStringWithBigDecimalSum(Map<K, V> map, BinaryOperator<E> adder) {
    return map.entrySet().stream()
        .map(entry -> "(" + entry.getKey() + "=" + entry.getValue()
            .stream()
            .reduce(adder)
            + ")")
        .collect(joining("\n"));
  }
}

@Data
class Coin {
  private final String currencyName;
  private final BigDecimal amount;
}