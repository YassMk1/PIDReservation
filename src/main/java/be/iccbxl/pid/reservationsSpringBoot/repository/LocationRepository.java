package be.iccbxl.pid.reservationsSpringBoot.repository;

import java.util.Optional;

import be.iccbxl.pid.reservationsSpringBoot.model.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Long> {
    Location findByDesignation(String designation);
    Optional<Location> findById(Long id);
}
