package evaluator.model;

import evaluator.util.InputValidation;
import evaluator.exception.InputValidationFailedException;

public class Question {

	private String statement;
	private String answer1;
	private String answer2;



	private String answer3;

	private String goodAnswer;
	private String domain;
	
	public Question() {
	}
	
	public Question(String statement, String answer1, String answer2, String answer3,
					String goodAnswer, String domain) throws InputValidationFailedException {
		
		InputValidation.validateStatement(statement);
		InputValidation.validateAnswer1(answer1);
		InputValidation.validateAnswer2(answer2);
		InputValidation.validateAnswer3(answer3);

		InputValidation.validateGoodAnswer(goodAnswer);
		InputValidation.validateDomain(domain);
		
		this.statement = statement;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.goodAnswer = goodAnswer;
		this.domain = domain;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	public String getGoodAnswer() {
		return goodAnswer;
	}
	public void setGoodAnswer(String goodAnswer) {
		this.goodAnswer = goodAnswer;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Question){
			Question i = (Question) obj;
			if(this.statement.equals(i.getStatement()) &&
			   this.answer1.equals(i.getAnswer1()) &&
			   this.answer2.equals(i.getAnswer2()) && this.answer3.equals(i.getAnswer2()) &&
			   this.goodAnswer.equals(i.getGoodAnswer()) &&
			   this.domain.equals(i.getDomain()))
				return true;
		}
		return false;
	}

}
