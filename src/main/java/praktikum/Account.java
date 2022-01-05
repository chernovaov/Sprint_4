package praktikum;

import io.qameta.allure.Step;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account {

    private final String name;
    private static final String CARDHOLDER =
            "^(?=.{3,19}$)[а-яёА-ЯЁa-zA-Z]+\\s[а-яёА-ЯЁa-zA-Z]+";

    private static final Pattern pattern = Pattern.compile(CARDHOLDER);

    public Account(String name) {
        this.name = name;
    }

    @Step("Проверить, что строка соответствует требованиям")
    public boolean checkNameToEmboss() {
        if (name == null) {
           return false;
        }
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

}
