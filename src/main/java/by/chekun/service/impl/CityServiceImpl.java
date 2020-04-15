package by.chekun.service.impl;

import by.chekun.dto.CityDto;
import by.chekun.dto.CityPartialDto;
import by.chekun.dto.mapper.CityMapper;
import by.chekun.model.City;
import by.chekun.repository.CityRepository;
import by.chekun.service.CityService;
import by.chekun.service.exception.ResourceNotFoundException;
import by.chekun.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository,
                           CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }


    @Override
    @Transactional
    public CityDto save(CityDto cityDto) {
        cityRepository.findByName(cityDto.getName())
                .ifPresent(city -> {
                    throw new ServiceException("City with name=" + city.getName() + " is already present! Just modify previous version!");
                });

        City city = cityMapper.toEntity(cityDto);

        return cityMapper.toDto(cityRepository.save(city));
    }

    @Override
    @Transactional
    public CityDto update(CityDto cityDto) {
        City city = cityRepository.findById(cityDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(cityDto.getId()));

        city.setName(cityDto.getName());
        city.setInfo(cityDto.getInfo());

        return cityMapper.toDto(city);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        cityRepository.delete(city);
    }

    @Override
    @Transactional
    public CityDto partialUpdate(Long id, CityPartialDto cityPartialDto) {
        City city =
                cityRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(id));
        resolveNewFields(city, cityPartialDto);

        return cityMapper.toDto(city);
    }

    private void resolveNewFields(City city, CityPartialDto cityPartialDto) {
        if (Objects.nonNull(cityPartialDto.getName())) {
            city.setName(cityPartialDto.getName());
        }
        if (Objects.nonNull(cityPartialDto.getInfo())) {
            city.setInfo(cityPartialDto.getInfo());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CityDto> findAll() {
        List<City> cities = cityRepository.findAll();

        return cityMapper.toDtoList(cities);
    }

    @Override
    @Transactional(readOnly = true)
    public CityDto findById(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return cityMapper.toDto(city);
    }

    @Override
    @Transactional(readOnly = true)
    public CityDto findByName(String name) {
        City city = cityRepository.findByNameContainingIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("City with name=" + name + " does not exist!"));

        return cityMapper.toDto(city);
    }

    @Override
    @Transactional(readOnly = true)
    public CityDto findRandomCity() {
        Long id = getRandomId();
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Random breaks.."));

        return cityMapper.toDto(city);
    }

    private Long getRandomId() {
        if (cityRepository.count() == 0) {
            throw new ServiceException("Actually storage is empty. Sorry");
        }

        long countAll = cityRepository.count();
        long bound = countAll + 1;
        final long origin = 1;
        return ThreadLocalRandom.current().nextLong(origin, bound);
    }
}
