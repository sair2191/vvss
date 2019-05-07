package evaluator.main;

import java.io.*;
import java.util.ArrayList;

import evaluator.exception.*;
import evaluator.model.Statistica;

import evaluator.controller.AppController;

//functionalitati
//F01.	 adaugarea unei noi intrebari pentru un anumit domeniu (enunt intrebare, raspuns 1, raspuns 2, raspuns 3, raspunsul corect, domeniul) in setul de intrebari disponibile;
//F02.	 crearea unui nou test (testul va contine 5 intrebari alese aleator din cele disponibile, din domenii diferite);
//F03.	 afisarea unei statistici cu numarul de intrebari organizate pe domenii.

public class StartApp {

    public static final String file = "intrebari.txt";

    public static void main(String[] args) throws IOException {

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        AppController appController = new AppController();

        boolean activ = true;
        String optiune;

        while (activ) {

            System.out.println();
            System.out.println("1.Adauga intrebare");
            System.out.println("2.Creeaza test");
            System.out.println("3.Statistica");
            System.out.println("4.Exit");
            System.out.println();

            optiune = console.readLine();

            switch (optiune) {
                case "1":
                    try {
                        ArrayList<String> domains = new ArrayList<>(appController.getUniqueDomains());
                        System.out.println("Alegeti domeniul : ");
                        int i = 1;
                        for (String domain : domains) {
                            System.out.println(i++ + "." + domain);
                        }

                        String option = console.readLine();
                        try {
                            int op = Integer.parseInt(option);
                            if (op > domains.size() || op < 0) {
                                throw new NumberFormatException();
                            }
                            System.out.println("Introduceti intrebarea: ");
                            String enunt = console.readLine();
                            String[] raspunsuri = new String[3];
                            System.out.println("Introduceti primul raspuns: ");
                            raspunsuri[0] = "1)".concat(console.readLine());
                            System.out.println("Introduceti al doilea raspuns: ");
                            raspunsuri[1] = "2)".concat(console.readLine());
                            System.out.println("Introduceti al treilea raspuns: ");
                            raspunsuri[2] = "3)".concat(console.readLine());
                            System.out.println("Introcudeti numarul raspunsului corect:");
                            String raspunsCorect = console.readLine();
                            appController.addQuestion(enunt, raspunsuri[0],raspunsuri[1],raspunsuri[2], raspunsCorect,domains.get(Integer.parseInt(raspunsCorect)));
                        } catch (NumberFormatException ex) {
                            System.err.println("Opstiunea nu este valida");
                        } catch (IndexOutOfBoundsException e) {
                            System.err.println("Aceasta optiune nu exista sau nu este valida");
                        } catch (DuplicateIntrebareException | InputValidationFailedException e) {
                            System.err.println(e.getMessage());
                        }
                    }catch (NumberFormatException e){
                        System.err.println(e.getMessage());
                    }
                    break;
                case "2":
                    try {
                        appController.createNewTest();
                    } catch (NotAbleToCreateTestException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case "3":
                    appController.loadIntrebariFromFile(file);
                    Statistica statistica;
                    try {
                        statistica = appController.getStatistica();
                        System.out.println(statistica);
                    } catch (NotAbleToCreateStatisticsException e) {
                        System.err.println(e.getMessage());
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
