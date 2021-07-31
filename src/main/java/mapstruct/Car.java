package mapstruct;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Example from https://mapstruct.org/
 */
@Data
@AllArgsConstructor
public class Car {

    private String make;
    private int numberOfSeats;
    private CarType type;
}
