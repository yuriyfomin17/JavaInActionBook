package chapt7.WordCounter.test;

import chapt7.WordCounter.WordCounter;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestWordCounter {
    private final String TEXT = " Nel mezzo del cammin di nostra vita " +
            "mi ritrovai in una selva oscura" + " ché la dritta via era smarrita ";

    @Test
    public void testWordCounterSequantially() {
        System.out.println("Word count in sequantial manner:" + WordCounter.countWordsSequqntialy(TEXT));
    }

    /**
     * <h3>testWordCounterParallel</h3>
     * is not calculating words correctly due to the fact that string is splitted randomly.
     * Hence, {@link java.util.Spliterator} is needed to make sure that string is not split randomly
     */

    @Test
    public void testWordCounterParallel(){
        Stream<Character> characterStream = IntStream.range(0, TEXT.length()).mapToObj(TEXT::charAt);
        WordCounter wordCounter =  characterStream.parallel().reduce(
                new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine
        );
        System.out.println("Word count in parallel manner:" + wordCounter.getCounter() );
    }
}
