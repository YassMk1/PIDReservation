package be.iccbxl.pid.reservationsSpringBoot.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import be.iccbxl.pid.reservationsSpringBoot.model.Location;
import be.iccbxl.pid.reservationsSpringBoot.model.Show;

public interface ShowRepository extends CrudRepository<Show, Long> {
	Show findBySlug(String slug);
	Show findByTitle(String title);
	List<Show> findByLocation(Location location);

}
