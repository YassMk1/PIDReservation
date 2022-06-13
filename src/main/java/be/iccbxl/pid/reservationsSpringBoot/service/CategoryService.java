package be.iccbxl.pid.reservationsSpringBoot.service;

import be.iccbxl.pid.reservationsSpringBoot.model.Category;
import be.iccbxl.pid.reservationsSpringBoot.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository repository;


  public List<Category> getAll() {
    List<Category> categories = new ArrayList<>();

    repository.findAll().forEach(categories::add);

    return categories;
  }

  public Category get(String id) {
    Long indice = Long.parseLong(id);
    Optional<Category> category = repository.findById(indice);

    return category.isPresent() ? category.get() : null;
  }

  public void add(Category category) {
    repository.save(category);
  }

  public void update(String id, Category category) {
    repository.save(category);
  }

  public void delete(String id) {
    Long indice = (long) Integer.parseInt(id);

    repository.deleteById(indice);
  }
}
