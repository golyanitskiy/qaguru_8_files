package qaguru.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class SelenideDownloadTest {
    void setUp() {
        Configuration.headless = true;
    }

    @Test
    void downloadTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md\"");
        File file = $("#raw-url").download();
        try (InputStream is = new FileInputStream(file)) {
            assertThat(new String(is.readAllBytes(), StandardCharsets.UTF_8))
                    .contains("This repository is the home of the next generation of JUnit");
        }
    }

    @Test
    void uploadSelenideTest() {
        open("https://the-internet.herokuapp.com/upload");
        $("input[type = 'file']").uploadFromClasspath("files/1.txt");
        $("#file-submit").click();
        $("div.example").shouldHave(Condition.text("File Uploaded!"));
        $("#uploaded-files").shouldHave(Condition.text("1.txt"));
    }
}