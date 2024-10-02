package uagrm.soe.mdeis.m7.testappweb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestClient {

	private WebDriver driver;

	public static void slowTyping(WebElement element, String text) throws InterruptedException {
		int delayInMillis = 50;
		for (char c : text.toCharArray()) {
			element.sendKeys(String.valueOf(c));
			Thread.sleep(delayInMillis);
		}
	}

	@BeforeEach
	void getUp() {
		driver = new ChromeDriver();
	}

	@Test
	void testRegisterClient() throws InterruptedException {
		driver.get("http://localhost:3000/");
		driver.findElement(By.id("nav-cliente")).click();
		driver.findElement(By.id("btn-cliente-page-add")).click();
		slowTyping(driver.findElement(By.id("form-cliente-code")), "123456");
		slowTyping(driver.findElement(By.id("form-cliente-nombre")), "Marcos Cabrera");
		slowTyping(driver.findElement(By.id("form-cliente-documento")), "1212223");
		slowTyping(driver.findElement(By.id("form-cliente-email")), "marcocabrera@yopmail.com");

		WebElement selectElement = driver.findElement(By.id("form-cliente-tipo-documento"));
		Select select = new Select(selectElement);
		select.selectByVisibleText("NIT");

		driver.findElement(By.id("form-cliente-submit")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());

		assertEquals("cliente Registrado Exitosamente", alert.getText());
		alert.accept();
		// driver.close();
	}

	@Test
	void testRegisterClientWithoutCode() throws InterruptedException {
		driver.get("http://localhost:3000/");
		driver.findElement(By.id("nav-cliente")).click();
		driver.findElement(By.id("btn-cliente-page-add")).click();
		slowTyping(driver.findElement(By.id("form-cliente-nombre")), "Marcos Cabrera");
		slowTyping(driver.findElement(By.id("form-cliente-documento")), "1212223");
		slowTyping(driver.findElement(By.id("form-cliente-email")), "marcocabrera@yopmail.com");

		WebElement selectElement = driver.findElement(By.id("form-cliente-tipo-documento"));
		Select select = new Select(selectElement);
		select.selectByVisibleText("NIT");

		driver.findElement(By.id("form-cliente-submit")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());

		assertEquals("Debe indicar un Codigo", alert.getText());
		alert.accept();
		driver.close();
	}

	@Test
	void testRegisterClientWithoutNombre() throws InterruptedException {
		driver.get("http://localhost:3000/");
		driver.findElement(By.id("nav-cliente")).click();
		driver.findElement(By.id("btn-cliente-page-add")).click();
		slowTyping(driver.findElement(By.id("form-cliente-code")), "123456");
		slowTyping(driver.findElement(By.id("form-cliente-documento")), "1212223");
		slowTyping(driver.findElement(By.id("form-cliente-email")), "marcocabrera@yopmail.com");

		WebElement selectElement = driver.findElement(By.id("form-cliente-tipo-documento"));
		Select select = new Select(selectElement);
		select.selectByVisibleText("NIT");

		driver.findElement(By.id("form-cliente-submit")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());

		assertEquals("Debe Indicar un Nombre", alert.getText());
		alert.accept();
		// driver.close();
	}

	@Test
	void testRegisterClientWithoutDocumento() throws InterruptedException {
		driver.get("http://localhost:3000/");
		driver.findElement(By.id("nav-cliente")).click();
		driver.findElement(By.id("btn-cliente-page-add")).click();
		slowTyping(driver.findElement(By.id("form-cliente-code")), "123456");
		slowTyping(driver.findElement(By.id("form-cliente-nombre")), "Marcos Cabrera");
		slowTyping(driver.findElement(By.id("form-cliente-email")), "marcocabrera@yopmail.com");

		WebElement selectElement = driver.findElement(By.id("form-cliente-tipo-documento"));
		Select select = new Select(selectElement);
		select.selectByVisibleText("NIT");

		driver.findElement(By.id("form-cliente-submit")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());

		assertEquals("Debe indicar un numero de documento", alert.getText());
		alert.accept();
		// driver.close();
	}

	@Test
	void testRegisterClientWithoutEmail() throws InterruptedException {
		driver.get("http://localhost:3000/");
		driver.findElement(By.id("nav-cliente")).click();
		driver.findElement(By.id("btn-cliente-page-add")).click();
		slowTyping(driver.findElement(By.id("form-cliente-code")), "123456");
		slowTyping(driver.findElement(By.id("form-cliente-nombre")), "Marcos Cabrera");
		slowTyping(driver.findElement(By.id("form-cliente-documento")), "1212223");

		WebElement selectElement = driver.findElement(By.id("form-cliente-tipo-documento"));
		Select select = new Select(selectElement);
		select.selectByVisibleText("NIT");

		driver.findElement(By.id("form-cliente-submit")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());

		assertEquals("Debe indicar un correo electrónico valido", alert.getText());
		alert.accept();
		// driver.close();
	}

}
