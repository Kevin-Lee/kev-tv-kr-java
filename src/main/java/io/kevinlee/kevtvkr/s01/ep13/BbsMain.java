package io.kevinlee.kevtvkr.s01.ep13;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.function.Consumer;

import static cc.kevinlee.functional.Funs.accepting;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * @author Kevin Lee
 * @since 2015-11-21
 */
public class BbsMain {

  private static Date newDate(int year, int month, int day) {
    return new Date(year - 1900, month - 1, day);
  }

  public static void main(String[] args) {

    final List<Bbs> result =
        Arrays.asList(
            new Bbs(1, false, newDate(2015, 11, 1)),
            new Bbs(2, false, newDate(2015, 11, 20)),
            new Bbs(3, false, newDate(2015, 11, 16)),
            new Bbs(4, false, newDate(2015, 11, 14))
        );

    System.out.println("Before");
    System.out.println("=========================");
    System.out.println(result.stream().map(String::valueOf).collect(joining("\n")));

//    for (final Bbs bbs : result) {
//      if (isWithin1Week(bbs)) {
//        bbs.setNew(true);
//      }
//    }
    final List<Bbs> list =
      result.stream()
          .peek(bbs -> doIfTrue(isWithin1Week(bbs), () -> bbs.setNew(true)))
          .map(bbs ->
              bbs.setFieldIfTrue(isWithin1Week(bbs), b -> b.setNew(true)))
          .map(bbs -> bbs.makeNewIfTrue(isWithin1Week(bbs)))
          .collect(toList());

    System.out.println("\nAfter");
    System.out.println("=========================");
    System.out.println(list.stream().map(String::valueOf).collect(joining("\n")));


  }

  public static void doIfTrue(boolean isTrue, Runnable runnable) {
    if (isTrue) {
      runnable.run();
    }
  }



  public static boolean isWithin1Week(Bbs bbs) {

    final Date date = bbs.getWriteDate();
    Calendar calendar = Calendar.getInstance();

    calendar.setTime(date);

    calendar.add(Calendar.DATE, +7);


    boolean flag = false;


    if (calendar.getTime().compareTo(new Date()) == 1) {

      flag = true;

    } else {

      flag = false;

    }

    return flag;


  }

}

@AllArgsConstructor
@Data
class Bbs {

  private Integer seq;

  private boolean isNew;

  private Date writeDate;

  public Bbs setFieldIfTrue(boolean isTrue, Consumer<Bbs> setter) {
    if (isTrue) {
      setter.accept(this);
    }
    return this;
  }

  public Bbs makeNewIfTrue(boolean isTrue) {
    if (isTrue) {
      isNew = true;
    }
    return this;
  }


}


