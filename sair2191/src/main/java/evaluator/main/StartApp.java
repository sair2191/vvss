package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.AppController;
import exception.DuplicateException;
import exception.InputValidationFailedException;
import exception.NotAbleToCreateStatisticsException;
import exception.NotAbleToCreateTestException;
import model.Question;
import model.Quiz;
import model.Statistic;

//functionalitati
//F01.	 adaugarea unei noi intrebari pentru un anumit domeniu (enunt intrebare, raspuns 1, raspuns 2, raspuns 3, raspunsul corect, domeniul) in setul de intrebari disponibile;
//F02.	 crearea unui nou test (testul va contine 5 intrebari alese aleator din cele disponibile, din domenii diferite);
//F03.	 afisarea unei statistici cu numarul de intrebari organizate pe domenii.

public class StartApp {

	private static final String file = "intrebari.txt";

	public static void main(String[] args) throws IOException, InputValidationFailedException, DuplicateException, NotAbleToCreateTestException {

		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

		AppController appController = new AppController();

		boolean activ = true;
		String optiune = null;

		while (activ) {

			System.out.println("");
			System.out.println("1.Adauga intrebare");
			System.out.println("2.Creeaza quiz");
			System.out.println("3.Statistic");
			System.out.println("4.Exit");
			System.out.println("");

			optiune = console.readLine();

			switch (optiune) {
				case "1":
					appController.addNewQuestion(new Question("Ce este v2?", "1) sda", "2) dsa", "3) dsa", "2", "Test"));
					break;
				case "2": {
					appController.loadQuestionsFromFile("intrebari.txt");
					Quiz q=appController.createNewTest();
					for (Question intrebare:q.getQuestions()){
						System.out.println(intrebare.getStatement());
					}


					break;
				}
				case "3": {
					appController.loadQuestionsFromFile(file);
				}
				Statistic statistic;
				try {
					statistic = appController.getStatistic();
					System.out.println(statistic);
				} catch (NotAbleToCreateStatisticsException e) {
					// TODO
				}
				break;


				case "4":
					activ = false;
					break;

				default:
					break;
			}
		}

	}
}


