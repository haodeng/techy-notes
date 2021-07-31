package mapstruct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDto {
    private String street;
    private String city;
    private String postCode;
}
