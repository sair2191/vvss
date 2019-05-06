package repository;

import exception.DuplicateException;
import model.Question;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class QuizRepository implements repository.Repository<Question> {

	private List<Question> questions;

	public QuizRepository() {
		setQuestions(new LinkedList<Question>());
	}

	@Override
	public void add(Question i) throws DuplicateException, IOException {
		if(exists(i))
			throw new DuplicateException("Intrebarea deja exista!");
		questions.add(i);
		String filename= "intrebari.txt";
		FileWriter fw = new FileWriter(filename,true); //the true will append the new data
		//fw.write("\n");
		fw.write(i.getStatement()+"\n");
		fw.write(i.getAnswer1()+"\n");//appends the string to the file
		fw.write(i.getAnswer2()+"\n");//appends the string to the file
		fw.write(i.getAnswer3()+"\n");//appends the string to the file
		fw.write(i.getGoodAnswer()+"\n");//appends the string to the file
		fw.write(i.getDomain()+"\n");//appends the string to the file
		fw.write("##"+"\n");//appends the string to the file


		fw.close();
	}

	@Override
	public boolean exists(Question i){
		for(Question quiz : questions)
			if(quiz.equals(i))
				return true;
		return false;
	}

	public Question pickRandomQuiz(){
		Random random = new Random();
		return questions.get(random.nextInt(questions.size()));
	}

	public int getNumberOfDistinctDomains(){
		return getDistinctDomains().size();

	}

	public Set<String> getDistinctDomains(){
		Set<String> domains = new TreeSet<String>();
		for(Question intrebre : questions)
			domains.add(intrebre.getDomain());
		return domains;
	}

	public List<Question> getIntrebariByDomain(String domain){
		List<Question> intrebariByDomain = new LinkedList<Question>();
		for(Question quiz : questions){
			if(quiz.getDomain().equals(domain)){
				intrebariByDomain.add(quiz);
			}
		}

		return intrebariByDomain;
	}

	public int getNumberOfQuestionByDomain(String domain){
		return getIntrebariByDomain(domain).size();
	}

	public List<Question> loadQuestionsFromFile(String f){

		List<Question> intrebari = new LinkedList<Question>();
		BufferedReader br = null;
		String line = null;
		List<String> intrebareAux;
		Question quiz;

		try{
			br = new BufferedReader(new FileReader(f));
			line = br.readLine();
			while(line != null){
				intrebareAux = new LinkedList<String>();
				while(!line.equals("##")){
					intrebareAux.add(line);
					line = br.readLine();
				}
				quiz = new Question();
				quiz.setStatement(intrebareAux.get(0));
				quiz.setAnswer1(intrebareAux.get(1));
				quiz.setAnswer2(intrebareAux.get(2));
				quiz.setAnswer3(intrebareAux.get(3));
				quiz.setGoodAnswer(intrebareAux.get(4));
				quiz.setDomain(intrebareAux.get(5));
				intrebari.add(quiz);
				line = br.readLine();
			}

		}
		catch (IOException e) {
			// TODO: handle exception
		}
		finally{
			try {
				br.close();
			} catch (IOException | NullPointerException e) {
				// TODO: handle exception
			}
		}

		return intrebari;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> intrebari) {
		this.questions = intrebari;
	}

}
