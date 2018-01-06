package org.spt.resource;

import static org.spt.utils.Constants.ERRORS;
import static org.spt.utils.Constants.ID_PATH;
import static org.spt.utils.Constants.ROLE_ADMIN;
import static org.spt.utils.Constants.ROLE_PROF;
import static org.spt.utils.Constants.ROLE_SCOLARITE;
import static org.spt.utils.Constants.USER_PATH;
import static org.spt.utils.Constants.USER_PATH_ALL;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.spt.entity.User;
import org.spt.repository.IUserRepository;
import org.spt.security.ApiLoggedInChecker;
import org.spt.service.UserService;

@RestController
public class UserResource {

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  UserService userService;

  @Autowired
  private ApiLoggedInChecker loggedInChecker;

  @Secured({ ROLE_ADMIN })
  @RequestMapping(path = USER_PATH, method = RequestMethod.POST)
  public Object saveUser(@RequestBody @Valid User user, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      Map<String, Object> errors = bindingResult.getFieldErrors().stream()
          .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
      errors.put("errors", true);
      return errors;
    }else {
      if (!user.getConfmPassword().equals(user.getPassword())) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("confmPassword", "Les mots de passe ne correspondent pas. Voulez-vous réessayer ?");
        errors.put(ERRORS, true);
        return errors;
      }
    }
    user.getRole().stream().forEach(r -> System.out.println(r.getRoleName()));
    user.setCreatedBy(loggedInChecker.getLoggedInUsername());
    user.setLastModifiedBy(loggedInChecker.getLoggedInUsername());
    return userRepository.save(user);
  }

  @Secured({ ROLE_ADMIN })
  @RequestMapping(path = USER_PATH, method = RequestMethod.PUT)
  public Object updateUser(@RequestBody @Valid User user, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      Map<String, Object> errors = bindingResult.getFieldErrors().stream()
          .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
      errors.put(ERRORS, true);
      return errors;
    } else {
      if (!user.getConfmPassword().equals(user.getPassword())) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("confmPassword", "Les mots de passe ne correspondent pas. Voulez-vous réessayer ?");
        errors.put(ERRORS, true);
        return errors;
      }
    }
    user.getRole().stream().forEach(r -> System.out.println(r.getRoleName()));
    user.setLastModifiedBy(loggedInChecker.getLoggedInUsername());
    user.setLastModifiedDate(new Date());
    return userRepository.save(user);
  }

  @Secured({ ROLE_ADMIN, ROLE_PROF })
  @RequestMapping(path = USER_PATH_ALL)
  public Page<User> listUser(@RequestParam int page, @RequestParam int size) {
    return userRepository.findAll(new PageRequest(page, size));
  }

  @Secured({ ROLE_ADMIN })
  @RequestMapping(path = USER_PATH + ID_PATH, method = RequestMethod.DELETE)
  public void deleteUser(@PathVariable Long id) {
    userRepository.delete(id);
  }

  @Secured({ ROLE_ADMIN, ROLE_PROF, ROLE_SCOLARITE })
  @RequestMapping(path = USER_PATH + ID_PATH, method = RequestMethod.GET)
  public User getUser(@PathVariable Long id) {
    return userRepository.findOne(id);
  }

  @RequestMapping(path = USER_PATH, method = RequestMethod.GET)
  public User resetPassword(@RequestParam String loginOrPassword) {
    return userService.resetPasswordUser(loginOrPassword);
  }

}
