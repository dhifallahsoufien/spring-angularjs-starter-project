package org.spt.email.api.service.impl;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.spt.common.DateHelper;
import org.spt.email.api.Email;
import org.spt.email.api.service.EmailService;
import org.spt.email.api.transformer.EmailToMimeMessage;


@Service
public class EmailServiceImpl implements EmailService {

  @Autowired
  private JavaMailSender javaMailSender;

  @Override
  public MimeMessage send(Email email) {
    email.setSentAt(DateHelper.now());
    final MimeMessage mimeMessage = EmailToMimeMessage.instance(javaMailSender).apply(email);
    javaMailSender.send(mimeMessage);
    return mimeMessage;
  }

}
