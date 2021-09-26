package java8.optional;

import lombok.bean.Car;
import lombok.bean.Tire;
import lombok.bean.Wheel;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OptionalTest {
    @Test
    public void testOfNull() {
        Car car = null;
        String carName = Optional.ofNullable(car)
                .map(myCar -> myCar.toString())
                .orElseGet(() -> newCar().toString());

        Assert.assertNotNull(carName);
    }

    @Test(expected = NullPointerException.class)
    public void testOf() {
        Car car = null;
        Optional.of(car);
    }

    @Test
    public void test() {
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

    @Test
    public void test2() {
        Car car = newCar2();

        Assert.assertFalse(car.getWheels().get(2).get().getTire().isPresent());
        Assert.assertFalse(car.getWheels().get(3).isPresent());

        List<Tire> tires = car.getWheels().stream()
                .map(wheel -> wheel.orElse(spareWheel()))
                .map(wheel -> wheel.getTire().orElse(spareTire()))
                .sorted((t1, t2) -> t1.getIdentifier().compareTo(t2.getIdentifier()))
                .collect(Collectors.toList());

        Assert.assertEquals(4, tires.size());
        Assert.assertEquals("Spare Tire", tires.get(0).getIdentifier());
        Assert.assertEquals("Tire 1", tires.get(1).getIdentifier());
        Assert.assertEquals("Tire 2", tires.get(2).getIdentifier());
        Assert.assertEquals("Tire of spare wheel", tires.get(3).getIdentifier());
    }

    private Car newCar() {
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

    private Car newCar2() {
        Car car = new Car();
        List<Optional<Wheel>> wheels = new ArrayList<>();

        Optional<Wheel> wheel1 = Optional.of(new Wheel());
        wheel1.get().setTire(Optional.of(new Tire("Tire 1")));
        wheels.add(wheel1);

        Optional<Wheel> wheel2 = Optional.of(new Wheel());
        wheel2.get().setTire(Optional.of(new Tire("Tire 2")));
        wheels.add(wheel2);

        //No tire
        Optional<Wheel> wheel3 = Optional.of(new Wheel());
        wheel3.get().setTire(Optional.empty());
        wheels.add(wheel3);

        Optional<Wheel> wheel4 = Optional.empty();
        wheels.add(wheel4);

        car.setWheels(wheels);
        return car;
    }

    private Wheel spareWheel() {
        Wheel wheel = new Wheel();
        wheel.setTire(Optional.of(new Tire("Tire of spare wheel")));

        return wheel;
    }

    private Tire spareTire() {
        return new Tire("Spare Tire");
    }

}