package ca.jrvs.apps.practice;

import static java.lang.Math.sqrt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamExcImp implements LambdaStreamExc {

  @Override
  public Stream<String> createStrStream(String... strings) {
    return Stream.of(strings);
  }

  @Override
  public Stream<String> toUpperCase(String... strings) {
    return createStrStream(strings).map(m -> m.toUpperCase());
  }

  @Override
  public Stream<String> filter(Stream<String> stringStream, String pattern) {
    return stringStream.filter(element -> element.contains(pattern));
  }

  @Override
  public IntStream createIntStream(int[] arr) {
    return Arrays.stream(arr);
  }

  @Override
  public IntStream createIntStream(int start, int end) {
    return IntStream.range(start, end + 1);
  }

  @Override
  public <E> List<E> toList(Stream<E> stream) {
    return stream.collect(Collectors.toList());
  }

  @Override
  public List<Integer> toList(IntStream intStream) {
    return intStream.boxed().collect(Collectors.toList());
  }

  @Override
  public DoubleStream squareRootIntStream(IntStream intStream) {
    return intStream.mapToDouble(num -> (double) sqrt(num));
  }

  @Override
  public IntStream getOdd(IntStream intStream) {
    return intStream.filter(num -> num % 2 != 0);
  }

  @Override
  public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
    return (x) -> System.out.println(prefix + x + suffix);
  }

  @Override
  public void printMessages(String[] messages, Consumer<String> printer) {
    for (String msg : messages) {
      printer.accept(msg);
    }
  }

  @Override
  public void printOdd(IntStream intStream, Consumer<String> printer) {
    intStream.filter(num -> num % 2 != 0).forEach(num -> printer.accept(String.valueOf(num)));
  }

  @Override
  public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
    return ints.flatMap(x -> x.stream()).map(num -> num * num);

    //List<Integer> list = new ArrayList<>();

    //for (List l : ints.collect(Collectors.toList())) {
    //  for (Object num : l) {
    //    list.add((Integer)num*(Integer)num);
    //  }
    //}
    //return list.stream();
  }

  /**
   * The main which used to test each method.
   *
   * @param args takes vales from command line
   */
  public static void main(String[] args) {
    LambdaStreamExc lse = new LambdaStreamExcImp();

    String[] str = {"a", "can", "master", "Rock"};
    System.out.println("CreateStrStream:");
    Stream<String> out = lse.createStrStream(str);
    out.forEach(System.out::println);

    System.out.println("ToUpperClass:");
    out = lse.toUpperCase(str);
    out.forEach(System.out::println);

    System.out.println("Filter:");
    out = lse.filter(lse.createStrStream(str), "a");
    out.forEach(System.out::println);

    int[] arr = {1, 16, 25, 100, 4};
    System.out.println("CreateIntStream:");
    IntStream intStream = lse.createIntStream(arr);
    intStream.forEach(System.out::println);

    System.out.println("toList for string stream: " + lse.toList(lse.createStrStream(str)));

    System.out.println("toList for Intstream: " + lse.toList(lse.createIntStream(arr)));

    System.out.println("CreateIntStream Range:");
    intStream = lse.createIntStream(1, 10);
    intStream.forEach(System.out::println);

    System.out.println("Provide sqrt of Intstream: ");
    DoubleStream doubleStream = lse.squareRootIntStream(lse.createIntStream(arr));
    doubleStream.forEach(System.out::println);

    System.out.println("Get odd: ");
    intStream = lse.getOdd(lse.createIntStream(1, 10));
    intStream.forEach(System.out::println);

    Consumer<String> printer = lse.getLambdaPrinter("start>", "<end");
    printer.accept("Message body");

    String[] messages = {"a", "b", "c"};
    lse.printMessages(messages, lse.getLambdaPrinter("msg:", "!"));

    lse.printOdd(lse.createIntStream(0, 5), lse.getLambdaPrinter("odd number:", "!"));
    List<List<Integer>> list = Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5));
    Stream<List<Integer>> strList = list.stream();
    System.out.println("flatnested with square: ");
    Stream<Integer> strInt = lse.flatNestedInt(strList);
    strInt.forEach(System.out::println);
  }
}