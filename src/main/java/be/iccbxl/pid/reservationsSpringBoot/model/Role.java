package be.iccbxl.pid.reservationsSpringBoot.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@Entity
@Table(name="roles")
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String role;

	@ManyToMany
	@JoinTable(
			name = "user_role",
			joinColumns = @JoinColumn(name = "role_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> users = new ArrayList<>();

	public Role addUser(User user) {
		if(!this.users.contains(user)) {
			this.users.add(user);
			user.addRole(this);
		}

		return this;
	}

	public Role removeUser(User user) {
		if(this.users.contains(user)) {
			this.users.remove(user);
			user.getRoles().remove(this);
		}

		return this;
	}

}
