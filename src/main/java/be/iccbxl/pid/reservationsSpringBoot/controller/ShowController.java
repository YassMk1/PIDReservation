package be.iccbxl.pid.reservationsSpringBoot.controller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import be.iccbxl.pid.reservationsSpringBoot.model.*;
import be.iccbxl.pid.reservationsSpringBoot.service.ShowService;



@Controller
public class ShowController {
	@Autowired
	ShowService service;

	@GetMapping("/shows")
    	public String index(Model model) {
		List<Show> shows = service.getAll();

		model.addAttribute("shows", shows);
		model.addAttribute("title", "Liste des spectacles");
		
        	return "show/index";
    	}
	
	@GetMapping("/shows/{id}")
   	 public String show(Model model, @PathVariable("id") String id) {
		Show show = service.get(id);

		//Récupérer les artistes du spectacle et les grouper par type
        Map<Type,ArrayList<ArtistType>> collaborateurs = new HashMap<>(); //TreeMap require Type to be comparable
        
        for(ArtistType at : show.getArtistTypes()) {
            if(collaborateurs.get(at.getType()) == null) {
            	collaborateurs.put(at.getType(), new ArrayList<>());
            }
            
            collaborateurs.get(at.getType()).add(at);
        }
        
        model.addAttribute("collaborateurs", collaborateurs);

		model.addAttribute("show", show);
		model.addAttribute("title", "Fiche d'un spectacle");
		
        	return "show/show";
    	}
	
		@GetMapping("/shows/create")
		public String create(Model model) {
	    Show show = new Show(null, null, null, null, false, 0);
	    

	    model.addAttribute("show", show);

	    return "show/create";
	  }
	
		@PostMapping("/shows/create")
		public String store(@Valid @ModelAttribute("show") Show show, BindingResult bindingResult, Model model) {

	    if (bindingResult.hasErrors()) {
	      return "show/create";
	    }

	    service.add(show);

	    return "redirect:/shows/" + show.getId();
	  }

}
