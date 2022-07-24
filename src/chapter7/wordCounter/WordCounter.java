package chapter7.wordCounter;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WordCounter {
    private final int counter;
    private final boolean lastSpace;
    final static String SENTENCE =
            " Nel mezzo del cammin di nostra vita " +
                    "mi ritrovai in una selva oscura" + " ché la dritta via era smarrita ";
    public WordCounter(int counter, boolean lastSpace){
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character character){
        if (Character.isWhitespace(character)){
            return lastSpace ? this: new WordCounter(counter, true );
        } else {
            return lastSpace ? new WordCounter(counter + 1, false) : this;

        }
    }

    public WordCounter combine(WordCounter wordCounter){
        return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
    }

    public int getCounter() {
        return counter;
    }

    private static int countWords(Stream<Character> stream){
        WordCounter wordCounter = stream.reduce(
                new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine
                );
        return wordCounter.getCounter();
    }

    public static void main(String[] args) {

//        Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
//        System.out.println("Found " + countWords(stream) + " words");
//        System.out.println("Found " + countWords(stream.parallel()) + " words");

        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream1 = StreamSupport.stream(spliterator, true);
        System.out.println("Found " + countWords(stream1.parallel()) + " words");
    }
}
