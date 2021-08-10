package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static io.qameta.allure.Allure.step;

public class RegistrationFormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    Faker faker = new Faker();

    @Test
    void successfulFillFormTest() {
        String firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                email = faker.internet().emailAddress(),
                gender = faker.demographic().sex(),
                phone = faker.phoneNumber().subscriberNumber(10),
                dateOfBirth = "29 July,1990",
                subject = "Maths",
                hobby = "Music",
                path = "ozero.jpg",
                address = faker.address().fullAddress(),
                state = "Haryana",
                city = "Karnal";

        step("Открыть форму регистрации",() -> registrationPage.openPage());

        step("Заполнить обязательные поля формы",() -> {
        registrationPage.typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(email)
                .selectGender(gender)
                .typePhone(phone)
                .setDateOfBirth("29", "July", "1990")
                .selectSubject(subject)
                .selectHobby(hobby)
                .selectFile(path)
                .typeAddress(address)
                .selectState(state)
                .selectCity(city);
        });

        step("Сохранить внесенные изменения",() -> {
        registrationPage.saveForm();
        });

        step("Проверить результат заполнения формы",() -> {
        registrationPage.checkResultsTitle()
                .checkResultsValue(firstName + " " + lastName)
                .checkResultsValue(email)
                .checkResultsValue(gender)
                .checkResultsValue(phone)
                .checkResultsValue(dateOfBirth)
                .checkResultsValue(subject)
                .checkResultsValue(hobby)
                .checkResultsValue(path)
                .checkResultsValue(address)
                .checkResultsValue(state)
                .checkResultsValue(city);
        });
    }
}
