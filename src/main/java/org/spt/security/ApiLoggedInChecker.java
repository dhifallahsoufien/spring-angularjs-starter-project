package org.spt.security;

import static java.util.Objects.nonNull;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class ApiLoggedInChecker {

  public User getLoggedInUser() {
    User user = null;
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
      Object principal = authentication.getPrincipal();
        user = (User) principal;
    }
    return user;
  }

  public String getLoggedInUsername() {
    User user = getLoggedInUser();
    return nonNull(user) ? user.getUsername().toUpperCase() : null;
  }
}