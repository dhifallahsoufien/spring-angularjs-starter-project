package org.spt.email.api.transformer;

import static com.google.common.base.Optional.fromNullable;
import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

import java.nio.charset.StandardCharsets;
import java.util.function.Function;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.spt.email.api.Email;
import org.spt.email.api.EmailAttachment;



public class EmailToMimeMessage implements Function<Email, MimeMessage> {

  private JavaMailSender javaMailSender;

  private EmailToMimeMessage() {

  }

  public static EmailToMimeMessage instance(JavaMailSender javaMailSender) {
    return new EmailToMimeMessage().withSender(javaMailSender);
  }

  private EmailToMimeMessage withSender(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
    return this;
  }

  @Override
  public MimeMessage apply(Email email) {
    final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    final boolean isMultipart = nonNull(email.getAttachments()) && !email.getAttachments().isEmpty();

    try {
      MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, isMultipart,
          fromNullable(email.getEncoding()).or(StandardCharsets.UTF_8.name()));

      messageHelper.setFrom(email.getFrom());
      if (nonNull(email.getReplyTo())) {
        messageHelper.setReplyTo(email.getReplyTo());
      }
      if (nonNull(email.getTo())) {
        for (InternetAddress address : email.getTo()) {
          messageHelper.addTo(address);
        }
      }
      if (nonNull(email.getCc())) {
        for (InternetAddress address : email.getCc()) {
          messageHelper.addCc(address);
        }
      }
      if (nonNull(email.getBcc())) {
        for (InternetAddress address : email.getBcc()) {
          messageHelper.addBcc(address);
        }
      }

      if (isMultipart) {
        for (EmailAttachment attachment : email.getAttachments()) {
          messageHelper.addAttachment(attachment.attachmentName(), new ByteArrayResource(attachment.attachmentData()));
        }
      }
      messageHelper.setSubject(ofNullable(email.getSubject()).orElse(""));
      messageHelper.setText(ofNullable(email.getBody()).orElse(""));

      if (nonNull(email.getSentAt())) {
        messageHelper.setSentDate(email.getSentAt());
      }

      if (nonNull(email.content())) {
        final MimeMultipart content = new MimeMultipart("mixed");
        final MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText(email.getBody(), email.getEncoding(), "html");
        content.addBodyPart(textPart);
        mimeMessage.setContent(content);
      }

    } catch (MessagingException e) {
      // throw new ConversionException("error occurred when trying to convert
      // EmailDto to MimeMessage", e);
      e.printStackTrace();
    }

    return mimeMessage;
  }
}
