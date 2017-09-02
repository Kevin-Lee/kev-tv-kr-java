package io.kevinlee.kevtvkr.s01.ep12;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

/**
 * @author Kevin Lee
 * @since 2015-11-14
 */
public class JavaMain {

  public static final int NUMBER1 = 111;

  private static class Holder {
    public static final JavaMain INSTANCE = new JavaMain();
  }

  enum Something {
    INSTANCE;
    public final Boolean NUMBER2 = Boolean.valueOf(true);

    public String getName() {
      return "Something";
    }

    public void printNumbers() {
      System.out.println("Number1: " + NUMBER1 +
          "\nNumber2: " + NUMBER2);
    }
  }

  public JavaMain() {
  }

  public static JavaMain getInstance() {
    return Holder.INSTANCE;
  }


  public static void main(String[] args) {

    Supplier<Function<String, String>> supplier = UnaryOperator::identity;

    Stream.iterate("*", UnaryOperator.identity()).limit(5).collect(joining(""));
    System.out.println(Something.INSTANCE.NUMBER2);
    Something.INSTANCE.printNumbers();
  }
}
