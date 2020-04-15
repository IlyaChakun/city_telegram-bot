package by.chekun.repository;

import by.chekun.config.AppTestConfig;
import by.chekun.model.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = AppTestConfig.class)
public class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void test_findByName_correct() {
        City minsk = new City();
        minsk.setId(1L);
        minsk.setName("Минск");
        minsk.setInfo("Город-герой.");
        City moscow = new City();
        moscow.setId(2L);
        moscow.setName("Москва");
        moscow.setInfo("Не забудьте посетить Красную Площадь. Ну а в ЦУМ можно и не заходить)");

        cityRepository.save(minsk);
        cityRepository.save(moscow);

        Optional<City> result = cityRepository.findByName("Москва");
        assertTrue(result.isPresent());
    }


    @Test
    public void test_findByNameContainingIgnoreCase_correct() {
        City minsk = new City();
        minsk.setId(1L);
        minsk.setName("Минск");
        minsk.setInfo("Город-герой.");
        City moscow = new City();
        moscow.setId(2L);
        moscow.setName("Москва");
        moscow.setInfo("Не забудьте посетить Красную Площадь. Ну а в ЦУМ можно и не заходить)");

        cityRepository.save(minsk);
        cityRepository.save(moscow);

        Optional<City> result = cityRepository.findByNameContainingIgnoreCase("моСква");
        assertTrue(result.isPresent());
    }

}
