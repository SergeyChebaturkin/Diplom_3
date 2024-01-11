package site.nomoreparties.stellarburgers.datafaker;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.util.Locale;

public class DataFaker {
    protected static final Faker faker = new Faker(new Locale("en"));
    protected static final FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("en"), new RandomService());
}
