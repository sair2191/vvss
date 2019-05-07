package evaluator.controller;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Statistica;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class TopDown {

    private AppController appController;


    @Before
    public void setUp() {
        appController = new AppController();
    }


    @Test
    public void A() throws DuplicateIntrebareException, InputValidationFailedException {
        appController.addQuestion("E=MC2?", "1)", "2)2", "3)3", "2", "Biologie");
        assertEquals(appController.getIntrebariRepository().getIntrebari().size(), 1);
    }

    @Test
    public void B() throws NotAbleToCreateTestException, DuplicateIntrebareException, InputValidationFailedException {
        appController.loadIntrebariFromFile("intrebari.txt");
        assertEquals(appController.createNewTest().getIntrebari().size(), 5);
    }

    @Test
    public void C() throws NotAbleToCreateStatisticsException {
        appController.loadIntrebariFromFile("intrebari.txt");
        Statistica statistica = appController.getStatistica();
        statistica.getIntrebariDomenii().forEach((k,v)->{
            assertEquals(v.intValue(),1);
        });

    }

    @Test
    public void PA() throws DuplicateIntrebareException, InputValidationFailedException {
        appController.addQuestion("E=MC2?", "1)", "2)2", "3)3", "2", "Fizica");
        appController.addQuestion("E=MC2?", "1)", "2)2", "3)3", "2", "Muzica");
        appController.addQuestion("E=MC2?", "1)", "2)2", "3)3", "2", "Mate");
        appController.addQuestion("E=MC2?", "1)", "2)2", "3)3", "2", "Romana");
        appController.addQuestion("E=MC2?", "1)", "2)2", "3)3", "2", "Biologie");
        assertEquals(appController.getIntrebariRepository().getIntrebari().size(), 5);
    }

    @Test
    public void PAB() throws DuplicateIntrebareException, InputValidationFailedException, NotAbleToCreateTestException {
        appController.addQuestion("E=MC2?", "1)", "2)2", "3)3", "2", "Fizica");
        appController.addQuestion("E=MC2?", "1)", "2)2", "3)3", "2", "Muzica");
        appController.addQuestion("E=MC2?", "1)", "2)2", "3)3", "2", "Mate");
        appController.addQuestion("E=MC2?", "1)", "2)2", "3)3", "2", "Romana");
        appController.addQuestion("E=MC2?", "1)", "2)2", "3)3", "2", "Biologie");

        evaluator.model.Test test = appController.createNewTest();

        assertEquals(appController.getIntrebariRepository().getIntrebari().size(), 5);
        assertEquals(5, test.getIntrebari().size());

    }

    @Test
    public void PABC() throws DuplicateIntrebareException, InputValidationFailedException, NotAbleToCreateTestException, NotAbleToCreateStatisticsException {
        appController.addQuestion("E=MC2?", "1)", "2)2", "3)3", "2", "Fizica");
        appController.addQuestion("E=MC2?", "1)", "2)2", "3)3", "2", "Muzica");
        appController.addQuestion("E=MC2?", "1)", "2)2", "3)3", "2", "Mate");
        appController.addQuestion("E=MC2?", "1)", "2)2", "3)3", "2", "Romana");
        appController.addQuestion("E=MC2?", "1)", "2)2", "3)3", "2", "Biologie");

        evaluator.model.Test test = appController.createNewTest();

        Statistica statistica = appController.getStatistica();

        assertEquals(appController.getIntrebariRepository().getIntrebari().size(), 5);
        assertEquals(5, test.getIntrebari().size());
        statistica.getIntrebariDomenii().forEach((k,v)->{
            assertEquals(v.intValue(),1);
        });
    }
}