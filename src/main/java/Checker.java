import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Checker {

    final WebDriver driver;

    public Checker() {
        //ChromeOptions options = new ChromeOptions();
        //options.setHeadless(true);
        //driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        driver = new ChromeDriver();
    }

    public List<PersonResult> checkAll(final List<Person> persons) {

        final List<PersonResult> result = new ArrayList<>();

        System.out.println("Start checking ...");

        // check all
        persons.forEach(p -> result.add(check(p)));

        driver.quit();

        System.out.println("... finished checking.");

        return result.stream().filter(personResult -> personResult.isResult()).collect(Collectors.toList());
    }

    public void fillFormAndSubmit(final Person person) {

        driver.get("https://freiburg.corona-ergebnis.de");

        final WebElement orderNumber = driver.findElement(By.id("OrderNumber"));
        orderNumber.sendKeys(person.getOrderNumber());

        final WebElement date = driver.findElement(By.id("date"));
        date.sendKeys(person.getDate());

        final WebElement zip = driver.findElement(By.id("ZipCode"));
        zip.sendKeys(person.getZipCode());

        final WebElement button = driver.findElement(By.id("login-button"));
        button.click();
    }

    public boolean hasResult() {
        return driver.getPageSource().contains("SARS-CoV-2-RNA (PCR)") && driver.getPageSource().contains("Ergebnis:");
    }

    public void closeNote() {
        try {
            new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(By.className("csa-wrapper"))).click();
        } catch (Exception e) {
            // ignore
        }
    }

    private PersonResult check(final Person person) {

        final PersonResult personResult = new PersonResult(person);

        fillFormAndSubmit(person);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (hasResult()) {
            closeNote();
            personResult.setScreenshot(((ChromeDriver) driver).getScreenshotAs(OutputType.FILE));
            personResult.setResult(true);
        }

        return personResult;
    }
}
