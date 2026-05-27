package stepdefinitions;

import com.codeborne.selenide.Condition;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.nio.file.Paths;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class LoginSteps {

    @Given("Я открываю страницу авторизации")
    public void openLoginPage() {
        String absolutePath = Paths.get("src/test/resources/login.html").toAbsolutePath().toString();
        String url = "file:///C:/login.html";

        open(url);

        // 1. Принудительное ожидание загрузки body
        $("body").shouldBe(Condition.visible, Duration.ofSeconds(10));

        // 2. Выведем в консоль ВЕСЬ текст страницы, который видит Selenide
        System.out.println("--- СОДЕРЖИМОЕ СТРАНИЦЫ ---");
        System.out.println(com.codeborne.selenide.Selenide.executeJavaScript("return document.body.innerHTML;").toString());
        System.out.println("---------------------------");

        // 3. Попробуем найти элемент не по ID, а по тегу, чтобы проверить, видит ли он хоть что-то
        boolean hasInputs = !$$("input").isEmpty();
        System.out.println("Найдено input-полей: " + $$("input").size());
    }

    @When("Я ввожу логин {string} и пароль {string}")
    public void enterCredentials(String login, String password) {
        $("#username").shouldBe(Condition.visible).setValue(login);
        $("#password").shouldBe(Condition.visible).setValue(password);
    }

    @When("Нажимаю кнопку войти")
    public void clickLoginButton() {
        $("#loginButton").shouldBe(Condition.visible).click();
    }

    @Then("Я вижу сообщение {string}")
    public void я_вижу_сообщение(String message) {
        $("#result").shouldHave(Condition.text(message));
    }

    @And("Я вижу приветствие {string}")
    public void я_вижу_приветствие(String greeting) {
        $("#greeting").shouldHave(Condition.text(greeting));
    }
}