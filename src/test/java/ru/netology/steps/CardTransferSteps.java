package ru.netology.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import ru.netology.data.DataHelper;
import ru.netology.page.DashBoardPage;
import ru.netology.page.LoginPage;

public class CardTransferSteps {
    private static LoginPage loginPage;
    private static DashBoardPage dashboardPage;

    @Пусть("пользователь залогинен с именем {string} и паролем {string}")
    public void authPage(String login, String password) {
        loginPage = Selenide.open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getOthersInfo(login, password);
        var verificationPage = loginPage.validLogin(authInfo);
        dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCodeFor());
    }

    @Когда("пользователь переводит {string} рублей с карты с номером {string} на свою {int} карту с главной страницы,")
    public void transfer(String amount, String firstNumberCard, int secondNumberCard) {
        var secondCard = DataHelper.getCardIndex(secondNumberCard);
        var transferPage = dashboardPage.transferPage(secondCard);
        transferPage.transfer(amount, DataHelper.getCardNumber(firstNumberCard));
    }

    @Тогда("баланс его {int} карты из списка на главной странице должен стать {int} рублей.")
    public void balance(int cardNumber, int newBalance) {
        var cardIndex = cardNumber - 1;
        var actualBalance = dashboardPage.getCardBalanceForIndex(cardIndex);
        Assertions.assertEquals(newBalance, actualBalance);
    }
}
