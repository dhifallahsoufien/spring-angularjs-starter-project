package org.spt.email.api.configuration;

import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MailSenderAutoConfiguration.class)
public class EmailConfiguration {

}
