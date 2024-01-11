package site.nomoreparties.stellarburgers.user;

import site.nomoreparties.stellarburgers.models.User;

import static site.nomoreparties.stellarburgers.datafaker.UserDataFaker.*;

public class UserGenerator {

    private UserGenerator() {
    }

    public static User randomValidUser() {
        return new User()
                .withEmail(randomValidEmail())
                .withPassword(randomValidPassword())
                .withName(randomValidName());
    }
}