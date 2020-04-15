package by.chekun.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CityDto extends AbstractDto {

    @NotBlank(message = "City name can`t be null and spaces!")
    @Length(min = 2, max = 64, message = "City name should be less than 64 and bigger than 2 characters")
    private String name;
    @NotBlank(message = "City info can`t be null and spaces!")
    @Length(min = 2, max = 512, message = "City info should be less than 512 and bigger then 2 characters")
    private String info;

    public CityDto() {
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

    @Override
    public String toString() {
        return name + " " + info;
    }
}
