package site.nomoreparties.stellarburgers;

import org.junit.Rule;
import org.junit.Test;
import site.nomoreparties.stellarburgers.pages.Constructor;

public class ConstructorTests {

    @Rule
    public DriverRule rule = new DriverRule();

    @Test
    public void transitionToFillingsTest() {
        Constructor constructor = new Constructor(rule.driver);
        constructor.open();
        constructor.isFillingsCurrent();
    }

    @Test
    public void transitionToSauceTest() {
        Constructor constructor = new Constructor(rule.driver);
        constructor.open();
        constructor.isSauceCurrent();
    }

    @Test
    public void transitionToBunsTest() {
        Constructor constructor = new Constructor(rule.driver);
        constructor.open();
        constructor.clickOnFillings();
        constructor.isBunsCurrent();
    }
}
