package test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({GameGridTest.class, 
               GridLocationTest.class})

public class TestSuite {
}
