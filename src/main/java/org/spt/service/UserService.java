package org.spt.service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.spt.utils.Constants.SPT_MAIL_SENDER_MAIL;
import static org.spt.utils.Constants.SPT_MAIL_SENDER_NAME;
import static org.spt.utils.Constants.TEMPLATE_FORGOT_PASSWORD;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.spt.email.api.Email;
import org.spt.email.api.service.EmailService;
import org.spt.entity.EmailTemplate;
import org.spt.entity.User;
import org.spt.helper.TemplateHelper;
import org.spt.repository.IUserRepository;
import org.spt.utils.Constants;

import com.google.common.collect.Lists;




@Service
public class UserService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);


  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private EmailTemplateService emailTemplateService;

  @Autowired
  private EmailService emailService;

  public void sendEmailToUser(User user) {
    EmailTemplate emailTemplate = emailTemplateService.findByNameAndLocale(TEMPLATE_FORGOT_PASSWORD, "French");
    if (isNull(emailTemplate)) {
      LOGGER.warn("email template [{}] not yet defined, mail will not be sent.", TEMPLATE_FORGOT_PASSWORD);
      return;
    }
    try {
      if (isNull(user)) {
        LOGGER.warn("contact point of establishment with identifier [{}] not yet defined, mail will not be sent.",
            user.getId());
        return;
      }
      Map<String, String> parameters = new HashMap<>();
      parameters.put("username", user.getUsername());
      parameters.put("email", user.getEmail());
      String body = TemplateHelper.merge(emailTemplate.getBody(), parameters, Constants.DOLLAR_DELIMITER);
      Email email = Email.instance().from(new InternetAddress(SPT_MAIL_SENDER_MAIL, SPT_MAIL_SENDER_NAME))
          .to(Lists.newArrayList(new InternetAddress(user.getEmail()))).subject(emailTemplate.getSubject())
          .body(body);
      emailService.send(email);
    } catch (Exception e) {
      LOGGER.warn("error occurred when trying to send email to [" + user.getId() + "].", e);
    }
  }

  public User resetPasswordUser(String userNameOrEmail) {
    User user = userRepository.findByUsername(userNameOrEmail);
    if (isNull(user)) {
      user = userRepository.findByEmail(userNameOrEmail);
    }
    if (nonNull(user)) {
      sendEmailToUser(user);
    }
    return user;
  }

}
