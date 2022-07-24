package chapt2;

import java.util.function.Predicate;

public interface ApplePredicate {

    /**
     * <h3>isAppleHeavy</h3> determines whether apple is heavy
     */

    boolean isAppleHeavy();

    /**
     * <h3>appleGreenPredicate</h3>  whether apple is green or not
     */
    boolean isAppleGreen();

    /**
     * <h3>appleRedPredicate</h3>  whether apple is red or not
     */
    boolean isAppleRed();

    /**
     * <h3>isAppleRedAndHeavy</h3>
     * determines whether it is heavy and red
     */
    boolean isAppleRedAndHeavy();
}
