package evaluator.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import evaluator.exception.InputValidationFailedException;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;
import evaluator.model.Test;
import evaluator.repository.IntrebariRepository;
import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;

public class AppController {

    private IntrebariRepository intrebariRepository;

    public IntrebariRepository getIntrebariRepository() {
        return intrebariRepository;
    }

    public void setIntrebariRepository(IntrebariRepository intrebariRepository) {
        this.intrebariRepository = intrebariRepository;
    }

    public AppController() {
        intrebariRepository = new IntrebariRepository();
    }

    public Intrebare addNewIntrebare(Intrebare intrebare) throws DuplicateIntrebareException {

        intrebariRepository.addIntrebare(intrebare);

        return intrebare;
    }

    public boolean exists(Intrebare intrebare) {
        return intrebariRepository.exists(intrebare);
    }

    public Test createNewTest() throws NotAbleToCreateTestException {
        if (intrebariRepository.getIntrebari().size() < 5)
            throw new NotAbleToCreateTestException("Nu exista suficiente intrebari pentru crearea unui test!(5)");
        if (intrebariRepository.getNumberOfDistinctDomains() < 5)
            throw new NotAbleToCreateTestException("Nu exista suficiente domenii pentru crearea unui test!(5)");
        List<Intrebare> testIntrebari = new LinkedList<Intrebare>();
        List<String> domenii = new LinkedList<String>();
        Intrebare intrebare;
        Test test = new Test();
        while (testIntrebari.size() != 5) {
            intrebare = intrebariRepository.pickRandomIntrebare();
            if (!testIntrebari.contains(intrebare) && !domenii.contains(intrebare.getDomeniu())) {
                testIntrebari.add(intrebare);
                domenii.add(intrebare.getDomeniu());
            }
        }
        test.setIntrebari(testIntrebari);
        return test;
    }

    public void loadIntrebariFromFile(String f) {
        intrebariRepository.setIntrebari(intrebariRepository.loadIntrebariFromFile(f));
    }

    public Statistica getStatistica() throws NotAbleToCreateStatisticsException {

        if (intrebariRepository.getIntrebari().isEmpty())
            throw new NotAbleToCreateStatisticsException("Repository-ul nu contine nicio intrebare!");

        Statistica statistica = new Statistica();
        for (String domeniu : intrebariRepository.getDistinctDomains()) {
            statistica.add(domeniu, intrebariRepository.getNumberOfIntrebariByDomain(domeniu));
        }

        return statistica;
    }

    public Set<String> getUniqueDomains() {
        return intrebariRepository.getDistinctDomains();
    }

    public void addQuestion(String enunt, String s, String s1, String s2, String raspunsCorect, String s3) throws DuplicateIntrebareException, InputValidationFailedException {
        Intrebare intrebare = new Intrebare(enunt, s, s1, s2, raspunsCorect, s3);
        addNewIntrebare(intrebare);
    }
}
