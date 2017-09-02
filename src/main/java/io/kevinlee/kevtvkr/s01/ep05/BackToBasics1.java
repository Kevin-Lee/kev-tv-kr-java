package io.kevinlee.kevtvkr.s01.ep05;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;

import static cc.kevinlee.functional.Funs.not;
import static cc.kevinlee.functional.Funs.satisfying;
import static java.util.stream.Collectors.joining;

/**
 * @author Kevin Lee
 * @since 2015-10-03
 */
public class BackToBasics1 {
  public static void main(String[] args) {
    // object identity and equality
    final String string1 = new String("a");
    final String string2 = new String("a");
    final String string3 = string1;

    if (string1 == string2) {
      // identity
      System.out.println("string1 == string2");
    }
    if (string1 == string3) {
      // identity
      System.out.println("string1 == string3");
    }
    if (string1.equals(string2)) {
      // equality
      System.out.println("string1.equals(string2)");
    }

//    !string1.equals("")
//    !"".equals(string1)
//    string1.length() != 0

//    !string1.isEmpty()

    if (string1 != null && !string1.isEmpty()) {
      if (string1.equals(string2)) {
        System.out.println("string1 " + string1 + " is equal to " + "string2 " + string2 + ".");
      }
    }

    Optional.ofNullable(string1)
        .filter(not(String::isEmpty))
        .map(s -> "string1 " + s + " is equal to " + "string2 " + string2 + ".")
        .ifPresent(System.out::println);


    final Product product1 = new Product(1L, "A", new BigDecimal("100"));
    final Product product2 = new Product(2L, "B", new BigDecimal("20.50"));
    final Product product3 = new Product(3L, "C", new BigDecimal("17.75"));
    final Product productA = new Product(1L, "A", new BigDecimal("100"));

    final Set<Product> products = new HashSet<>();
    products.add(product1);
    products.add(product2);
    products.add(product3);
    products.add(productA);

    System.out.println(
      products.stream()
              .map(String::valueOf)
  //            .map(product -> String.valueOf(product))
              .collect(joining("\n", "[\n", "\n]"))
    );


    final Map<String, Integer> map = new HashMap<>();
    map.put("A", 1);
    map.put("B", 2);
    map.put("C", 3);
    map.put("A", 10);
    System.out.println(map);

    final Map<Product, Integer> productToNumber = new HashMap<>();
    productToNumber.put(product1, 1);
    productToNumber.put(product2, 2);
    productToNumber.put(product3, 3);
    product1.setPrice(new BigDecimal("999"));
    productToNumber.put(product1, 10);

    System.out.println(
      productToNumber.entrySet().stream()
          .map(entry -> entry.getKey() + " ======> " + entry.getValue())
          .collect(joining("\n", "{\n", "\n}"))
    );


    final Product p1 = new Product("A", new BigDecimal("100"));
    System.out.println(p1);

    Set<Product> productSet = new HashSet<>();
    productSet.add(p1);
    System.out.println(productSet);

    // ORM이 id를 설정 했다고 가정해 주세요.
    p1.setId(1L);
    productSet.add(p1);

    System.out.println(productSet);

    String productCode = UUID.randomUUID().toString();
    System.out.println(productCode);

    Map<Point, Integer> m = new HashMap<>();
    Point p = new Point(1, 1);

    System.out.println("p.hashCode() == new Point(1, 1).hashCode(): "
        + (p.hashCode() == new Point(1, 1).hashCode()));
    System.out.println("(p.equals(new Point(1, 1))): "
        + (p.equals(new Point(1, 1))));
    m.put(p, 10);
    System.out.println(m.get(p));
    p.setX(10);
    System.out.println(m.get(p));
    System.out.println(m.get(new Point(1, 1)));
    p.setX(1);
    System.out.println(m.get(p));
    System.out.println(m.get(new Point(1, 1)));



  }


}

class Point {
  private int x;
  private int y;

  public Point(int x, int y) {
    setX(x);
    setY(y);
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Point) {
      Point otherPoint = (Point) other;
      return otherPoint.getX() == getX() &&
          otherPoint.getY() == getY();
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return "(x=" + x + ", y=" + y + ")";
  }

  @Override
  public int hashCode() {
    return Objects.hash(getX(), getY());
  }
}


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of="name")
class Product {
  private Long id;
  private String name;
  private BigDecimal price;

  public Product(String name, BigDecimal price) {
    this(null, name, price);
  }

}

