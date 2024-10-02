package uagrm.soe.mdeis.m7.testappweb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestVenta {
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
   void testPerformSale() throws InterruptedException {
      driver.get("http://localhost:3000/");
      driver.findElement(By.id("nav-producto")).click();
      Thread.sleep(300);
      driver.findElement(By.id("prod-cart-0")).click();
      Thread.sleep(300);
      driver.findElement(By.id("prod-cart-1")).click();
      Thread.sleep(300);
      driver.findElement(By.id("prod-cart-2")).click();
      Thread.sleep(800);
      driver.findElement(By.id("nav-servicio")).click();
      Thread.sleep(300);
      driver.findElement(By.id("serv-cart-0")).click();
      Thread.sleep(300);
      driver.findElement(By.id("serv-cart-1")).click();
      Thread.sleep(300);
      driver.findElement(By.id("nav-venta")).click();
      Thread.sleep(300);
      driver.findElement(By.id("form-venta-facturado")).click();
      Thread.sleep(300);

      WebElement selectElement = driver.findElement(By.id("form-venta-cliente"));
      Select select = new Select(selectElement);
      select.selectByIndex(1);

      Thread.sleep(400);
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

      Thread.sleep(400);
      driver.findElement(By.id("form-venta-submit")).click();

      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
      Alert alert = wait.until(ExpectedConditions.alertIsPresent());

      assertEquals("Venta Realizada", alert.getText());
      alert.accept();
   }

}
