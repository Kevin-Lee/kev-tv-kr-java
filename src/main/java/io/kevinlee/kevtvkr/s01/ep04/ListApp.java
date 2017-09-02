package io.kevinlee.kevtvkr.s01.ep04;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 * @author Kevin Lee
 * @since 2015-09-27
 */
public class ListApp {
  public static void main(String[] args) {
    final List<String> list = asList("kevin", "king", "cibernate");

//    list.stream().map(String::toUpperCase)
//    final List<String> strings = list.map(String::toUpperCase);

//    Mappable<String> m = () -> list;
//    final List<String> strings = m.map(String::toUpperCase);
//    System.out.println(strings);

    run((ListLike<String> & Mappable<String>) () -> list);
  }

  private static <T extends List<String> & Mappable<String>> void run(T list) {
    final List<String> strings = list.map(s -> s.toUpperCase());
//                                     .map(s -> "#" + s)
//                                     .get();
    System.out.println(strings);
  }

//  private static <E, T extends List<E> & Mappable<E>> void run(T list) {
//    list.map(elem -> String.valueOf(elem))
//        .map(s -> s.toUpperCase())
//        .map(s -> "#" + s)
//        .forEach(s -> System.out.println(s));
//  }
}

//List<A> => List<B>

interface ListLike<E> extends List<E>, Supplier<List<E>> {
  
  @Override
  default int size() {
    return get().size();
  }

  @Override
  default boolean isEmpty() {
    return get().isEmpty();
  }

  @Override
  default boolean contains(Object o) {
    return get().contains(o);
  }

  @Override
  default Iterator<E> iterator() {
    return get().iterator();
  }

  @Override
  default Object[] toArray() {
    return get().toArray();
  }

  @Override
  default <T> T[] toArray(T[] a) {
    return get().toArray(a);
  }

  @Override
  default boolean add(E e) {
    return get().add(e);
  }

  @Override
  default boolean remove(Object o) {
    return get().remove(o);
  }

  @Override
  default boolean containsAll(Collection<?> c) {
    return get().containsAll(c);
  }

  @Override
  default boolean addAll(Collection<? extends E> c) {
    return get().addAll(c);
  }

  @Override
  default boolean addAll(int index, Collection<? extends E> c) {
    return get().addAll(index, c);
  }

  @Override
  default boolean removeAll(Collection<?> c) {
    return get().removeAll(c);
  }

  @Override
  default boolean retainAll(Collection<?> c) {
    return get().retainAll(c);
  }

  @Override
  default void clear() {
    get().clear();
  }

  @Override
  default E get(int index) {
    return get().get(index);
  }

  @Override
  default E set(int index, E element) {
    return get().set(index, element);
  }

  @Override
  default void add(int index, E element) {
    get().add(index, element);
  }

  @Override
  default E remove(int index) {
    return get().remove(index);
  }

  @Override
  default int indexOf(Object o) {
    return get().indexOf(o);
  }

  @Override
  default int lastIndexOf(Object o) {
    return get().lastIndexOf(o);
  }

  @Override
  default ListIterator<E> listIterator() {
    return get().listIterator();
  }

  @Override
  default ListIterator<E> listIterator(int index) {
    return get().listIterator(index);
  }

  @Override
  default List<E> subList(int fromIndex, int toIndex) {
    return get().subList(fromIndex, toIndex);
  }

  @Override
  default void forEach(Consumer<? super E> action) {
    get().forEach(action);
  }
}

interface Mappable<E> extends Supplier<List<E>> {

  default <R> List<R> map(Function<E, R> mapper) {
    return get().stream().map(mapper).collect(toList());
  }
}
