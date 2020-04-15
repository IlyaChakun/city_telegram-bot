package by.chekun.dto;

import by.chekun.service.validation.CustomNullableNotBlank;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CityPartialDto {

    @CustomNullableNotBlank(message = "Name can`t be spaces!")
    @Length(min = 2, max = 64, message = "City name should be less than 64 and bigger than 2 characters")
    private String name;
    @CustomNullableNotBlank(message = "City info can`t be spaces!")
    @Length(min = 2, max = 512, message = "City info should be less than 512 and bigger then 2 characters")
    private String info;

    public CityPartialDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
