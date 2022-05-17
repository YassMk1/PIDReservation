package be.iccbxl.pid.reservationsSpringBoot.service;

import be.iccbxl.pid.reservationsSpringBoot.model.Artist;
import be.iccbxl.pid.reservationsSpringBoot.repository.ArtistRepository;
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
}
