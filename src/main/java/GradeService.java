import java.time.Instant;
import java.util.Comparator;
import java.util.List;

public class GradeService {
    public double getExamGrade(Examen examen, Etudiant etudiant, Instant t) {
        return examen.getNotes()
                .stream()
                .filter(note -> note.getEtudiant().equals(etudiant))
                .findFirst()
                .map(note -> note.getHistoriqueNotes().stream()
                        .filter(historique -> historique.getDebut().isBefore(t))
                        .max(Comparator.comparing(HistoriqueNote::getDebut))
                        .map(HistoriqueNote::getNouvelleValeur)
                        .orElse(note.getValeurInitiale()))
                .orElse(0.0);
    }

    public double getCourseGrade(Cours cours, Etudiant etudiant, Instant t) {
        List<Examen> examens = cours.getExamens();

        int totalCoef = 0;
        double totalNote = 0.0;

        for (Examen examen : examens) {
            double note = getExamGrade(examen, etudiant, t);
            totalNote += note * examen.getCoefficient();
            totalCoef += examen.getCoefficient();
        }

        if (totalCoef == 0) {
            throw new IllegalArgumentException("Aucun examen pour ce cours.");
        }

        return totalNote / totalCoef;
    }
}
