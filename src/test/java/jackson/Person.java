package jackson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Person {
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
    private boolean active = true;
    private Instant created;

    enum Gender {
        MALE, FEMALE;
    }
}
