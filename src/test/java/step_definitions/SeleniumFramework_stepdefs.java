package step_definitions;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.junit.Assert;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


public class SeleniumFramework_stepdefs{
    public WebDriver driver;

    
    @Before
    public void startDriver() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "src/test/java/driver/chromedriver.exe");

    	driver = new ChromeDriver();
    }

    @Given("^I access tje Prime Control page$")
    public void i_access_tje_Prime_Control_page() throws Throwable {
        driver.get("https://www.primecontrol.com.br/");
        driver.manage().window().maximize();
    }

    @And("^I click on the option Formulario de Contato$")
    public void i_click_on_the_option_Formulario_de_Contato() throws Throwable {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("div.textwidget:nth-child(2) > p:nth-child(3) > a:nth-child(1)")));
        driver.findElement(By.cssSelector("div.textwidget:nth-child(2) > p:nth-child(3) > a:nth-child(1)")).click();
    }

    @And("^I fill All field$")
    public void i_fill_All_field() throws Throwable {
        driver.findElement(By.cssSelector("#mauticform_input_contatonowebsite_primeiro_nome")).sendKeys("Primeiro Nome");
        driver.findElement(By.cssSelector("#mauticform_input_contatonowebsite_sobrenome")).sendKeys("Sobrenome");
        driver.findElement(By.cssSelector("#mauticform_input_contatonowebsite_empresa")).sendKeys("Empresa");
        driver.findElement(By.cssSelector("#mauticform_input_contatonowebsite_cargo")).sendKeys("Cargo");
        driver.findElement(By.cssSelector("#mauticform_input_contatonowebsite_cidade")).sendKeys("Floripa");
        driver.findElement(By.cssSelector("#mauticform_input_contatonowebsite_email")).sendKeys("luiz.lohn@gmail.com");
        driver.findElement(By.cssSelector("#mauticform_input_contatonowebsite_telefone_com_codigo_de_ar")).sendKeys("48996927487");
        driver.findElement(By.cssSelector("#mauticform_input_contatonowebsite_comentarios1")).sendKeys("comentario");
        new Select(driver.findElement(By.cssSelector("#mauticform_input_contatonowebsite_como_voce_nos_conheceu"))).selectByVisibleText("Google");
    }

    @And("^I click on the button Enviar$")
    public void i_click_on_the_button_Enviar() throws Throwable {
        driver.findElement(By.cssSelector("#mauticform_input_contatonowebsite_enviar")).click();
    }

    @Then("^should display a success message$")
    public void should_display_a_success_message() throws Throwable {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("#mauticform_contatonowebsite_message")));
        String message = driver.findElement(By.cssSelector("#mauticform_contatonowebsite_message")).getText();
        Assert.assertSame("Obrigado! Seus dados foram enviados com sucesso. Responderemos seu contato em até 1 dia útil.", message);
    }
    @After
    public void quitDriver(Scenario scenario) {
       
        if(scenario.isFailed()) {
        try {
        	 scenario.write("Current Page URL is " + driver.getCurrentUrl());
            byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
        
        }
        driver.quit();
        
    }
    
}