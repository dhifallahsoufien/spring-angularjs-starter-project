package org.spt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.spt.entity.EmailTemplate;

public interface IEmailTemplateRepository extends JpaRepository<EmailTemplate, Long> {

  EmailTemplate findByNameAndLocale(String name, String locale);

  EmailTemplate findByName(String name);
}
