package be.iccbxl.pid.reservationsSpringBoot.controller;

import be.iccbxl.pid.reservationsSpringBoot.model.Artist;
import be.iccbxl.pid.reservationsSpringBoot.service.ArtistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ArtistController {

    @Autowired
    ArtistService service;

    @GetMapping("/artists")
    public String index(Model model) {

        List<Artist> artists = service.getAllArtists();

        model.addAttribute("artists", artists);
        model.addAttribute("title", "Liste des artistes");

        return "artist/index";
    }
    
    @GetMapping("/artists/{id}")
    public String show(Model model, @PathVariable("id") String id) {
	Artist artist = service.getArtist(id);

	model.addAttribute("artist", artist);
	model.addAttribute("title", "Fiche d'un artiste");
		
        return "artist/show";
    }


}
