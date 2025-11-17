import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter

public class Note {
    private Etudiant etudiant;
    private double valeurInitiale;
    private List<HistoriqueNote> historiqueNotes;
}
