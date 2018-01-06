package org.spt.email.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import javax.mail.internet.InternetAddress;

public class Email {

  private InternetAddress from;
  private InternetAddress replyTo;
  private Collection<InternetAddress> to;
  private Collection<InternetAddress> cc;
  private Collection<InternetAddress> bcc;
  private String subject = "";
  private String body = "";
  private Locale locale;
  private Date sentAt;
  private Collection<EmailAttachment> attachments = new ArrayList<>();
  private String encoding;
  private String content;

  public static Email instance() {
    return new Email();
  }

  public InternetAddress from() {
    return from;
  }

  public Email from(InternetAddress from) {
    this.from = from;
    return this;
  }

  public InternetAddress replyTo() {
    return replyTo;
  }

  public Email replyTo(InternetAddress replyTo) {
    this.replyTo = replyTo;
    return this;
  }

  public Collection<InternetAddress> to() {
    return to;
  }

  public Email to(Collection<InternetAddress> to) {
    this.to = to;
    return this;
  }

  public Collection<InternetAddress> cc() {
    return cc;
  }

  public Email cc(Collection<InternetAddress> cc) {
    this.cc = cc;
    return this;
  }

  public Collection<InternetAddress> bcc() {
    return bcc;
  }

  public Email bcc(Collection<InternetAddress> bcc) {
    this.bcc = bcc;
    return this;
  }

  public String subject() {
    return subject;
  }

  public Email subject(String subject) {
    this.subject = subject;
    return this;
  }

  public String body() {
    return body;
  }

  public Email body(String body) {
    this.body = body;
    return this;
  }

  public Locale locale() {
    return locale;
  }

  public Email locale(Locale locale) {
    this.locale = locale;
    return this;
  }

  public Date sentAt() {
    return sentAt;
  }

  public Email sentAt(Date sentAt) {
    this.sentAt = sentAt;
    return this;
  }

  public Collection<EmailAttachment> attachments() {
    return attachments;
  }

  public Email attachments(Collection<EmailAttachment> attachments) {
    this.attachments = attachments;
    return this;
  }

  public String encoding() {
    return encoding;
  }

  public Email encoding(String encoding) {
    this.encoding = encoding;
    return this;
  }

  public String content() {
    return content;
  }

  public Email content(String content) {
    this.content = content;
    return this;
  }

  public InternetAddress getFrom() {
    return from;
  }

  public void setFrom(InternetAddress from) {
    this.from = from;
  }

  public InternetAddress getReplyTo() {
    return replyTo;
  }

  public void setReplyTo(InternetAddress replyTo) {
    this.replyTo = replyTo;
  }

  public Collection<InternetAddress> getTo() {
    return to;
  }

  public void setTo(Collection<InternetAddress> to) {
    this.to = to;
  }

  public Collection<InternetAddress> getCc() {
    return cc;
  }

  public void setCc(Collection<InternetAddress> cc) {
    this.cc = cc;
  }

  public Collection<InternetAddress> getBcc() {
    return bcc;
  }

  public void setBcc(Collection<InternetAddress> bcc) {
    this.bcc = bcc;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Locale getLocale() {
    return locale;
  }

  public void setLocale(Locale locale) {
    this.locale = locale;
  }

  public Date getSentAt() {
    return sentAt;
  }

  public void setSentAt(Date sentAt) {
    this.sentAt = sentAt;
  }

  public Collection<EmailAttachment> getAttachments() {
    return attachments;
  }

  public void setAttachments(Collection<EmailAttachment> attachments) {
    this.attachments = attachments;
  }

  public String getEncoding() {
    return encoding;
  }

  public void setEncoding(String encoding) {
    this.encoding = encoding;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
