import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class StudentRegistrationFormTest {

    static Logger log = LoggerFactory.getLogger(StudentRegistrationFormTest.class);

    @BeforeAll
    static void setupConfig() {
        log.info("@BeforeAll method");
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
    }

    @BeforeEach
    void openDemoqaPage() {
        log.info("@BeforeEach");
        open("https://demoqa.com/automation-practice-form");
    }

    //@AfterEach
    void closeBrowser() {
        log.info("@AfterEach");
        closeWebDriver();
    }

    @Test
    void checkAutomationPracticeForm() {
        //заполняем поля
        $("#firstName").setValue("Name");
        $("#lastName").setValue("Surname");
        $("#userEmail").setValue("myname@example.com");
        $(byText("Male")).click();
        $("#userNumber").setValue("1234567890");
        //выбираем dateOfBirth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1905");
        $(".react-datepicker__month-select").selectOption("December");
        $(".react-datepicker__day--009").click();

        $("#subjectsInput").setValue("English");
        $(byText("English")).click();

        $(byText("Sports")).click();
        $(byText("Reading")).click();
        $("#currentAddress").setValue("My address").scrollTo();
        $(byText("Select State")).click();
        $(byText("Haryana")).click();
        $(byText("Select City")).click();
        $(byText("Karnal")).click();

        //нажимаем кнопку submit
        $("#submit").click();

        //проверяем поля
        $(".table").shouldHave(text("Student Name"), text("Name" + " " + "Surname"));
        $(".table").shouldHave(text("Student Email"), text("myname@example.com"));
        $(".table").shouldHave(text("Gender"), text("Male"));
        $(".table").shouldHave(text("Mobile"), text("1234567890"));
        $(".table").shouldHave(text("Date of Birth"), text("09 December,1905"));
        $(".table").shouldHave(text("Subjects"), text("English"));
        $(".table").shouldHave(text("Hobbies"), text("Sports, Reading"));
        $(".table").shouldHave(text("Address"), text("My address"));
        $(".table").shouldHave(text("State and City"), text("Haryana Karnal"));
    }
}
