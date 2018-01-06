package org.spt.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.spt.common.AbstractAuditingEntity;

@Entity
public class Etudiant extends AbstractAuditingEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue
  private Long idEtudiant;
  @NotNull
  private String nom;
  @NotNull
  private String prenom;
  @NotNull
  private Date dateNaissance;

  public Etudiant() {
    super();
    // TODO Auto-generated constructor stub
  }

  public Etudiant(String nom, String prenom, Date dateNaissance) {
    super();
    this.nom = nom;
    this.prenom = prenom;
    this.dateNaissance = dateNaissance;
  }

  public Long getIdEtudiant() {
    return idEtudiant;
  }

  public void setIdEtudiant(Long idEtudiant) {
    this.idEtudiant = idEtudiant;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public Date getDateNaissance() {
    return dateNaissance;
  }

  public void setDateNaissance(Date dateNaissance) {
    this.dateNaissance = dateNaissance;
  }

}
