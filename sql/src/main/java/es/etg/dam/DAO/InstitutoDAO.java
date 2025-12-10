package es.etg.dam.DAO;

import java.sql.SQLException;
import java.util.List;

public interface InstitutoDAO {

    // MÉTODOS PARA LA GESTIÓN DE ALUMNOS

    public void crearTablaAlumno() throws Exception;

    public void eliminarTablaAlumno() throws Exception;

    public List<Alumno> listarAlumnos() throws SQLException;

    public int insertarAlumno(Alumno a) throws SQLException;

    public int insertarAlumnos(List<Alumno> alumnos) throws SQLException;

    public int actualizarAlumno(Alumno a) throws SQLException;

    public int borrarAlumno(Alumno a) throws SQLException;

    // MÉTODOS PARA LA GESTIÓN DE ASISTENCIAS

    public void crearTablaFalta() throws Exception;

    public void eliminarTablaFalta() throws Exception;

    public List<Falta> listarFaltas() throws SQLException;

    public List<Falta> listarFaltasPorAlumno(int alumnoId) throws SQLException;

    public int insertarFalta(Falta f) throws SQLException;

    public int insertarFaltas(List<Falta> faltas) throws SQLException;

    public int actualizarFalta(Falta f) throws SQLException;

    public int borrarFalta(Falta f) throws SQLException;
}