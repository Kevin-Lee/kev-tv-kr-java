package io.kevinlee.kevtvkr.s02.live20170901;

import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Kevin Lee
 * @since 2017-09-01
 */
public class Fp2SolveOopIssue {
  public static void main(String[] args) {
    final Bbb bbb = new Bbb();
    final Aaa aaa = new Aaa("http://kevinlee.io");
    final Aaa aaa2 = new Aaa("http://kevinlee.io");
    final Aaa aaa3 = new Aaa("http://kevinlee.io");
    final Ccc ccc = new Ccc("http://blog.kevinlee.io");
    final Ccc ccc2 = new Ccc("http://blog.kevinlee.io");
    final Ccc ccc3 = new Ccc("http://blog.kevinlee.io");
    final Date date = new Date();

    final Another another = new Another();

    System.out.println("Aaa: " + aaa);

    // System.out.println(
    //     new StringBuilder().append("A: ").append(aaa).toString()
    // );

    bbb.doSomething(aaa.toString());

    // compile-time error
    // bbb.doSomething(aaa);

    Helper.callSomethingWithA(bbb, aaa);
    // BHelper.callSomethingWithA(bbb, ccc);
    // BHelper.callSomethingWithA(bbb, date);

    // bbb.doSomething(aaa.toString());
    // bbb.doSomething(date);

    Helper.callAnotherSomethingWithA(another, aaa);

    Helper.callWith(bbb::doSomething, aaa);
    Helper.callWith(another::doSomethingElse, aaa);


    Helper.callSomethingWithA(bbb, aaa);

    Helper.callWith2(bbb::doSomething, Aaa::toString, aaa);
    Helper.callWith2(bbb::doSomething, Aaa::toString, aaa2);
    Helper.callWith2(bbb::doSomething, Aaa::toString, aaa3);

    Helper.callWith2(bbb::doSomething, Date::toString, date);

    final Consumer<Aaa> doSomethingCaller = Helper.callWith3(bbb::doSomething, Aaa::toString);
    doSomethingCaller.accept(aaa);
    doSomethingCaller.accept(aaa2);
    doSomethingCaller.accept(aaa3);

    final Consumer<Ccc> doSomethingFromBCaller = Helper.callWith3(bbb::doSomething, Ccc::toString);
    doSomethingFromBCaller.accept(ccc);
    doSomethingFromBCaller.accept(ccc2);
    doSomethingFromBCaller.accept(ccc3);

    final Consumer<Ccc> doSomethingFromBCallerWithInfo = Helper.callWith3(bbb::doSomething, Ccc::getInfo);

    doSomethingFromBCallerWithInfo.accept(ccc);
    doSomethingFromBCallerWithInfo.accept(ccc2);
    doSomethingFromBCallerWithInfo.accept(ccc3);

    final Function<Function<Ccc, String>, Consumer<Ccc>> f = Helper.callWith4(bbb::doSomething);

    final Consumer<Ccc> f2 = f.apply(Ccc::toString);
    final Consumer<Ccc> f3 = f.apply(Ccc::getInfo);
    f2.accept(ccc);
    f2.accept(ccc2);
    f2.accept(ccc3);

    f3.accept(ccc);
    f3.accept(ccc2);
    f3.accept(ccc3);


    final Consumer<Aaa> aaaConsumer = Helper.<Aaa>callWith4(bbb::doSomething).apply(Aaa::toString);

    aaaConsumer.accept(aaa);
    aaaConsumer.accept(aaa2);
    aaaConsumer.accept(aaa3);

    // compile-time error
    // aaaConsumer.accept(bbb);

    final Consumer<Aaa> aaaConsumer2 = Helper.<Aaa>callWith4(another::doSomethingElse).apply(Aaa::toString);

    aaaConsumer2.accept(aaa);

  }
}

final class Helper {
  private Helper() throws IllegalAccessException {
    throw new IllegalAccessException(getClass().getName() + " cannot be instantiated.");
  }

  public static void callSomethingWithA(Bbb bbb, Aaa object) {
    bbb.doSomething(object.toString());
  }

  public static void callAnotherSomethingWithA(Another another, Aaa object) {
    another.doSomethingElse(object.toString());
  }
  // public static void callSomethingWithA(Bbb bbb, Object object) {
  //   bbb.doSomething(object.toString());
  // }

  // public static <T> void callSomethingWithA(Bbb bbb, T object) {
  //   if (object instanceof Aaa || object instanceof Bbb)
  //     bbb.doSomething(object.toString());
  // }

  public static void callWith(Consumer<String> f, Object object) {
    f.accept(object.toString());
  }

  public static <T> void callWith2(Consumer<String> f, Function<T, String> f2, T t) {
    f.accept(f2.apply(t));
  }

  public static <T> Consumer<T> callWith3(Consumer<String> f, Function<T, String> f2) {
    return t -> f.accept(f2.apply(t));
  }

  public static <T> Function<Function<T, String>, Consumer<T>> callWith4(Consumer<String> f) {
    return f2 -> t -> f.accept(f2.apply(t));
  }

}

class Aaa {
  private final String url;

  public Aaa(final String url) {
    this.url = url;
  }

  @Override
  public String toString() {
    return url;
  }
}
class Ccc {
  private final String url;

  public Ccc(final String url) {
    this.url = url;
  }

  public String getInfo() {
    return "INFO: " + url;
  }

  @Override
  public String toString() {
    return url;
  }
}

class Bbb {
  public void doSomething(final String value) {
    System.out.println("The URL is " + value);
  }
}

class Another {
  public void doSomethingElse(final String value) {
    System.out.println("Whatever it is. " + value);
  }
}