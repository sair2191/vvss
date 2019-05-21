package evaluator.controller;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class F01Test_ecp2 {
    private AppController controller;

    @Before
    public void setUp() throws Exception {
        controller = new AppController();
    }

    @Test
    public void ecp2() throws DuplicateIntrebareException, InputValidationFailedException {
        controller.addQuestion("E=?","1)","2)2","3)3","2","Mate");
        assertEquals(controller.getIntrebariRepository().getIntrebari().size(),1);
    }
}
