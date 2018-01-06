package org.spt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.spt.entity.Role;

public interface IRoleRepository extends JpaRepository<Role, Long> {

}
