package model;

/*
 * Factory for creating AttackStrategy classes.
 */
public class AttackStrategyFactory {

    private static String[] availableStrategies = {
        "dumb",
        "closest"
    };

    public static String[] getAvailableStrategies() {
        return AttackStrategyFactory.availableStrategies;
    }

    public static AttackStrategy createStrategy(String strategyName) {
        if (strategyName == "dumb") {
            return new DumbStrategy();
        } else if (strategyName == "closest") {
            return new ClosestStrategy();
        } else {
            throw new IllegalArgumentException("Invalid strategy name.");
        }
    }

}
