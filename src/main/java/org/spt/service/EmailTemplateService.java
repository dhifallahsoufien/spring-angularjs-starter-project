package org.spt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.spt.entity.EmailTemplate;
import org.spt.repository.IEmailTemplateRepository;

@Service
public class EmailTemplateService {

  @Autowired
  private IEmailTemplateRepository emailTemplateRepository;

  public EmailTemplate findByNameAndLocale(String name, String locale) {
    return emailTemplateRepository.findByNameAndLocale(name, locale);
  }

  public EmailTemplate findByName(String name) {
    return emailTemplateRepository.findByName(name);
  }

}
