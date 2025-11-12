import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class Enseignant extends Personne{
    public enum Specialite {
        FRONTEND, BACKEND, DATA
    }

    private Specialite specialite;

    public Enseignant(int id, String nom, String prenom, LocalDate dateNaissance, String email, String telephone, Specialite specialite) {
        super(id, nom, prenom, dateNaissance, email, telephone);
        this.specialite = specialite;
    }


}
