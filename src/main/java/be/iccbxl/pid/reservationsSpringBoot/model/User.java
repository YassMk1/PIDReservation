package be.iccbxl.pid.reservationsSpringBoot.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="users")
@Getter
@Setter
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String login;
  private String password;
  private String firstname;
  private String lastname;
  private String email;
  private String langue;
  private LocalDateTime created_at;

  @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
  private List<Role> roles = new ArrayList<>();

  @ManyToMany(mappedBy = "users")
  private List<Representation> representations = new ArrayList<>();

  protected User() {}

  public User(String login, String firstname, String lastname) {
    this.login = login;
    this.firstname = firstname;
    this.lastname = lastname;
    this.created_at = LocalDateTime.now();
  }

  public User addRole(Role role) {
    if(!this.roles.contains(role)) {
      this.roles.add(role);
      role.addUser(this);
    }

    return this;
  }

  public User removeRole(Role role) {
    if(this.roles.contains(role)) {
      this.roles.remove(role);
      role.getUsers().remove(this);
    }

    return this;
  }

  public User addRepresentation(Representation representation) {
    if(!this.representations.contains(representation)) {
      this.representations.add(representation);
      representation.addUser(this);
    }

    return this;
  }

  public User removeRepresentation(Representation representation) {
    if(this.representations.contains(representation)) {
      this.representations.remove(representation);
      representation.getUsers().remove(this);
    }

    return this;
  }


  @Override
  public String toString() {
    return login + "(" + firstname + " " + lastname + ")";
  }
}

