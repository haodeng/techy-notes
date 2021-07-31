package mapstruct;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Person {
    private String firstName;
    private String lastName;
    private Address address;
    private List<Language> languages;
}
