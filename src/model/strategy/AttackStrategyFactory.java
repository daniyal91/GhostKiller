package model.strategy;

/*
 * Factory for creating AttackStrategy classes.
 */
public class AttackStrategyFactory {

    private static String[] availableStrategies = {
            "dumb",
            "closest",
            "first",
            "weakest"
    };

    public static String[] getAvailableStrategies() {
        return AttackStrategyFactory.availableStrategies;
    }

    public static AttackStrategy createStrategy(String strategyName) {
        if (strategyName == "dumb") {
            return new DumbStrategy();
        } 
        else if (strategyName == "closest") {
            return new ClosestStrategy();

        }
        else if (strategyName == "first") {
            return new FirstStrategy();
        }
        else if (strategyName == "weakest") {
            return new WeakestStrategy();
        }  
        else {
            throw new IllegalArgumentException("Invalid strategy name.");
        }
    }

}
