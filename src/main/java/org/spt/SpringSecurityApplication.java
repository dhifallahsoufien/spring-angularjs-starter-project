package org.spt;

import java.text.ParseException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.spt.entity.Etudiant;
import org.spt.repository.IEtudiantRepository;

@SpringBootApplication
public class SpringSecurityApplication {


  public static void main(String[] args) throws ParseException {
    ApplicationContext applicationContext = SpringApplication.run(SpringSecurityApplication.class, args);
    IEtudiantRepository etudiantRepository = applicationContext.getBean(IEtudiantRepository.class);
    /*
     * DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
     * etudiantRepository.save(new Etudiant("Soufien", "DHifallah",
     * df.parse("18/08/1984"))); etudiantRepository.save(new Etudiant("Hatem",
     * "DHifallah", df.parse("10/08/1990"))); etudiantRepository.save(new
     * Etudiant("Wafa", "DHifallah", df.parse("01/01/1989")));
     */
    List<Etudiant> le = etudiantRepository.findAll();
    le.forEach(e -> System.out.println(e.getNom()));

	}
}
