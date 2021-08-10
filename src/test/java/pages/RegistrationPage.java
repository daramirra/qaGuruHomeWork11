package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import components.Calendar;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;

public class RegistrationPage {

    private final static String FORM_TITLE = "Student Registration Form";
    private final static String RESULTS_TITLE = "Thanks for submitting the form";

    private final Calendar calendar = new Calendar();
    private final SelenideElement modal = $("[role=dialog]");

    @Step("Открыть страницу с формой регистрации")
    public void openPage(){
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(FORM_TITLE));
    }

    @Step("Заполнить поле 'First name'")
    public RegistrationPage typeFirstName(String firstName){
        $("#firstName").setValue(firstName);
        return this;
    }

    @Step("Заполнить поле 'Last name'")
    public RegistrationPage typeLastName(String lastName){
        $("#lastName").setValue(lastName);
        return this;
    }

    @Step("Заполнить поле 'Email'")
    public RegistrationPage typeEmail(String email){
        $("#userEmail").setValue(email);
        return this;
    }

    @Step("Заполнить поле 'Gender'")
    public RegistrationPage selectGender(String gender){
        $(format("[name=gender][value=%s]", gender)).parent().click();
        return this;
    }

    @Step("Заполнить поле 'Mobile'")
    public RegistrationPage typePhone(String phone){
        $("#userNumber").setValue(phone);
        return this;
    }

    @Step("Заполнить поле 'Date of Birth'")
    public RegistrationPage setDateOfBirth(String day, String month, String year){
        calendar.setDate(day, month, year);
        return this;
    }

    @Step("Заполнить поле 'Subjects'")
    public RegistrationPage selectSubject(String subject){
        $("#subjectsInput").setValue(subject).pressEnter();
        return this;
    }

    @Step("Заполнить поле 'Hobbies'")
    public RegistrationPage selectHobby(String hobby){
        $("#hobbiesWrapper").$(byText(hobby)).click();
        return this;
    }

    @Step("Приложить файл в поле 'Picture'")
    public RegistrationPage selectFile(String path){
        $("#uploadPicture").uploadFromClasspath(path);
        return this;
    }

    @Step("Заполнить поле 'Current Address'")
    public RegistrationPage typeAddress(String address){
        $("#currentAddress").setValue(address);
        return this;
    }

    @Step("Заполнить поле 'Select State'")
    public RegistrationPage selectState(String state){
        $("#react-select-3-input").setValue(state).pressEnter();
        return this;
    }

    @Step("Заполнить поле 'Select City'")
    public RegistrationPage selectCity(String city){
        $("#react-select-4-input").setValue(city).pressEnter();
        return this;
    }

    @Step("Нажать кнопку 'Submit'")
    public RegistrationPage saveForm(){
        $("#submit").scrollTo().click();
        return this;
    }

    @Step("Проверить заголовок модального окна с результатами заполнения формы регистрации")
    public RegistrationPage checkResultsTitle(){
        modal.$(".modal-title").shouldHave(text(RESULTS_TITLE));
        return this;
    }

    @Step("Проверить содержимое заполненных полей формы регистрации")
    public RegistrationPage checkResultsValue(String value){
        modal.$(".table-responsive").shouldHave(text(value));
        return this;
    }
}
