package by.chekun.repository;

import by.chekun.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findByName(String name);

    Optional<City> findByNameContainingIgnoreCase(String name);

}
