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
    PageObjects po = new PageObjects();

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
        $(byXpath("//*[@aria-label = 'Choose Tuesday, December 1st, 1998']")).click();
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
        verification("Student Name", po.firstName + " " + po.secondName);
        System.out.println("name Match");
        verification("Student Email", po.email);
        System.out.println("email Match");
        verification("Gender", po.gender);
        System.out.println("Gender Match");
        verification("Mobile", po.mobilePhone);
        System.out.println("MobilPhone Match");
        verification("Date of Birth", po.dateOfBirth);
        System.out.println("Date of Birth Match");
        verification("Subjects", po.subject);
        System.out.println("Subjects Match");
        verification("Hobbies", po.hobbies);
        System.out.println("Hobbies Match");
        verification("Picture", po.picture);
        System.out.println("Picture Match");
        verification("Address", po.address);
        System.out.println("Address Match");
        verification("State and City", po.stateAndCity);
        System.out.println("State and City Match");


    }

    //verification method
    private void verification(String label, String value) {
        String string = MessageFormat.format("//td[contains(text(),\"{0}\")]/..//td[2] ", label);
        $(byXpath(string)).should(Condition.exactText(value));
    }


}
