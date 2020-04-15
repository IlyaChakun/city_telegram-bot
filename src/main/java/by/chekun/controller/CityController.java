package by.chekun.controller;

import by.chekun.controller.exception.IllegalRequestException;
import by.chekun.dto.CityDto;
import by.chekun.dto.CityPartialDto;
import by.chekun.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }


    @PostMapping
    public ResponseEntity<CityDto> save(@RequestBody @Valid CityDto cityDto,
                                        BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result.getFieldErrors());
        }

        CityDto savedCity = cityService.save(cityDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedCity.getId()).toUri());

        return new ResponseEntity<>(
                savedCity,
                httpHeaders,
                HttpStatus.CREATED);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<CityDto> updateCertificateName(@PathVariable Long id,
                                                         @RequestBody @Valid CityPartialDto cityPartialDto,
                                                         BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result.getFieldErrors());
        }
        return new ResponseEntity<>(
                cityService.partialUpdate(id, cityPartialDto),
                HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long cityId) {
        cityService.delete(cityId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDto> update(@PathVariable(name = "id") Long cityId,
                                          @RequestBody @Valid CityDto cityDto,
                                          BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result.getFieldErrors());
        }

        cityDto.setId(cityId);
        CityDto updatedCity = cityService.update(cityDto);

        return new ResponseEntity<>(
                updatedCity,
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> findById(@PathVariable(name = "id") Long cityId) {
        CityDto cityDto = cityService.findById(cityId);

        return new ResponseEntity<>(
                cityDto,
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CityDto>> findAll() {
        List<CityDto> cities = cityService.findAll();

        return new ResponseEntity<>(
                cities,
                HttpStatus.OK);
    }


}
