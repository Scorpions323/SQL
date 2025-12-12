package es.etg.dam.DAO;

import java.sql.SQLException;
import java.util.List;

public interface InstitutoDAO {

    // METODOS PARA ALUMNOS
    public void crearTablaAlumno() throws Exception;

    public void eliminarTablaAlumno() throws Exception;

    public List<Alumno> listarAlumnos() throws SQLException;

    public int insertarAlumno(Alumno a) throws SQLException;

    public int insertarAlumnos(List<Alumno> alumnos) throws SQLException;

    public int actualizarAlumno(Alumno a) throws SQLException;

    public int borrarAlumno(Alumno a) throws SQLException;

    // METODOS PARA PROFESORES
    public void crearTablaProfesor() throws Exception;

    public void eliminarTablaProfesor() throws Exception;

    public int insertarProfesor(Profesor p) throws SQLException;

    public int insertarProfesores(List<Profesor> profesores) throws SQLException;

    public int actualizarProfesor(Profesor p) throws SQLException;

    public int borrarProfesor(Profesor p) throws SQLException;

    public List<Profesor> listarProfesores() throws SQLException;

    public List<Profesor> listarProfesoresPorAlumno(int alumnoId) throws SQLException;

    public Profesor buscarProfesorPorId(int id) throws SQLException;
}
