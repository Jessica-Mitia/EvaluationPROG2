import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class Cours {
    public enum Label {
        PROG1, PROG2, PROG3, WEB1, WEB2
    }

    private int id;
    private Label label;
    private int credit;
    private Enseignant enseignant;
}
