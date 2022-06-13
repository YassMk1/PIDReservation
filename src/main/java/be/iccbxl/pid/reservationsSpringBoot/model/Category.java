package be.iccbxl.pid.reservationsSpringBoot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(max = 10)
  private String name;

  @OneToMany(targetEntity = Show.class, mappedBy = "category", fetch = FetchType.EAGER)
  private List<Show> shows;

  public Category(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
