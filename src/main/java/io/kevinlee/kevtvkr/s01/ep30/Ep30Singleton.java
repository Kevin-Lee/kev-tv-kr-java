package io.kevinlee.kevtvkr.s01.ep30;

import java.io.*;

/**
 * @author Kevin Lee
 * @since 2016-03-20
 */
public class Ep30Singleton {

  public static void main(String[] args) {

    System.out.println("1=============================");
    System.out.println(OldSingleton.NAME);

    System.out.println("2=============================");

    final OldSingleton oldSingleton1 = OldSingleton.INSTANCE;
    System.out.println("3=============================");
    final OldSingleton oldSingleton2 = OldSingleton.INSTANCE;

    System.out.println("oldSingleton1 == oldSingleton2 => " + (oldSingleton1 == oldSingleton2));

    System.out.println("\n==============================\n" +
        "Lazy Singleton");

    System.out.println("1=============================");
    System.out.println(LazySingleton.NAME);

    System.out.println("2=============================");

    final LazySingleton lazySingleton1 = LazySingleton.getInstance();
    System.out.println("3=============================");
    final LazySingleton lazySingleton2 = LazySingleton.getInstance();

    System.out.println("lazySingleton1 == lazySingleton2 => " + (lazySingleton1 == lazySingleton2));


    final String filename = "/tmp/kevin-tmp/singleton.jsz";


    System.out.println("\n==============================\n" +
        "Serialize OldSingleton");


    try (FileOutputStream fileOut =
             new FileOutputStream(filename);
         ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

      out.writeObject(oldSingleton1);

      System.out.println("Serialized: OldSingleton");
    } catch (IOException i) {
      i.printStackTrace();
    }

    try (FileInputStream fileIn = new FileInputStream(filename);
         ObjectInputStream in = new ObjectInputStream(fileIn)) {
      final OldSingleton deserializedOldSingleton = (OldSingleton) in.readObject();

      System.out.println("oldSingleton1 == deserializedOldSingleton => " + (oldSingleton1 == deserializedOldSingleton));

    } catch (IOException i) {
      i.printStackTrace();
    } catch (ClassNotFoundException c) {
      System.out.println("OldSingleton class not found");
      c.printStackTrace();
    }

    System.out.println("\n==============================\n" +
        "NewSingleton");

    final NewSingleton newSingleton1 = NewSingleton.INSTANCE;
    final NewSingleton newSingleton2 = NewSingleton.INSTANCE;
    System.out.println("newSingleton1 == newSingleton2 => " + (newSingleton1 == newSingleton2));


    try (FileOutputStream fileOut =
             new FileOutputStream(filename);
         ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

      out.writeObject(newSingleton1);

      System.out.println("Serialized: NewSingleton");
    } catch (IOException i) {
      i.printStackTrace();
    }

    try (FileInputStream fileIn = new FileInputStream(filename);
         ObjectInputStream in = new ObjectInputStream(fileIn)) {
      final NewSingleton deserializedNewSingleton = (NewSingleton) in.readObject();

      System.out.println("newSingleton1 == deserializedNewSingleton => " + (newSingleton1 == deserializedNewSingleton));

    } catch (IOException i) {
      i.printStackTrace();
    } catch (ClassNotFoundException c) {
      System.out.println("NewSingleton class not found");
      c.printStackTrace();
    }


  }


}

class OldSingleton implements Serializable {
  public static final String NAME = new String("OldSingleton");
  //  public static final String NAME = "OldSingleton";
  public static final OldSingleton INSTANCE = new OldSingleton();

  private OldSingleton() {
    System.out.println("Hi!");
  }

  public void greet(final String name) {
    System.out.println("Hello " + name + "!");
  }
}

class LazySingleton {
  public static final String NAME = new String("LazySingleton");

  private static final class LazySingletonHolder {
    private static final LazySingleton INSTANCE = new LazySingleton();
  }

  private LazySingleton() {
    System.out.println("Hi");
  }

  public static LazySingleton getInstance() {
    return LazySingletonHolder.INSTANCE;
  }


  public void greet(final String name) {
    System.out.println("Hello " + name + "!");
  }
}

enum NewSingleton {

  INSTANCE;

  public void greet(final String name) {
    System.out.println("Hello " + name + "!");
  }
}
