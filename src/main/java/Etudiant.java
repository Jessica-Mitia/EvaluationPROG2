import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class Etudiant extends Personne{
    private String groupe;
    private Tuteur tuteur;

    public Etudiant(int id, String nom, String prenom, LocalDate dateNaissance, String email, String telephone, String groupe, Tuteur tuteur) {
        super(id, nom, prenom, dateNaissance, email, telephone);
        this.groupe = groupe;
        this.tuteur = tuteur;
    }
}
