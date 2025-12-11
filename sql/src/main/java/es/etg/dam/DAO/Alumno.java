package es.etg.dam.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Alumno {

    private int id;
    private String nombre;
    private String apellido;
    private int edad;
}
