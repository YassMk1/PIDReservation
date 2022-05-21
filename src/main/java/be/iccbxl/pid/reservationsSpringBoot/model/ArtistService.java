package be.iccbxl.pid.reservationsSpringBoot.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

	public Artist getArtist(String id) {
		// TODO Auto-generated method stub
		return artistRepository.findById(Long.parseLong(id)).orElse(null);
	}
}
