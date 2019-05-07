package evaluator.controller;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.model.Intrebare;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class F01Test {

    private AppController controller;

    @Before
    public void setUp() throws Exception {
        controller = new AppController();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = InputValidationFailedException.class)
    public void bva1() throws DuplicateIntrebareException, InputValidationFailedException {
        controller.addQuestion("Cat face 1 + 1?","111","2)2","3)3","2","Mate");
    }

    @Test(expected = InputValidationFailedException.class)
    public void bva2() throws DuplicateIntrebareException, InputValidationFailedException {
        controller.addQuestion("AAAAAAAAAAAAAAAAAA" +
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" +
                "AAAAAAAAAAAAAAAAAAAAAAA" +
                "AAAAAAAAAAAAAA","111","2)2","3)3","2","Mate");
    }

    @Test
    public void bva3() throws DuplicateIntrebareException, InputValidationFailedException {
        controller.addQuestion("E=?","1)","2)2","3)3","2","Mate");
        assertEquals(controller.getIntrebariRepository().getIntrebari().size(),1);
    }
    @Test
    public void bva4() throws DuplicateIntrebareException, InputValidationFailedException {
        controller.addQuestion("Pentru o intrebare de 100 de caractere cat" +
                "crezi ca a trebui sa scrii ca sa  obtii 100 caractere ?","1)10 ani","2)2","3)3","2","Mate");
        assertEquals(controller.getIntrebariRepository().getIntrebari().size(),1);
    }

    @Test(expected = InputValidationFailedException.class)
    public void ecp1() throws DuplicateIntrebareException, InputValidationFailedException {
        controller.addQuestion("","","","","","");
    }
    @Test
    public void ecp2() throws DuplicateIntrebareException, InputValidationFailedException {
        controller.addQuestion("E=?","1)","2)2","3)3","2","Mate");
        assertEquals(controller.getIntrebariRepository().getIntrebari().size(),1);
    }
}