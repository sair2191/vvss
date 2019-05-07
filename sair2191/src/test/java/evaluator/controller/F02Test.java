package evaluator.controller;

import evaluator.exception.NotAbleToCreateTestException;
import evaluator.main.StartApp;
import evaluator.model.Intrebare;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class F02Test {

    private AppController controller;

    @Before
    public void setUp() throws Exception {
        controller = new AppController();
        controller.loadIntrebariFromFile(StartApp.file);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void valid() throws NotAbleToCreateTestException {
        evaluator.model.Test t =  controller.createNewTest();
        assert t != null;
    }

    @Test(expected = NotAbleToCreateTestException.class)
    public void nonValid() throws NotAbleToCreateTestException {
        controller.getIntrebariRepository().getIntrebari().clear();
        controller.createNewTest();
    }

    @Test(expected = NotAbleToCreateTestException.class)
    public void nonValid2() throws NotAbleToCreateTestException {
        Intrebare intrebare = controller.getIntrebariRepository().getIntrebari().get(0);
        controller.getIntrebariRepository().getIntrebari().set(1,intrebare);
        controller.getIntrebariRepository().getIntrebari().set(2,intrebare);
        controller.getIntrebariRepository().getIntrebari().set(3,intrebare);
        controller.createNewTest();
    }
}