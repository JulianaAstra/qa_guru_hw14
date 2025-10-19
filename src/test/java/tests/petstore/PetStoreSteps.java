package tests.petstore;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class PetStoreSteps {
    @Step("Открыть страницу яндекс")
    public void openPage() {
        open("https://ya.ru");
    }

    @Step("Найти несуществующий элемент")
    public void findElement() {
        $("#broken_test").shouldBe(visible);
    }

    @Step("Закрыть окно")
    public void closePage() {
        closeWebDriver();
    }
}
