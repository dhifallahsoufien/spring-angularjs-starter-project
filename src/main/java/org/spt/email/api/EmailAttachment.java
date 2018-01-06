package org.spt.email.api;


public class EmailAttachment {

  private String attachmentName;
  private byte[] attachmentData;

  public String attachmentName() {
    return attachmentName;
  }

  public EmailAttachment attachmentName(String attachmentName) {
    this.attachmentName = attachmentName;
    return this;
  }

  public byte[] attachmentData() {
    return attachmentData;
  }

  public EmailAttachment attachmentData(byte[] attachmentData) {
    this.attachmentData = attachmentData;
    return this;
  }

}
