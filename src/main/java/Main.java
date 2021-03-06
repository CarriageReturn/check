import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.mail.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        final Person[] persons;

        try {
            persons = getPersonsToCheck();
        } catch (IOException e) {
            System.out.println("No persons to check found. Place a file named 'persons.json' with all persons to check in the root directory.");
            return;
        }

        try {
            final List<PersonResult> result = new Checker().checkAll(new ArrayList<>(Arrays.asList(persons)));

            if (!result.isEmpty()) {

                final StringBuffer sb = new StringBuffer();
                result.stream().forEach(r -> sb.append(r.toString() + "\n"));

                final List<File> files = result.stream().map(r -> r.getScreenshot()).collect(Collectors.toList());
                new Mailer().createAndSendMail(sb.toString(), files);

                System.out.println(String.format("Checked %d persons. Retrieved %d results.", persons.length, result.size()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Person[] getPersonsToCheck() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("persons.json"), Person[].class);
    }
}
