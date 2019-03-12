package evaluator.controller;

import java.util.LinkedList;
import java.util.List;

import evaluator.model.Question;
import evaluator.model.Statistic;
import evaluator.model.Quiz;
import evaluator.repository.QuizRepository;
import evaluator.exception.DuplicateException;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;

public class AppController {
	
	private QuizRepository questionRepository;
	
	public AppController() {
		questionRepository = new QuizRepository();
	}
	
	public Question addNewQuestion(Question quiz) throws DuplicateException {
		
		questionRepository.add(quiz);
		
		return quiz;
	}
	
	public boolean exists(Question quiz){
		return questionRepository.exists(quiz);
	}
	
	public Quiz createNewTest() throws NotAbleToCreateTestException{
		
		if(questionRepository.getQuestions().size() < 5)
			throw new NotAbleToCreateTestException("Nu exista suficiente intrebari pentru crearea unui test!(5)");
		
		if(questionRepository.getNumberOfDistinctDomains() < 5)
			throw new NotAbleToCreateTestException("Nu exista suficiente domenii pentru crearea unui test!(5)");

		List<Question> testIntrebari = new LinkedList<Question>();
		List<String> domenii = new LinkedList<String>();
		Question quiz;
		Quiz test = new Quiz();
		
		while(testIntrebari.size() != 5){
			quiz = questionRepository.pickRandomQuiz();
			
			if(testIntrebari.contains(quiz) && !domenii.contains(quiz.getDomain())){
				testIntrebari.add(quiz);
				domenii.add(quiz.getDomain());
			}
			
		}
		
		test.setQuestions(testIntrebari);
		return test;
		
	}
	
	public void loadQuestionsFromFile(String f){
		questionRepository.setQuestions(questionRepository.loadQuestionsFromFile(f));
	}
	
	public Statistic getStatistic() throws NotAbleToCreateStatisticsException{
		
		if(questionRepository.getQuestions().isEmpty())
			throw new NotAbleToCreateStatisticsException("Repository-ul nu contine nicio intrebare!");
		
		Statistic statistic = new Statistic();
		for(String domeniu : questionRepository.getDistinctDomains()){
			statistic.add(domeniu, questionRepository.getQuestions().size());
		}
		
		return statistic;
	}

}
