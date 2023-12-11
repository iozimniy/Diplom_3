package site.nomoreparties.stellarburgers.user;

import io.qameta.allure.Step;

public class UserGenerator {

    @Step("Создать валидные регистрационные данные")
    public static User generateRandomUser() {
        return new User(DataGenerator.generateRandomEmail(), DataGenerator.generateRandomPassword(), DataGenerator.generateRandomName());
    }

    @Step("Создать невалидные регистрационные данные - пароль меньше 6 символов")
    public static User generateRandomIncorrectPassword() {
        return new User(DataGenerator.generateRandomEmail(), DataGenerator.ganerateRandomIncorrectPassword(), DataGenerator.generateRandomName());
    }
}
