import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JsonTest {

    @Test
    public void writePerson() throws IOException {

        final Person person = new Person();
        person.setDate("18031970");
        person.setOrderNumber("12812981");
        person.setZipCode("12345");

        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("target/person_out.json"), person);
    }

    @Test
    public void writePersonList() throws IOException {

        final Person p1 = new Person();
        p1.setDate("18031970");
        p1.setOrderNumber("12812981");
        p1.setZipCode("12345");

        final Person p2 = new Person();
        p2.setDate("18031970");
        p2.setOrderNumber("12812981");
        p2.setZipCode("12345");

        ArrayList persons = new ArrayList();
        persons.add(p1);
        persons.add(p2);

        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("target/personList_out.json"), persons);
    }

    @Test
    public void readPerson() throws IOException {

        final ObjectMapper objectMapper = new ObjectMapper();
        final Person person = objectMapper.readValue(new File("target/person_in.json"), Person.class);

        assertEquals("18031970", person.getDate());
        assertEquals("12812981", person.getOrderNumber());
        assertEquals("12345", person.getZipCode());
    }

    @Test
    public void readPersonList() throws IOException {

        final ObjectMapper objectMapper = new ObjectMapper();
        final Person[] persons = objectMapper.readValue(new File("target/personList_in.json"), Person[].class);

        assertEquals(2, persons.length);

        assertEquals("18031970", persons[0].getDate());
        assertEquals("12812981", persons[0].getOrderNumber());
        assertEquals("12345", persons[0].getZipCode());

        assertEquals("18031970", persons[1].getDate());
        assertEquals("12812981", persons[1].getOrderNumber());
        assertEquals("12345", persons[1].getZipCode());
    }
}


