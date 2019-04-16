package evaluator.model;

import java.util.LinkedList;
import java.util.List;

public class Quiz {

	private List<Question> questions;

	public Quiz() {
		questions = new LinkedList<Question>();
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}
