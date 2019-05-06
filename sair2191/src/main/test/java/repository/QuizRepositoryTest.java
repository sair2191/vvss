package repository;

import exception.InputValidationFailedException;
import model.Question;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuizRepositoryTest {
    @Test
    public void add() throws Exception {
        Question q=new Question("Ce este?","1)2","2)3","3)3","1","Fizica");
        assertNotNull(q);
    }

    @Test(expected = InputValidationFailedException.class)
    public void add2() throws Exception {
        Question q=new Question("ce este?","1)2","2)3","3)3","1","Fizica");
        assertNull(q);

    }
    @Test(expected = InputValidationFailedException.class)
    public void add3() throws Exception {
        Question q=new Question("Ce este?","1)2","2)3","3)3","5","Fizica");
        assertNull(q);

    }
    @Test(expected = InputValidationFailedException.class)
    public void add4() throws Exception {
        Question q=new Question("","1)2","2)3","3)3","5","Fizica");
        assertNull(q);
    }
    @Test

    public void add5() throws Exception {
        Question q=new Question("Ce esteddddddddddddddddddddddddddddd?","1)2","2)3","3)3","1","Fizica");
        assertNotNull(q);

    }

    @Test
    public void add6() throws Exception {
        Question q=new Question("Ce esteddddddddddddddddddddddddddddds?","1)2","2)3","3)3","1","Fizica");
        assertNotNull(q);
    }
    @Test(expected = InputValidationFailedException.class)
    public void add7() throws Exception {
        Question q=new Question("Ce esteddddddddddddddddddddddddddddds?","1)2","2)3","3)3","","Fizica");
        assertNull(q);

    }

    @Test
    public void testValidQuestionsLoaded()
    {
        final String file = "intrebari.txt";
        QuizRepository quizRepository = new QuizRepository();
        assertTrue(quizRepository.loadQuestionsFromFile(file).size() > 0);
    }

    @Test
    public void testUnvalidQuestionsLoaded()
    {
        final String file = "intrebari22222.txt";
        QuizRepository quizRepository = new QuizRepository();
        assertTrue( quizRepository.loadQuestionsFromFile(file).size() == 0);
    }
}