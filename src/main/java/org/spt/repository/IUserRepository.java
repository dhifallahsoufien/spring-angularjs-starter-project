package org.spt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.spt.entity.User;

public interface IUserRepository extends JpaRepository<User, Long> {

  public Page<User> findByActived(boolean actived, Pageable p);

  public User findByUsername(String username);

  public User findByEmail(String email);

}
