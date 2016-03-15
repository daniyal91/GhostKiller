package model.strategy;

/**
 * Factory for creating AttackStrategy classes.
 */
public class AttackStrategyFactory {

    private static String[] availableStrategies = {
            "random",
            "nearest",
            "first",
            "weakest",
            "strongest"
    };

    /**
     * Returns all the strategies available for creation by the current
     * Factory class.
     *
     * @return A list of strategy names.
     */
    public static String[] getAvailableStrategies() {
        return AttackStrategyFactory.availableStrategies;
    }

    /**
     * Creates the AttackStrategy class associated
     * with the strategy name
     *
     * @param strategyName Name of the strategy class to create.
     *
     * @return A new AttackStrategy instance.
     */
    public static AttackStrategy createStrategy(String strategyName) {
        if (strategyName == "random") {
            return new RandomStrategy();
        } else if (strategyName == "nearest") {
            return new NearestStrategy();
        } else if (strategyName == "first") {
            return new FirstStrategy();
        } else if (strategyName == "weakest") {
            return new WeakestStrategy();
        } else if (strategyName == "strongest") {
            return new StrongestStrategy();
        } else {
            throw new IllegalArgumentException("Invalid strategy name.");
        }
    }

}
