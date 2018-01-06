package org.spt.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.spt.entity.Role;
import org.spt.entity.User;
import org.spt.repository.IRoleRepository;
import org.spt.repository.IUserRepository;

@RestController
@Secured(value = { "ROLE_ADMIN" })
public class RoleResource {

  @Autowired
  private IRoleRepository roleRepository;

  @Autowired
  private IUserRepository userRepository;

  @RequestMapping(value = "/addRole")
  public Role save(Role role) {
    return roleRepository.save(role);
  }

  @RequestMapping(value = "/roles")
  public List<Role> findRoles() {
    return roleRepository.findAll();
  }

  @RequestMapping(value = "/addRoleToUser")
  public User addRoleToUser(Long idRole, Long idUser) {
    User user = userRepository.findOne(idUser);
    Role role = roleRepository.findOne(idRole);
    user.getRole().add(role);
    return userRepository.save(user);
  }
}
