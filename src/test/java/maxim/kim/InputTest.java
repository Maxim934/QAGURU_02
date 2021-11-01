package maxim.kim;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.text.MessageFormat;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class InputTest {
    File img = new File("src/test/resources/img.png");
    TestData po = new TestData();

    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
        open("https://demoqa.com/automation-practice-form");
    }

    @Test
    void fillInputs() {
        //Заполнение инпута имя
        $("#firstName").setValue(po.firstName);
        //заполнение инпута фамилия
        $("#lastName").setValue(po.secondName);
        //Заполнение инпута почта
        $("#userEmail").setValue(po.email);
        //клик на Male
        $(byText("Male")).click();
        //заполнение номера телефона
        $("#userNumber").setValue(po.mobilePhone);
        //Клик на календарь
        $("#dateOfBirthInput").click();
        //Выбора года 1998
        $(".react-datepicker__year-select").selectOption("1998");
        //Выбор месяца декабрь
        $(".react-datepicker__month-select").selectOption("December");
        //Выбор даты
        $(".react-datepicker__day.react-datepicker__day--001").click();
        //Выбор урока
        $("#subjectsInput").click();
        $("#subjectsInput").sendKeys("M");
        //Find Math
        $(byText(po.subject)).click();
        //delete Math
//        $(".css-19bqh2r").click(); - тут есть баг при нажатии на кнопку удалить предмет (Х) - лэндинг сбивается позже допишу с вызовом этого бага

        //hobbies
        $("[for=hobbies-checkbox-1]").click();
        $("[for=hobbies-checkbox-2]").click();
        $("[for=hobbies-checkbox-3]").click();

        //upload img
        $("#uploadPicture").uploadFile(img);
        //address
        $("#currentAddress").setValue(po.address);
        //state
        $("#state").click();
        //choose NCR
        $(byText("NCR")).click();
        //city
        $("#city").click();
        //Select Delhi
        $(byText("Delhi")).click();
        //submit
        $("#submit").click();

        //Verification
        checkTableRow("Student Name", po.firstName + " " + po.secondName);
        checkTableRow("Student Email", po.email);
        checkTableRow("Gender", po.gender);
        checkTableRow("Mobile", po.mobilePhone);
        checkTableRow("Date of Birth", po.dateOfBirth);
        checkTableRow("Subjects", po.subject);
        checkTableRow("Hobbies", po.hobbies);
        checkTableRow("Picture", po.picture);
        checkTableRow("Address", po.address);
        checkTableRow("State and City", po.stateAndCity);

    }

    //verification method
    private void checkTableRow(String rowLabel, String expectedResult) {
        String string = MessageFormat.format("//td[contains(text(),\"{0}\")]/..//td[2] ", rowLabel);
        $(byXpath(string)).should(Condition.exactText(expectedResult));
    }


}
