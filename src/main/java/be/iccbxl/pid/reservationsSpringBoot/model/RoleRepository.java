package be.iccbxl.pid.reservationsSpringBoot.model;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByRole(String role);
}

