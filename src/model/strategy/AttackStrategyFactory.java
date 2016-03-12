package model.strategy;

/*
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

    public static String[] getAvailableStrategies() {
        return AttackStrategyFactory.availableStrategies;
    }

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
