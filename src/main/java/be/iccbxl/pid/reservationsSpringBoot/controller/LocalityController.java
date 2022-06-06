package be.iccbxl.pid.reservationsSpringBoot.controller;


import java.util.List;

import be.iccbxl.pid.reservationsSpringBoot.model.Locality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import be.iccbxl.pid.reservationsSpringBoot.model.Locality;
import be.iccbxl.pid.reservationsSpringBoot.service.LocalityService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class LocalityController {
  @Autowired
  LocalityService service;

  @GetMapping("/localities")
  public String index2(Model model) {
    List<Locality> localities = service.getAll();

    model.addAttribute("localities", localities);
    model.addAttribute("title", "Liste des localités");

    return "locality/index";
  }

  @GetMapping("/localities/{id}")
  public String show(Model model, @PathVariable("id") String id) {
    Locality locality = service.get(id);  //??????

    model.addAttribute("mylocality", locality);
    model.addAttribute("title", "Fiche d'une localité");

    return "locality/show";
  }

  @GetMapping("/localities/create")
  public String create(Model model) {
    Locality locality = new Locality(null, null);

    model.addAttribute("mylocality", locality);

    return "locality/create";
  }

  @PostMapping("/localities/create")
  public String store(@Valid @ModelAttribute("mylocality") Locality locality, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
      return "locality/create";
    }

    service.add(locality);

    return "redirect:/localities/" + locality.getId();
  }

  @GetMapping("/localities/{id}/edit")
  public String edit(Model model, @PathVariable("id") String id, HttpServletRequest request) {
    Locality locality = service.get(id);

    model.addAttribute("mylocality", locality);


    //Générer le lien retour pour l'annulation
    String referrer = request.getHeader("Referer");

    if (referrer != null && !referrer.equals("")) {
      model.addAttribute("back", referrer);
    } else {
      model.addAttribute("back", "/localities/" + locality.getId());
    }

    return "locality/edit";
  }

  @PutMapping("/localities/{id}/edit")
  public String update(@Valid @ModelAttribute("mylocality") Locality locality, BindingResult bindingResult, @PathVariable("id") String id, Model model) {

    if (bindingResult.hasErrors()) {
      return "locality/edit";
    }

    Locality existing = service.get(id);

    if (existing == null) {
      return "locality/index";
    }

    Long indice = (long) Integer.parseInt(id);

    locality.setId(indice);
    service.update(id, locality);

    model.addAttribute("mylocality", locality);

    return "redirect:/localities/" + locality.getId();
  }

  @DeleteMapping("/localities/{id}")
  public String delete(@PathVariable("id") String id, Model model) {
    Locality existing = service.get(id);

    if (existing != null) {
      Long indice = (long) Integer.parseInt(id);

      service.delete(id);
    }

    return "redirect:/localities";
  }

}

