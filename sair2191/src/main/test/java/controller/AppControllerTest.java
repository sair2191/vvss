package controller;

import exception.NotAbleToCreateTestException;
import model.Quiz;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppControllerTest {
    //nu am incarcat intrebarile din fisier
    @Test(expected = NotAbleToCreateTestException.class)
    public void createNewTest() throws Exception {
        AppController appController = new AppController();
        appController.createNewTest();
    }
    //valid
    @Test
    public void createNewTest2() throws Exception{
        AppController appController = new AppController();
        appController.loadQuestionsFromFile("intrebari.txt");
        Quiz q=appController.createNewTest();
        assertEquals(q.getQuestions().size(),5);
    }
    //nu exista 5 domenii
    @Test(expected = NotAbleToCreateTestException.class)
    public void createNewTest3() throws Exception{
        AppController appController = new AppController();
        appController.loadQuestionsFromFile("intrebari2.txt");
        appController.createNewTest();
    }

}