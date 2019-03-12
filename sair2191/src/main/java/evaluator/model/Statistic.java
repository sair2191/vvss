package evaluator.model;

import java.util.HashMap;
import java.util.Map;

public class Statistic {

	private Map<String, Integer> domainQuestions;
	
	public Statistic() {
		domainQuestions = new HashMap<String, Integer>();
	}
	
	public void add(String key, Integer value){
		domainQuestions.put(key, value);
	}

	public Map<String, Integer> getDomainQuestions() {
		return domainQuestions;
	}

	public void setDomainQuestions(Map<String, Integer> domainQuestions) {
		this.domainQuestions = domainQuestions;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		for(String domeniu : domainQuestions.keySet()){
			sb.append(domeniu + ": " + domainQuestions.get(domeniu) + "\n");
		}
		
		return sb.toString();
	}

}
