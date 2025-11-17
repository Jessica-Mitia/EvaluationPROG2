import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EtudiantTest {

    @Test
    public void testGetExamGrade_withHistoryUsesLatestValue() {
        Etudiant etu = new Etudiant(1, "Doe", "John", LocalDate.of(2000,1,1), "a@b.com", "000", "G1", null);

        // Note with initial value 10 and two history entries
        HistoriqueNote h1 = new HistoriqueNote(Instant.parse("2020-01-01T00:00:00Z"), 12.0, "correction1");
        HistoriqueNote h2 = new HistoriqueNote(Instant.parse("2020-02-01T00:00:00Z"), 14.0, "correction2");
        Note note = new Note(etu, 10.0, List.of(h1, h2));

        Cours cours = new Cours(1, Cours.Label.PROG2, 3, null, List.of());
        Examen exam = new Examen(1, "Exam1", cours, Instant.parse("2020-03-01T00:00:00Z"), 1, List.of(note));
        cours.setExamens(List.of(exam));

        // time after both history entries
        Instant t = Instant.parse("2020-03-02T00:00:00Z");

        double result = etu.getExamGrade(exam, etu, t);

        assertEquals(14.0, result, 1e-9);
    }

    @Test
    public void testGetCourseGrade_calculatesWeightedAverage() {
        Etudiant etu = new Etudiant(2, "Smith", "Anna", LocalDate.of(2001,5,5), "s@x.com", "111", "G2", null);

        // First exam: coefficient 2, note 12
        Note note1 = new Note(etu, 12.0, List.of());
        Cours cours = new Cours(2, Cours.Label.PROG2, 4, null, List.of());
        Examen exam1 = new Examen(2, "ExamA", cours, Instant.parse("2021-01-01T00:00:00Z"), 2, List.of(note1));

        // Second exam: coefficient 1, note 6
        Note note2 = new Note(etu, 6.0, List.of());
        Examen exam2 = new Examen(3, "ExamB", cours, Instant.parse("2021-02-01T00:00:00Z"), 1, List.of(note2));

        cours.setExamens(List.of(exam1, exam2));

        Instant t = Instant.parse("2021-03-01T00:00:00Z");

        double result = etu.getCourseGrade(cours, etu, t);

        // (12*2 + 6*1) / (2+1) = 10.0
        assertEquals(10.0, result, 1e-9);
    }

    @Test
    public void testGetCourseGrade_noExamsThrows() {
        Etudiant etu = new Etudiant(3, "X", "Y", LocalDate.of(1999,1,1), "x@y.com", "222", "G3", null);
        Cours cours = new Cours(3, Cours.Label.WEB1, 2, null, List.of());

        Instant t = Instant.now();

        assertThrows(IllegalArgumentException.class, () -> etu.getCourseGrade(cours, etu, t));
    }
}
