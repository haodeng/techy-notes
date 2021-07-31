package mapstruct;

import mapstruct.dto.AddressDto;
import mapstruct.dto.PersonDto;
import mapstruct.mapper.PersonMapper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonMapperTest {
    @Test
    public void shouldMapPersonToPersonDto() {
        // given:
        Person person = new Person(
                "Hao",
                "Deng",
                new Address("street 1", "KBH", "2000"),
                Arrays.asList(Language.CHINESE, Language.DANISH));

        // when:
        PersonDto personDto = PersonMapper.INSTANCE.personToPersonDto(person);

        // then:
        assertThat(personDto).isNotNull();
        assertThat(personDto.getFirstName()).isEqualTo("Hao");
        assertThat(personDto.getLastName()).isEqualTo("Deng");

        AddressDto addressDto = personDto.getAddressDto();
        assertThat(addressDto).isNotNull();
        assertThat(addressDto.getStreet()).isEqualTo("street 1");
        assertThat(addressDto.getCity()).isEqualTo("KBH");
        assertThat(addressDto.getPostCode()).isEqualTo("2000");

        assertThat(personDto.getLanguages()).containsExactlyInAnyOrder("CHINESE", "DANISH");
    }

    @Test
    public void shouldMapPersonDtoToPerson() {
        // given:
        PersonDto personDto = new PersonDto(
                "Hao",
                "Deng",
                new AddressDto("street 1", "KBH", "2000"),
                Arrays.asList("CHINESE", "DANISH"));

        // when:
        Person person = PersonMapper.INSTANCE.personDtoToPerson(personDto);

        // then:
        assertThat(person).isNotNull();
        assertThat(person.getFirstName()).isEqualTo("Hao");
        assertThat(person.getLastName()).isEqualTo("Deng");

        Address address = person.getAddress();
        assertThat(address).isNotNull();
        assertThat(address.getStreet()).isEqualTo("street 1");
        assertThat(address.getCity()).isEqualTo("KBH");
        assertThat(address.getPostCode()).isEqualTo("2000");

        assertThat(person.getLanguages()).containsExactlyInAnyOrder(Language.CHINESE, Language.DANISH);
    }
}
