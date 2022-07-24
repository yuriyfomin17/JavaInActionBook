package chapt2;

import java.util.function.Predicate;

public interface ApplePredicate {

    /**
     * <h3>appleHeavyPredicate</h3> receives {@link Apple} and {@link Predicate}
     * <br/>and determines whether it is heavy or not
     */

    boolean appleHeavyPredicate(Apple apple, Predicate<Apple> applePredicate);
}
