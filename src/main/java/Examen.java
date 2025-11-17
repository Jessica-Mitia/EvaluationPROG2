import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Examen {
    private int id;
    private String titre;
    private Cours cours;
    private Instant date;
    private int coefficient;
    private List<Note> notes;
}
