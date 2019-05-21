package evaluator.controller;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class F01Test_ecp1 {

    private AppController controller;

    @Before
    public void setUp() throws Exception {
        controller = new AppController();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = InputValidationFailedException.class)
    public void ecp1() throws DuplicateIntrebareException, InputValidationFailedException {
        controller.addQuestion("","","","","","");
    }
}
