package be.iccbxl.pid.reservationsSpringBoot.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.reservationsSpringBoot.model.Location;
import be.iccbxl.pid.reservationsSpringBoot.model.Show;
import be.iccbxl.pid.reservationsSpringBoot.repository.ShowRepository;

@Service
public class ShowService {
	@Autowired
	private ShowRepository repository;
	
	
	public List<Show> getAll() {
		List<Show> shows = new ArrayList<>();
		
		repository.findAll().forEach(shows::add);
		
		return shows;
	}
	
	public Show get(String id) {
		Long indice = (long) Integer.parseInt(id);
		Optional<Show> show = repository.findById(indice); 
		
		return show.isPresent() ? show.get() : null;
	}

	public void add(Show show) {
		repository.save(show);
	}

	public void update(String id, Show show) {
		repository.save(show);
	}

	public void delete(String id) {
		Long indice = (long) Integer.parseInt(id);
		
		repository.deleteById(indice);
	}

	public List<Show> getFromLocation(Location location) {
		return repository.findByLocation(location);
	}
}


