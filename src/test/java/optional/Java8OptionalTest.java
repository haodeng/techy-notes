package optional;

import bean.Car;
import bean.Tire;
import bean.Wheel;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Java8OptionalTest {

    @Test
    public void test(){
        Car car = newCar();

        List<String> s = car.getWheels().stream()
                .map(w -> w.get().getTire())
                .map(t -> t.get().getIdentifier())
                .collect(Collectors.toList());


        Assert.assertEquals(4, s.size());
        Assert.assertEquals("Tire 1", s.get(0));
        Assert.assertEquals(null, s.get(3));

        Assert.assertEquals("Tire 1",
                car.getWheels().get(0).flatMap(Wheel::getTire).map(Tire::getIdentifier).orElse("Unknown"));

        Assert.assertEquals("Unknown",
                car.getWheels().get(3).flatMap(Wheel::getTire).map(Tire::getIdentifier).orElse("Unknown"));

        Assert.assertEquals("Tire 1-copy",
                car.getWheels().get(3).flatMap(Wheel::getTire)
                        .map(Tire::getIdentifier)
                        .orElseGet(
                                () -> car.getWheels().get(0).flatMap(Wheel::getTire).map(t -> t.getIdentifier()).get() + "-copy"
                        ));
    }

    private Car newCar(){
        Car car = new Car();
        List<Optional<Wheel>> wheels = new ArrayList<>();

        Optional<Wheel> wheel1 = Optional.of(new Wheel());
        wheel1.get().setTire(Optional.of(new Tire("Tire 1")));
        wheels.add(wheel1);

        Optional<Wheel> wheel2 = Optional.of(new Wheel());
        wheel2.get().setTire(Optional.of(new Tire("Tire 2")));
        wheels.add(wheel2);

        Optional<Wheel> wheel3 = Optional.of(new Wheel());
        wheel3.get().setTire(Optional.of(new Tire("Tire 3")));
        wheels.add(wheel3);

        Optional<Wheel> wheel4 = Optional.of(new Wheel());
        wheel4.get().setTire(Optional.of(new Tire()));
        wheels.add(wheel4);

        car.setWheels(wheels);
        return car;
    }
}