package org.spt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.spt.entity.Etudiant;

public interface IEtudiantRepository extends JpaRepository<Etudiant, Long> {

}
