package es.etg.dam.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Falta {
    private int id;
    private int alumnoId;
    private String dia;
    private boolean justificada;
}
