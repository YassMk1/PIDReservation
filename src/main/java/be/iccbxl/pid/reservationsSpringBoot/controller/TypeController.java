package be.iccbxl.pid.reservationsSpringBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import be.iccbxl.pid.reservationsSpringBoot.model.Type;
import be.iccbxl.pid.reservationsSpringBoot.service.TypeService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class TypeController {
  @Autowired
  TypeService service;

  @GetMapping("/types")
  public String index2(Model model) {
    List<Type> types = service.getAll();

    model.addAttribute("types", types);
    model.addAttribute("title", "Liste des types");

    return "type/index";
  }

  @GetMapping("/types/{id}")
  public String show(Model model, @PathVariable("id") String id) {
    Type type = service.getType(id);

    model.addAttribute("type", type);
    model.addAttribute("title", "Fiche d'un type");

    return "type/show";
  }

  @GetMapping("/types/create")
  public String create(Model model) {
    Type type = new Type(null);

    model.addAttribute("type", type);

    return "type/create";
  }

  @PostMapping("/types/create")
  public String store(@Valid @ModelAttribute("type") Type type, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
      return "type/create";
    }

    service.addType(type);

    return "redirect:/types/" + type.getId();
  }

  @GetMapping("/types/{id}/edit")
  public String edit(Model model, @PathVariable("id") String id, HttpServletRequest request) {
    Type type = service.getType(id);

    model.addAttribute("mytype", type);


    //Générer le lien retour pour l'annulation
    String referrer = request.getHeader("Referer");

    if (referrer != null && !referrer.equals("")) {
      model.addAttribute("back", referrer);
    } else {
      model.addAttribute("back", "/types/" + type.getId());
    }

    return "type/edit";
  }

  @PutMapping("/types/{id}/edit")
  public String update(@Valid @ModelAttribute("mytype") Type type, BindingResult bindingResult, @PathVariable("id") String id, Model model) {

    if (bindingResult.hasErrors()) {
      return "type/edit";
    }

    Type existing = service.getType(id);

    if (existing == null) {
      return "type/index";
    }

    Long indice = (long) Integer.parseInt(id);

    type.setId(indice);
    service.updateType(id, type);

    model.addAttribute("type", type);

    return "redirect:/types/" + type.getId();
  }

  @DeleteMapping("/types/{id}")
  public String delete(@PathVariable("id") String id, Model model) {
    Type existing = service.getType(id);

    if (existing != null) {
      Long indice = (long) Integer.parseInt(id);

      service.deleteType(id);
    }

    return "redirect:/types";
  }

}

