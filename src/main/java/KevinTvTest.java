import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kevin Lee
 * @since 2017-02-25
 */
public class KevinTvTest {

  // @Data
  // @AllArgsConstructor
  // private static class Person {
  //   private Long id;
  //   private String name;
  // }
  //
  // private static void printName(final Person person) {
  //   // person = new Person(1L, "John");
  //   person.setName("John");
  //   System.out.println(person.getName());
  // }

  // private static boolean process(final int i) {
  //   // Do something to change the state of another object.
  //   System.out.println("processing " + i);
  //   return i % 3 == 0 || i % 5 == 0;
  // }

  private static List<String> addSuffix(List<String> xs, String suffix) {
    final List<String> result = new ArrayList<>();
    for (final String x : xs) {
      result.add(x + suffix);
    }
    return result;
  }

  public static void main(final String[] args) {

    final List<String> list = Arrays.asList("aaa", "bbb", "ccc");

    System.out.println(addSuffix(list, "-123"));


  //   // String x = "aaa";
  //   // String y = "aaa";
  //   // String z = new String("aaa");
  //   //
  //   // System.out.println("x == y: " + (x == y));
  //   // System.out.println("x.equals(y): " + x.equals(y));
  //   // System.out.println("x == z: " + (x == z));
  //   // System.out.println("x.equals(z): " + x.equals(z));
  //
  //   final String aaa = "aaa";
  //
  //   // String aaaa = new String("aaaa").intern();
  //   String aaaa = "aaaa";
  //
  //   // System.out.println(
  //   //     "(aaa + \"a\") == \"aaaa\": " + ((aaa + "aa") == aaaa)
  //   // );
  //   System.out.println(aaa + "a");
  //
  //   System.out.println(
  //       new StringBuilder().append(aaa).append("a").toString()
  //   );
  //
  //   String text = "";
  //   final List<String> list = Arrays.asList("aaa", "bbb", "ccc");
  //   for (final String each : list) {
  //     text += each;
  //   }
  //   System.out.println(text);
  //
  //   final StringBuilder builder = new StringBuilder();
  //   for (final String each : list) {
  //     builder.append(each);
  //   }
  //   System.out.println(builder);
  //
  //   String text2 = "aaa" + "bbb" + "ccc";
  //   String text2 = "aaabbbccc";
  //   String text2 = x + "bbb" + "ccc";
  //   // String text2 = list.get(0) + list.get(1) + list.get(2);
  //
  //   list.stream().collect(Collectors.joining(""));
  //
  //
  //   final StringBuilder builder2 = new StringBuilder();
  //   builder2.append("aaa")
  //           .append("bbb")
  //           .append("ccc");
  //
  //   String text3 = "aaa";
  //   text3.concat("bbb");
  //   text3.concat("ccc");
  //
  //   final List<Integer> ns = Arrays.asList(1, 2, 3, 4, 5);
  //   boolean isSuccess = false;
  //
  //   for (final int i : ns) {
  //     isSuccess |= process(i);
  //     isSuccess = isSuccess | process(i);
  //   }
  //   try {
  //     process(1);
  //   } catch (final RuntimeException e) {
  //     // loggging
  //     e = new IllegalArgumentException("");
  //     throw e;
  //   }
  //   System.out.println("isSuccess: " + isSuccess);
  //
  //   isSuccess = ns.stream()
  //                 .reduce(
  //                     false,
  //                     (r, x) -> process(x), (r, x) -> r || x
  //                 );
  //   System.out.println("isSuccess: " + isSuccess);
  //
  }
}
