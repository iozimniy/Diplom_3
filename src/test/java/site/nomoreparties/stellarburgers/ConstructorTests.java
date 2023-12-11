package site.nomoreparties.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Rule;
import org.junit.Test;
import site.nomoreparties.stellarburgers.pages.Constructor;

public class ConstructorTests {

    @Rule
    public DriverRule rule = new DriverRule();

    @Test
    @DisplayName("Переход к Начинкам")
    public void transitionToFillingsTest() {
        Constructor constructor = new Constructor(rule.driver);
        constructor.open();
        constructor.isFillingsCurrent();
    }

    @Test
    @DisplayName("Переход к Соусам")
    public void transitionToSauceTest() {
        Constructor constructor = new Constructor(rule.driver);
        constructor.open();
        constructor.isSauceCurrent();
    }

    @Test
    @DisplayName("Переход к Булкам")
    public void transitionToBunsTest() {
        Constructor constructor = new Constructor(rule.driver);
        constructor.open();
        constructor.isFillingsCurrent();
        constructor.isBunsCurrent();
    }
}
