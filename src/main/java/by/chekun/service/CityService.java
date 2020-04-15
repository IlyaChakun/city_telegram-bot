package by.chekun.service;

import by.chekun.dto.CityDto;
import by.chekun.dto.CityPartialDto;

import java.util.List;

public interface CityService {

    CityDto save(CityDto cityDto);

    CityDto update(CityDto cityDto);

    CityDto partialUpdate(Long id, CityPartialDto cityPartialDto);

    void delete(Long id);

    List<CityDto> findAll();

    CityDto findById(Long id);

    CityDto findByName(String name);

    CityDto findRandomCity();
}
