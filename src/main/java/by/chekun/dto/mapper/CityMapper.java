package by.chekun.dto.mapper;

import by.chekun.dto.CityDto;
import by.chekun.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityMapper extends AbstractMapper<City, CityDto> {

    @Autowired
    public CityMapper() {
        super(City.class, CityDto.class);
    }

}