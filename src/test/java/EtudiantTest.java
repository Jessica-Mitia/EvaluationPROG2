import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EtudiantTest {

    Etudiant etudiant;
    Etudiant etudiant2;
    Enseignant enseignant;

    Note note1;
    Note note2;

    HistoriqueNote historiqueNote1;
    HistoriqueNote historiqueNote2;

    Examen examen1;
    Examen examen2;

    Cours cours;
    GradeService gradeService;

    @BeforeEach
    public void setUp() {
        etudiant = new Etudiant(1, "Fitia", "Princia", null,
                "princia@gmail.com", "0335648948", "k1", null);

        etudiant2 = new Etudiant(2, "Lovamanitra", "Mario", null,
                "mario@gmail.com", "0334512398", "k1", null);

        enseignant = new Enseignant(1, "Ramarozaka", "Toky", null,
                "toky@gmail.com", "0325698475", Enseignant.Specialite.BACKEND);

        historiqueNote1 = new HistoriqueNote(Instant.parse("2025-01-25T14:00:00Z"), 18, "ajout bonus");
        historiqueNote2 = new HistoriqueNote(Instant.parse("2025-02-25T14:00:00Z"), 19, "ajout bonus");

        note1 = new Note(etudiant, 15, List.of(historiqueNote1));
        note2 = new Note(etudiant2, 16, List.of(historiqueNote2));

        examen1 = new Examen(1, "First exam", null,
                Instant.parse("2025-01-01T14:00:00Z"), 2, List.of(note1, note2));

        examen2 = new Examen(2, "Second exam", null,
                Instant.parse("2025-02-01T14:00:00Z"), 3, List.of(note1, note2));

        cours = new Cours(1, Cours.Label.PROG1, 5, enseignant, List.of(examen1, examen2));

        examen1.setCours(cours);
        examen2.setCours(cours);

        gradeService = new GradeService();
    }

    @Test
    public void getExamGradeTest() {
        double firstValue = gradeService.getExamGrade(examen1, etudiant,
                Instant.parse("2025-01-20T14:00:00Z"));

        double lastValue = gradeService.getExamGrade(examen1, etudiant,
                Instant.parse("2025-02-01T14:00:00Z"));

        assertEquals(15, firstValue);
        assertEquals(18, lastValue);
    }

    @Test
    public void getCoursGradeTest() {
        double result = gradeService.getCourseGrade(cours, etudiant,
                Instant.parse("2025-01-20T14:00:00Z"));

        double expected = ((15 * 2) + (15 * 3)) / 5.0;

        assertEquals(expected, result);
    }
}
