package mapstruct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * Example from https://mapstruct.org/
 */
@AllArgsConstructor
@Data
public class CarDto {

    private String make;
    private int seatCount;
    private String type;

}
