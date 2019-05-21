package evaluator.controller;

import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.main.StartApp;
import evaluator.model.Statistica;
import org.junit.Before;
import org.junit.Test;

public class F03Test_non_valid {

    private AppController controller;

    @Before
    public void setUp() throws Exception {
        controller = new AppController();
        controller.loadIntrebariFromFile(StartApp.file);
    }
    
    @Test(expected = NotAbleToCreateStatisticsException.class)
    public void nonValid() throws NotAbleToCreateStatisticsException {
        controller.getIntrebariRepository().getIntrebari().clear();
        Statistica statistica = controller.getStatistica();
    }
}
