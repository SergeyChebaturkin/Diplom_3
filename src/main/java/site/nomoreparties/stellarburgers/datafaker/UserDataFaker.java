package site.nomoreparties.stellarburgers.datafaker;

public class UserDataFaker extends DataFaker {
    public static String randomValidEmail() {
        return faker.internet().emailAddress();
    }
    public static String randomValidPassword() {
        return faker.internet().password(6,10,true);    }
    public static String randomValidName() {
        return faker.name().username();
    }
    public static String randomInvalidPassword() {
        return fakeValuesService.regexify("([A-Z]|[a-z]|[0-9]){" + faker.number().numberBetween(1, 5) + "}");
    }
}
