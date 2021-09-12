package jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonMappingTest {
    private static ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    public static void init() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    public void personToJson() throws JsonProcessingException {
        Person person = Person.builder()
                .name("Hao")
                .dateOfBirth(LocalDate.of(2001, 9, 12))
                .gender(Person.Gender.MALE)
                .active(true)
                .build();

        String json = objectMapper.writeValueAsString(person);
        // {"name":"Hao","dateOfBirth":"2001-09-12","gender":"MALE","active":true}
        System.out.println(json);
    }

    @Test
    public void jsonToPerson() throws JsonProcessingException {
        String json = "{\"name\":\"Hao\",\"dateOfBirth\":\"2001-09-12\",\"gender\":\"MALE\",\"active\":true}";
        Person person = objectMapper.readValue(json, Person.class);

        Assertions.assertEquals("Hao", person.getName());
        Assertions.assertEquals(LocalDate.of(2001, 9, 12), person.getDateOfBirth());
        Assertions.assertEquals(Person.Gender.MALE, person.getGender());
        Assertions.assertEquals(true, person.isActive());
    }

    @Test
    public void jsonToMap() throws JsonProcessingException {
        String json = "{\"name\":\"Hao\",\"dateOfBirth\":\"2001-09-12\",\"gender\":\"MALE\",\"active\":true}";
        Map<String, Object> map
                = objectMapper.readValue(json, new TypeReference<>(){});

        Assertions.assertEquals("Hao", map.get("name"));
        Assertions.assertEquals("2001-09-12", map.get("dateOfBirth"));
        Assertions.assertEquals("MALE", map.get("gender"));
        Assertions.assertEquals(true, map.get("active"));
    }

    @Test
    public void jsonToMapAnotherWay() throws JsonProcessingException {
        String json = "{\"name\":\"Hao\",\"dateOfBirth\":\"2001-09-12\",\"gender\":\"MALE\",\"active\":true}";
        Map<String, Object> map
                = objectMapper.readValue(json, Map.class);

        Assertions.assertEquals("Hao", map.get("name"));
        Assertions.assertEquals("2001-09-12", map.get("dateOfBirth"));
        Assertions.assertEquals("MALE", map.get("gender"));
        Assertions.assertEquals(true, map.get("active"));
    }

    @Test
    public void mapToJson() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Hao");
        map.put("dateOfBirth", LocalDate.of(2001, 9, 12));
        map.put("gender", Person.Gender.MALE);
        map.put("active", true);

        String json = objectMapper.writeValueAsString(map);

        // {"gender":"MALE","name":"Hao","active":true,"dateOfBirth":"2001-09-12"}
        System.out.println(json);
    }

    @Test
    public void jsonArrayToList() throws JsonProcessingException {
        String json = "[{\"name\":\"Hao\",\"dateOfBirth\":\"2001-09-12\",\"gender\":\"MALE\",\"active\":true}, " +
                "{\"name\":\"Hau\",\"dateOfBirth\":\"2001-09-13\",\"gender\":\"MALE\",\"active\":true}]";

        List<Person> list = Arrays.asList(objectMapper.readValue(json, Person[].class));
        Assertions.assertEquals(2, list.size());
    }
}
