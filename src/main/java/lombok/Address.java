package lombok;

@AllArgsConstructor
@ToString
public class Address {
    @Getter @Setter private String houseNumber;
    @Getter @Setter private String street;
    @Getter @Setter private String city;
    @Getter @Setter private String state;
    @Getter @Setter private String country;
    @Getter @Setter private String zip;
}