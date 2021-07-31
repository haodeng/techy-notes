package mapstruct.mapper;

import mapstruct.Car;
import mapstruct.dto.CarDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    /**
     * Example from https://mapstruct.org/
     */
    @Mapping(source = "numberOfSeats", target = "seatCount")
    CarDto carToCarDto(Car car);

    @Mapping(source = "seatCount", target = "numberOfSeats")
    Car carDtoToCar(CarDto carDto);
}
