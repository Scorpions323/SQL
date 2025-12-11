package es.etg.dam.DAO;

import java.sql.SQLException;
import java.util.List;

public interface InstitutoDAO {

    // METODOS PARA ALUMNOS
    void crearTablaAlumno() throws Exception;

    void eliminarTablaAlumno() throws Exception;

    List<Alumno> listarAlumnos() throws SQLException;

    int insertarAlumno(Alumno a) throws SQLException;

    int insertarAlumnos(List<Alumno> alumnos) throws SQLException;

    int actualizarAlumno(Alumno a) throws SQLException;

    int borrarAlumno(Alumno a) throws SQLException;

    // METODOS PARA PROFESORES
    void crearTablaProfesor() throws Exception;

    void eliminarTablaProfesor() throws Exception;

    List<Profesor> listarProfesores() throws SQLException;

    int insertarProfesor(Profesor p) throws SQLException;

    int insertarProfesores(List<Profesor> profesores) throws SQLException;

    int actualizarProfesor(Profesor p) throws SQLException;

    int borrarProfesor(Profesor p) throws SQLException;
}
