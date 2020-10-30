import java.io.File;

public class PersonResult {

    private Person person;

    private boolean result = false;

    private File screenshot = null;

    public PersonResult(Person person) {
        this.person = person;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setScreenshot(File screenshot) {
        this.screenshot = screenshot;
    }

    public boolean isResult() {
        return result;
    }

    public File getScreenshot() {
        return screenshot;
    }

    @Override
    public String toString() {
        if (result) {
            return "Testergebnis für " + person.getDate() + " verfügbar.";
        } else {
            return "Noch kein Testergebnis für " + person.getDate() + " verfügbar.";
        }
    }
}
