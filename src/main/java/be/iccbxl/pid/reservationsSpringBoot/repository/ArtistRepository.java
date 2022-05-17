package be.iccbxl.pid.reservationsSpringBoot.repository;

import be.iccbxl.pid.reservationsSpringBoot.model.Artist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArtistRepository extends CrudRepository<Artist, Long> {

    List<Artist> findByLastname(String lastname);

    List<Artist> findAll();
}
