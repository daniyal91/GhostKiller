package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({
    GameGridTestCase.class,
    GridLocationTestCase.class,
    GameTestCase.class,
    StrategyTestCase.class,
    CritterTestCase.class,
    PathTestCase.class,
    GameScoreTestCase.class
    })

public class TestSuite {
}