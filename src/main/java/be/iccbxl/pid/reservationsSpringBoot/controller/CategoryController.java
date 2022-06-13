package be.iccbxl.pid.reservationsSpringBoot.controller;


import be.iccbxl.pid.reservationsSpringBoot.model.Category;
import be.iccbxl.pid.reservationsSpringBoot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class CategoryController {
  @Autowired
  CategoryService service;

  @GetMapping("/categories")
  public String index2(Model model) {
    List<Category> categories = service.getAll();

    model.addAttribute("categories", categories);
    model.addAttribute("title", "Liste des categories");

    return "category/index";
  }

  @GetMapping("/categories/{id}")
  public String show(Model model, @PathVariable("id") String id) {
    Category category = service.get(id);  //??????

    model.addAttribute("category", category);
    model.addAttribute("title", "Fiche d'une categorie");

    return "category/show";
  }

  @GetMapping("/categories/create")
  public String create(Model model) {
    Category category = new Category(null);

    model.addAttribute("category", category);

    return "category/create";
  }

  @PostMapping("/categories/create")
  public String store(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
      return "category/create";
    }

    service.add(category);

    return "redirect:/categories/" + category.getId();
  }

  @GetMapping("/categories/{id}/edit")
  public String edit(Model model, @PathVariable("id") String id, HttpServletRequest request) {
    Category category = service.get(id);

    model.addAttribute("category", category);


    //Générer le lien retour pour l'annulation
    String referrer = request.getHeader("Referer");

    if (referrer != null && !referrer.equals("")) {
      model.addAttribute("back", referrer);
    } else {
      model.addAttribute("back", "/categories/" + category.getId());
    }

    return "category/edit";
  }

  @PutMapping("/categories/{id}/edit")
  public String update(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult, @PathVariable("id") String id, Model model) {

    if (bindingResult.hasErrors()) {
      return "category/edit";
    }

    Category existing = service.get(id);

    if (existing == null) {
      return "category/index";
    }

    Long indice = (long) Integer.parseInt(id);

    category.setId(indice);
    service.update(id, category);

    model.addAttribute("category", category);

    return "redirect:/categories/" + category.getId();
  }

  @DeleteMapping("/categories/{id}")
  public String delete(@PathVariable("id") String id, Model model) {
    Category existing = service.get(id);

    if (existing != null) {
      Long indice = (long) Integer.parseInt(id);

      service.delete(id);
    }

    return "redirect:/categories";
  }

}

