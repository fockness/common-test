/*
package com.example.demo.cases.mapstruct;

import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.Test;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Date;

public class MapStructTest {

    @Test
    public void testMapStruct(){
        PersonDO one = new PersonDO().setAge(1).setId(2).setBirthday(new Date()).setName("222").setGender(Gender.ONE.name());
        PersonDTO personDTO = PersonConverter.INSTANCE.do2dto(one);
        System.out.println(personDTO);
    }
}

@Mapper
interface PersonConverter{

    PersonConverter INSTANCE = Mappers.getMapper(PersonConverter.class);

    @Mappings(@Mapping(source = "name", target = "userName"))
    PersonDTO do2dto(PersonDO person);
}

@Data
@Accessors(chain = true)
class PersonDO {
    private Integer id;
    private String name;
    private int age;
    private Date birthday;
    private String gender;
}

@Data
@Accessors(chain = true)
class PersonDTO {
    private String userName;
    private Integer age;
    private Date birthday;
    private Gender gender;
}

enum Gender{
    ONE("one", "1"),
    TWO("two", "2"),
    ;
    private String type;
    private String value;

    Gender(String type, String value){
        this.type = type;
        this.value = value;
    }
}
*/
