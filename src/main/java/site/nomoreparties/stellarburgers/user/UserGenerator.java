package site.nomoreparties.stellarburgers.user;

public class UserGenerator {

    public static User generateRandomUser() {
        return new User(DataGenerator.generateRandomEmail(), DataGenerator.generateRandomPassword(), DataGenerator.generateRandomName());
    }

    public static User generateRandomIncorrectPassword() {
        return new User(DataGenerator.generateRandomEmail(), DataGenerator.ganerateRandomIncorrectPassword(), DataGenerator.generateRandomName());
    }
}
