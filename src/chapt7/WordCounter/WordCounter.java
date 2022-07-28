package chapt7.WordCounter;

public class WordCounter {
    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) return lastSpace ? this : new WordCounter(counter, true);
        else {
            return lastSpace ? new WordCounter(counter + 1, false) : this;
        }
    }

    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
    }

    public static int countWordsSequqntialy(String text) {
        int count = 0;
        boolean lastSpace = false;
        for (Character character : text.toCharArray()) {
            if (Character.isWhitespace(character)) {
                lastSpace = true;
            } else {
                if (lastSpace) count += 1;
                lastSpace = false;
            }
        }
        return count;
    }

    public int getCounter() {
        return counter;
    }
}
