package maxim.kim;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class InputTest {
    @BeforeAll
    static void beforeAll(){
        Configuration.startMaximized = true;
        open("https://demoqa.com/automation-practice-form");
    }

    @Test
    void fillInputs(){
    $("#firstName").setValue("First name");
    $("#lastName").setValue("Last name");
    $("#userEmail").setValue("User email");
}

}
