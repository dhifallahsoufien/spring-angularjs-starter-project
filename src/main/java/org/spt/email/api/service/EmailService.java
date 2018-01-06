package org.spt.email.api.service;

import javax.mail.internet.MimeMessage;

import org.spt.email.api.Email;

public interface EmailService {
  MimeMessage send(Email email) throws Exception;

}
