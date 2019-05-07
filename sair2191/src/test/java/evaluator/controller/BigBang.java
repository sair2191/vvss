package evaluator.controller;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Statistica;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class BigBang {

    private AppController appController;


    @Before
    public void setUp() {
        appController = new AppController();
    }


    @Test
    public void A() throws DuplicateIntrebareException, InputValidationFailedException {
        appController.addQuestion("Cat face 1 + 1?","1)1","2)2","3)3","2","Mate");
        assertEquals(appController.getIntrebariRepository().getIntrebari().size(), 1);
    }

    @Test
    public void B() throws NotAbleToCreateTestException {
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
    public void integration() throws DuplicateIntrebareException, InputValidationFailedException, NotAbleToCreateTestException, NotAbleToCreateStatisticsException {
        appController.addQuestion("Cat face 1 + 1?","1)1","2)2","3)3","2","Mate");
        appController.addQuestion("Cat face 1 + 1?","1)1","2)2","3)3","2","Fizica");
        appController.addQuestion("Cat face 1 + 1?","1)1","2)2","3)3","2","Astronomie");
        appController.addQuestion("Cat face 1 + 1?","1)1","2)2","3)3","2","Astrologie");
        appController.addQuestion("Cat face 1 + 1?","1)1","2)2","3)3","2","Filozofie");

        evaluator.model.Test test = appController.createNewTest();

        Statistica statistica = appController.getStatistica();

        assertEquals(appController.getIntrebariRepository().getIntrebari().size(), 5);
        assertEquals(5, test.getIntrebari().size());
        statistica.getIntrebariDomenii().forEach((k,v)->{
            assertEquals(v.intValue(),1);
        });
    }
}