package evaluator.controller;

import evaluator.exception.NotAbleToCreateTestException;
import evaluator.main.StartApp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class F02Test_non_valid {
    private AppController controller;

    @Before
    public void setUp() throws Exception {
        controller = new AppController();
        controller.loadIntrebariFromFile(StartApp.file);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = NotAbleToCreateTestException.class)
    public void nonValid() throws NotAbleToCreateTestException {
        controller.getIntrebariRepository().getIntrebari().clear();
        controller.createNewTest();
    }
}
