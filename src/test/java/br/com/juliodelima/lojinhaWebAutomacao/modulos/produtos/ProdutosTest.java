package br.com.juliodelima.lojinhaWebAutomacao.modulos.produtos;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@DisplayName("Testes Web do modulo de Produtos")
public class ProdutosTest {
    @Test
    @DisplayName("Validar que não é permitido registrar um produto com valor igual a zero")
    public void testNaoPermitirRegistrarProdutoComValorIgualAZero(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        driver.manage().window().maximize();

        driver.get("http://165.227.93.41/lojinha-web/v2/");
        driver.findElement(By.cssSelector("label[for='usuario']")).click();
        driver.findElement(By.id("usuario")).sendKeys("admin");
        driver.findElement(By.cssSelector("label[for='senha']")).click();
        driver.findElement(By.id("senha")).sendKeys("admin");
        driver.findElement(By.cssSelector("button[class='btn waves-effect waves-light']")).click();

        driver.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        driver.findElement(By.id("produtonome")).sendKeys("MacBook Pro 13 M2 CPU 8-core GPU 10-core 256GB");
        driver.findElement(By.id("produtovalor")).sendKeys("000");
        driver.findElement(By.id("produtocores")).sendKeys("Cinzento sideral");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
//        <div class="toast rounded" style="top: 0px; opacity: 0.771496; margin-top: -1.42519px;">O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00</div>
        String mensagemToast = driver.findElement(By.cssSelector(".toast.rounded")).getText();
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemToast);

        driver.quit();
    }
}
