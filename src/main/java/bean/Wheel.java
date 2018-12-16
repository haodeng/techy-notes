package bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

public class Wheel {

    @Getter @Setter
    private Optional<Tire> tire;


}