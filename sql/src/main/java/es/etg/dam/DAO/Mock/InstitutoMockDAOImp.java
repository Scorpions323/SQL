package es.etg.dam.DAO.Mock;

import java.sql.SQLException;
import java.util.List;

import es.etg.dam.DAO.Alumno;
import es.etg.dam.DAO.InstitutoDAO;
import es.etg.dam.DAO.Profesor;

public class InstitutoMockDAOImp implements InstitutoDAO {

    // TABLA ALUMNOS

    @Override
    public void crearTablaAlumno() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearTablaAlumno'");
    }

    @Override
    public void eliminarTablaAlumno() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearTablaAlumno'");
    }

    @Override
    public List<Alumno> listarAlumnos() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearTablaAlumno'");
    }

    @Override
    public int insertarAlumno(Alumno a) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearTablaAlumno'");
    }

    @Override
    public int insertarAlumnos(List<Alumno> alumnosList) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearTablaAlumno'");
    }

    @Override
    public int actualizarAlumno(Alumno a) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearTablaAlumno'");
    }

    @Override
    public int borrarAlumno(Alumno a) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearTablaAlumno'");
    }

    // TABLA PROFESORES

    @Override
    public void crearTablaProfesor() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearTablaAlumno'");
    }

    @Override
    public void eliminarTablaProfesor() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearTablaAlumno'");
    }

    @Override
    public int insertarProfesor(Profesor p) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearTablaAlumno'");
    }

    @Override
    public int insertarProfesores(List<Profesor> profesoresList) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearTablaAlumno'");
    }

    @Override
    public int actualizarProfesor(Profesor p) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearTablaAlumno'");
    }

    @Override
    public int borrarProfesor(Profesor p) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearTablaAlumno'");
    }

    @Override
    public List<Profesor> listarProfesores() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearTablaAlumno'");
    }

    @Override
    public List<Profesor> listarProfesoresPorAlumno(int alumnoId) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearTablaAlumno'");
    }

    @Override
    public Profesor buscarProfesorPorId(int id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearTablaAlumno'");
    }
}
