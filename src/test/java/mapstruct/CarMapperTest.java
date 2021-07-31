package mapstruct;

import mapstruct.dto.CarDto;
import mapstruct.mapper.CarMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CarMapperTest {

    /**
     * Example from https://mapstruct.org/
     */
    @Test
    public void shouldMapCarToDto() {
        //given
        Car car = new Car("Morris", 5, CarType.SEDAN);

        //when
        CarDto carDto = CarMapper.INSTANCE.carToCarDto(car);

        //then
        assertThat(carDto).isNotNull();
        assertThat(carDto.getMake()).isEqualTo("Morris");
        assertThat(carDto.getSeatCount()).isEqualTo(5);
        assertThat(carDto.getType()).isEqualTo("SEDAN");
    }

    @Test
    public void shouldMapCarDtoToCar() {
        //given
        CarDto carDto = new CarDto("Morris", 5, "SEDAN");

        //when
        Car car = CarMapper.INSTANCE.carDtoToCar(carDto);

        //then
        assertThat(car).isNotNull();
        assertThat(car.getMake()).isEqualTo("Morris");
        assertThat(car.getNumberOfSeats()).isEqualTo(5);
        assertThat(car.getType()).isEqualTo(CarType.SEDAN);
    }
}
