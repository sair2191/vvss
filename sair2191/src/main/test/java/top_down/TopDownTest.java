package top_down;

import controller.AppController;
import exception.DuplicateException;
import exception.InputValidationFailedException;
import exception.NotAbleToCreateStatisticsException;
import exception.NotAbleToCreateTestException;
import model.Question;
import model.Quiz;
import org.junit.Test;
import repository.QuizRepository;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TopDownTest {
    @Test
    public void createNewTest2() throws Exception{
        AppController appController = new AppController();
        appController.loadQuestionsFromFile("intrebari.txt");
        Quiz q=appController.createNewTest();
        assertEquals(q.getQuestions().size(),5);
    }

    @Test
    public void testValidQuestionsLoaded()
    {
        final String file = "intrebari.txt";
        QuizRepository quizRepository = new QuizRepository();
        assertTrue(quizRepository.loadQuestionsFromFile(file).size() > 0);
    }

    @Test(expected = InputValidationFailedException.class)
    public void add2() throws Exception {
        AppController appController = new AppController();

        Question q=new Question("ce este?","1)2","2)3","3)3","1","Fizica");
        assertNull(q);

        appController.addNewQuestion(q);
    }

    @Test // P->A
    public void testTopDown1() throws InputValidationFailedException, DuplicateException, IOException {
        AppController appController = new AppController();
        QuizRepository quizRepository = appController.getQuestionRepository();

        Question q=new Question("Ce este?","1)2","2)3","3)3","1","Fizica");
        quizRepository.add(q);

        assertEquals(appController.getQuestionRepository().getQuestions().size(), 1);
    }

    @Test(expected = NotAbleToCreateTestException.class) // P->A->B
    public void testTopDown2() throws InputValidationFailedException, DuplicateException, IOException, NotAbleToCreateTestException {
        AppController appController = new AppController();
        QuizRepository quizRepository = appController.getQuestionRepository();

        Question q=new Question("Ce este?","1)2","2)3","3)3","1","Fizica");
        quizRepository.add(q);

        appController.createNewTest();
    }

    @Test
    public void testTopDown3() throws NotAbleToCreateStatisticsException, NotAbleToCreateTestException {
        AppController appController = new AppController();
        QuizRepository quizRepository = appController.getQuestionRepository();
        final String file = "intrebari.txt";
        quizRepository.setQuestions(quizRepository.loadQuestionsFromFile(file));

        Quiz quiz  = appController.createNewTest();
        assertEquals(quiz.getQuestions().size(),5);

        assertTrue(appController.getStatistic().getDomainQuestions().size() > 0);
    }
}
