import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@Getter
@Setter

public class HistoriqueNote {
    private Instant debut;
    private double nouvelleValeur;
    private String raison;
}
