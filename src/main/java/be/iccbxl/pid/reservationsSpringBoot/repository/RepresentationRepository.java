package be.iccbxl.pid.reservationsSpringBoot.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import be.iccbxl.pid.reservationsSpringBoot.model.Location;
import be.iccbxl.pid.reservationsSpringBoot.model.Representation;
import be.iccbxl.pid.reservationsSpringBoot.model.Show;

public interface RepresentationRepository extends CrudRepository<Representation, Long> {
	List<Representation> findByShow(Show show);
	List<Representation> findByLocation(Location location);
	List<Representation> findByWhen(LocalDateTime when);
}

