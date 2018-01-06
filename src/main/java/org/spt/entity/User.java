package org.spt.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.spt.common.AbstractAuditingEntity;

@Entity
@Table(name = "user")
public class User extends AbstractAuditingEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @NotNull
  @Column(name = "first_name", length = 75)
  private String firstName;

  @NotNull
  @Column(name = "last_name", length = 80)
  private String lastName;

  @NotNull
  @Column(name = "username", length = 65)
  private String username;

  @NotNull
  @Column(name = "password", length = 64)
  private String password;

  @Transient
  private String confmPassword;

  @NotNull
  @Column(name = "email", unique = true, length = 115)
  private String email;

  @ManyToMany
  @JoinTable(name = "user_role")
  private Collection<Role> role;

  @Column(name = "actived")
  private boolean actived;

  public User() {

  }

  public User(String firstName, String lastName, String username, String password, Collection<Role> role,
      boolean actived) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
    this.role = role;
    this.actived = actived;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
  public Collection<Role> getRole() {
    return role;
  }

  public void setRole(Collection<Role> role) {
    this.role = role;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isActived() {
    return actived;
  }

  public void setActived(boolean actived) {
    this.actived = actived;
  }

  public String getConfmPassword() {
    return confmPassword;
  }

  public void setConfmPassword(String confmPassword) {
    this.confmPassword = confmPassword;
  }

}