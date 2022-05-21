package be.iccbxl.pid.reservationsSpringBoot.repository;

import java.util.Optional;

import be.iccbxl.pid.reservationsSpringBoot.model.Type;
import org.springframework.data.repository.CrudRepository;

public interface TypeRepository extends CrudRepository<Type, Long> {
	Type findByType(String type);
	Optional<Type> findById(Long id);
}
