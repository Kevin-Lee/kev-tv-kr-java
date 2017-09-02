package io.kevinlee.kevtvkr.s01.ep07;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Kevin Lee
 * @since 2015-10-17
 */
public class BackToBasics2 {
  public static void main(String[] args) {
    Something something = new Something(123);
    System.out.println(something.equals(new Something(123)));
    System.out.println("something.hashCode(): " + something.hashCode() + " / new Something(123).hashCode(): " + new Something(123).hashCode());

    final Map<Something, String> m = new HashMap<>();
    m.put(something, "a");
    m.put(new Something(2), "b");

    System.out.println("something: " + m.get(something));
    System.out.println("something: " + m.get(new Something(123)));
    something.setValue(1);

    System.out.println("something: " + m.get(something));
    System.out.println("Something(123): " + m.get(new Something(123)));

  }
}


class Something<E> {
  private E value;

  public Something(E value) {
    this.value = value;
  }

  public E getValue() {
    return value;
  }

  public void setValue(E value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Something)) return false;
    Something<?> something = (Something<?>) o;
    return Objects.equals(value, something.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
