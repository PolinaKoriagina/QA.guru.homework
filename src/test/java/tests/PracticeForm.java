package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeForm {

    static Logger logger = LoggerFactory.getLogger(PracticeForm.class);

    @BeforeAll
    static void setUp() {
        logger.info("@BeforeAll method");
        Configuration.startMaximized = true;
        Configuration.browser = "chrome";
    }

    @AfterAll
    static void tearDown() {
        logger.info("@AfterAll method");
        logger.info("The form is submitted :)");
    }
    @Test
    void successfulSubmitForm(){

        String name = "Polina";
        String lastName = "Koriagina";
        String email = "pirog.p@gmail.com";
        String phoneNumber = "1234567890";
        String state = "NCR";
        String city = "Delhi";
        String gender = "Female";

        open("https://demoqa.com/automation-practice-form");
        $("[id=\"firstName\"]").setValue(name);
        $("[id=\"lastName\"]").setValue(lastName);
        $("[id=\"userEmail\"]").setValue(email);
        $(byText(gender)).click();
        $("[id=\"userNumber\"]").setValue(phoneNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__year-select").selectOption("1996");
        $("[aria-label='Choose Saturday, June 22nd, 1996']").click();
        $("[id=\"subjectsInput\"]").setValue("Co").pressEnter();
        $("[for=\"hobbies-checkbox-2\"]").click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/IMG_4209.jpeg"));
        $(byText("Select State")).scrollTo().click();
        $(byText(state)).click();
        $(byText("Select City")).click();
        $(byText(city)).click();
        $("[id=\"submit\"]").click();
        $("[id=\"example-modal-sizes-title-lg\"]").shouldHave(exactText("Thanks for submitting the form"));
        $(byXpath("//table/tbody/tr[1]/td[2]")).shouldHave(text(name + " " + lastName));
        $(byXpath("//table/tbody/tr[2]/td[2]")).shouldHave(text(email));
        $(byXpath("//table/tbody/tr[3]/td[2]")).shouldHave(text(gender));
        $(byXpath("//table/tbody/tr[4]/td[2]")).shouldHave(text(phoneNumber));
        $(byXpath("//table/tbody/tr[5]/td[2]")).shouldHave(text("22 June,1996"));
        $(byXpath("//table/tbody/tr[6]/td[2]")).shouldHave(text("Computer Science"));
        $(byXpath("//table/tbody/tr[7]/td[2]")).shouldHave(text("Reading"));
        $(byXpath("//table/tbody/tr[8]/td[2]")).shouldHave(text("IMG_4209.jpeg"));
        $(byXpath("//table/tbody/tr[10]/td[2]")).shouldHave(text(state + " " + city));

    }

}
