import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class CheckerTest {

    @Test
    public void test() throws MalformedURLException, InterruptedException {

        final Person person = new Person();
        person.setDate("18031970");
        person.setOrderNumber("12812981");
        person.setZipCode("12345");

        final Checker checker = new Checker();

        checker.fillFormAndSubmit(person);
        checker.closeNote();

        TimeUnit.MINUTES.sleep(5);
    }

}