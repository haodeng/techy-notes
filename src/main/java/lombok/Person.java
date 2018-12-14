package lombok;


import java.time.LocalDate;

@AllArgsConstructor
@ToString
public class Person {
    @Getter @Setter private String name;
    @Getter @Setter private LocalDate dateOfBirth;
    @Getter @Setter private Address address;


}