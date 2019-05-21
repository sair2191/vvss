package evaluator.controller;

import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.main.StartApp;
import evaluator.model.Statistica;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class F03Test_valid {

    private AppController controller;

    @Before
    public void setUp() throws Exception {
        controller = new AppController();
        controller.loadIntrebariFromFile(StartApp.file);
    }

    @Test
    public void valid() throws NotAbleToCreateStatisticsException {
        Statistica statistica = controller.getStatistica();
        assert statistica.getIntrebariDomenii().keySet().size() == 5;
    }
}
