package mapstruct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PersonDto {
    private String firstName;
    private String lastName;
    private AddressDto addressDto;
    private List<String> languages;
}
