package ca.jrvs.apps.practice;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public interface LambdaStreamExc {

  /**
   * Create a String stream from array.
   * note: arbitrary number of value will be stored in an array
   *
   * @param strings takes any length of string and stored in an array.
   * @return stream of strings
   */
  Stream<String> createStrStream(String... strings);

  /**
   * Convert all strings to uppercase please use createStrStream.
   *
   * @param strings takes arbitrary length of string
   * @return stream of string
   */
  Stream<String> toUpperCase(String... strings);

  /**
   * filter strings that contains the pattern e.g. filter(stringStream, "a") will return another
   * stream which no element contains a
   *
   * @param stringStream Stream of String
   * @param pattern string pattern to be used for filtering
   * @return Stream of String
   */
  Stream<String> filter(Stream<String> stringStream, String pattern);

  /**
   * Create a intStream from a arr[].
   *
   * @param arr integer array
   * @return intstream
   */
  IntStream createIntStream(int[] arr);

  /**
   * Create a IntStream range from start to end inclusive.
   *
   * @param start start of range
   * @param end end of range
   * @return int stream
   */
  IntStream createIntStream(int start, int end);

  /**
   * Convert a stream to list.
   *
   * @param stream stream
   * @param <E> type of stream
   * @return list of that type
   */
  <E> List<E> toList(Stream<E> stream);

  /**
   * Convert a intStream to list.
   *
   * @param intStream int stream
   * @return list of integers
   */
  List<Integer> toList(IntStream intStream);

  /**
   * Convert a intStream to a doubleStream and compute square root of each element.
   *
   * @param intStream int stream
   * @return Double stream
   */
  DoubleStream squareRootIntStream(IntStream intStream);


  /**
   * filter all even number and return odd numbers from a intStream.
   *
   * @param intStream int stream
   * @return int stream of odd numbers
   */
  IntStream getOdd(IntStream intStream);

  /**
   * Return a lambda function that print a message with a prefix and suffix.
   * This lambda can be useful to format logs
   * You will learn: - functional interface http://bit.ly/2pTXRwM & http://bit.ly/33onFig - lambda
   * syntax
   * e.g. LambdaStreamExc lse = new LambdaStreamImp(); Consumer<String> printer =
   * lse.getLambdaPrinter("start>", "<end"); printer.accept("Message body");
   * sout: start>Message body<end
   *
   * @param prefix prefix str
   * @param suffix suffix str
   * @return
   */
  Consumer<String> getLambdaPrinter(String prefix, String suffix);

  /**
   * Print each message with a given printer Please use `getLambdaPrinter` method.
   * e.g. String[] messages = {"a","b", "c"}; lse.printMessages(messages,
   * lse.getLambdaPrinter("msg:", "!") );
   * sout: msg:a! msg:b! msg:c!
   *
   * @param messages message array
   * @param printer consumer of String type
   */
  void printMessages(String[] messages, Consumer<String> printer);

  /**
   * Print all odd number from a intStream.
   * Please use `createIntStream` and `getLambdaPrinter`methods
   * e.g. lse.printOdd(lse.createIntStream(0, 5), lse.getLambdaPrinter("odd number:", "!"));
   * sout: odd number:1! odd number:3! odd number:5!
   *
   * @param intStream int stream
   * @param printer consumer of string type
   */
  void printOdd(IntStream intStream, Consumer<String> printer);

  /**
   * Square each number from the input.
   * Please write two solutions and compare difference - usingflatMap
   *
   * @param ints stream of list of integers
   * @return stream of integers
   */
  Stream<Integer> flatNestedInt(Stream<List<Integer>> ints);

}