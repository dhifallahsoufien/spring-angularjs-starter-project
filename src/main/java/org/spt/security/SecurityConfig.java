package org.spt.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  public void globalConfig(AuthenticationManagerBuilder authenticationManagerBuilder,DataSource dataSource) throws Exception {
    /*
     * authenticationManagerBuilder.inMemoryAuthentication().withUser("admin").
     * password("admin").roles("ADMIN", "PROF");
     * authenticationManagerBuilder.inMemoryAuthentication().withUser("user").
     * password("123").roles("PROF");
     * authenticationManagerBuilder.inMemoryAuthentication().withUser("et1").
     * password("et1").roles("ETUDIANT");
     * authenticationManagerBuilder.inMemoryAuthentication().withUser("scol1").
     * password("scol1").roles("SCOLARITE");
     */
    authenticationManagerBuilder.jdbcAuthentication().dataSource(dataSource)
    .usersByUsernameQuery("select username as principal, password as credentials, true from user where username=?")
    .authoritiesByUsernameQuery("select user.username as principal, role.role_name as role from user user,user_role us, role role where user.username = ? and user.id = us.user_id and us.role_id = role.id")
        .rolePrefix("ROLE_");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests()
        .antMatchers("/bt-sp-v3.3.4_dev/**", "/css/**", "/js/**", "/angular/**", "/images/**", "/resetpassword**",
            "/users**")
        .permitAll().anyRequest()
        .authenticated()
        .and()
        .formLogin().loginPage("/login")
        .permitAll().defaultSuccessUrl("/").failureUrl("/erreur.html").and().logout()
        .invalidateHttpSession(true).logoutUrl("/logout").permitAll();
  }
}
