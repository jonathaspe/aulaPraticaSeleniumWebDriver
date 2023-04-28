package br.com.juliodelima.taskit.signup;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@DisplayName("Testes automatizados da funcionalidade de Sign Up")
public class SignUpTests {
    @Test
    @DisplayName("Registrar um novo usuário com dados válidos")
    public void testRegistrarUmNovoUsuarioComDadosValidos(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));

        driver.get("http://www.juliodelima.com.br/taskit/");
        driver.findElement(By.id("signup")).click();
        driver.findElement(By.id("name-sign-up")).sendKeys("textUser");
        driver.findElement(By.id("login-sign-up")).sendKeys("textLogin");
        driver.findElement(By.id("password-sign-up")).sendKeys("textPassword");
        driver.findElement(By.id("btn-submit-sign-up")).click();
        String hiUser = driver.findElement(By.className("me")).getText();
        Assertions.assertEquals("Hi, Jonathas Santos", hiUser);

        driver.quit();
    }
}
