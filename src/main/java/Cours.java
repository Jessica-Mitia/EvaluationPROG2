import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter

public class Cours {
    public enum Label {
        PROG1, PROG2, PROG3, WEB1, WEB2
    }

    private int id;
    private Label label;
    private int credit;
    private Enseignant enseignant;
    private List<Examen> examens;
}
