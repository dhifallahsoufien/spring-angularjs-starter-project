package org.spt.resource;

import static org.spt.utils.Constants.ERRORS;
import static org.spt.utils.Constants.ETUDIANT_PATH;
import static org.spt.utils.Constants.ID_PATH;
import static org.spt.utils.Constants.ROLE_ADMIN;
import static org.spt.utils.Constants.ROLE_PROF;
import static org.spt.utils.Constants.ROLE_SCOLARITE;

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
import org.springframework.web.bind.annotation.RestController;
import org.spt.entity.Etudiant;
import org.spt.repository.IEtudiantRepository;
import org.spt.security.ApiLoggedInChecker;

@RestController
public class EtudiantResource {

  @Autowired
  private IEtudiantRepository etudiantRepository;

  @Autowired
  private ApiLoggedInChecker loggedInChecker;

  @Secured({ ROLE_ADMIN, ROLE_PROF })
  @RequestMapping(path = ETUDIANT_PATH, method = RequestMethod.POST)
  public Object saveEtudiant(@RequestBody @Valid Etudiant e, BindingResult bindingResult) {
    if(bindingResult.hasErrors()) {
      Map<String, Object> errors =
          bindingResult.getFieldErrors().stream()
              .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
      errors.put(ERRORS, true);
      return errors;
    }
    e.setCreatedBy(loggedInChecker.getLoggedInUsername());
    e.setLastModifiedBy(loggedInChecker.getLoggedInUsername());
    return etudiantRepository.save(e);
  }

  @RequestMapping(path = ETUDIANT_PATH)
  public Page<Etudiant> listEtudiants(int page, int size) {
    return etudiantRepository.findAll(new PageRequest(page, size));
  }

  @Secured({ ROLE_ADMIN, ROLE_PROF, ROLE_SCOLARITE })
  @RequestMapping(path = ETUDIANT_PATH + ID_PATH, method = RequestMethod.DELETE)
  public void deleteEtudiant(@PathVariable Long id) {
    etudiantRepository.delete(id);
  }

}
