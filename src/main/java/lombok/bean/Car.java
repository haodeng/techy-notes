package lombok.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

public class Car {

    @Getter @Setter
    private List<Optional<Wheel>> wheels;
}