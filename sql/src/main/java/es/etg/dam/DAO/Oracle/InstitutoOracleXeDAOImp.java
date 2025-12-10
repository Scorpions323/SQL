package es.etg.dam.DAO.Oracle;

import java.sql.SQLException;
import java.util.List;

import es.etg.dam.DAO.Alumno;
import es.etg.dam.DAO.Falta;
import es.etg.dam.DAO.InstitutoDAO;

public class InstitutoOracleXeDAOImp implements InstitutoDAO {

    // ALUMNO
    @Override
    public void crearTablaAlumno() throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'crearTablaAlumno'");
    }

    @Override
    public void eliminarTablaAlumno() throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'eliminarTablaAlumno'");
    }

    @Override
    public List<Alumno> listarAlumnos() throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'listarAlumnos'");
    }

    @Override
    public int insertarAlumno(Alumno a) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'insertarAlumno'");
    }

    @Override
    public int insertarAlumnos(List<Alumno> alumnos) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'insertarAlumnos'");
    }

    @Override
    public int actualizarAlumno(Alumno a) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'actualizarAlumno'");
    }

    @Override
    public int borrarAlumno(Alumno a) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'borrarAlumno'");
    }

    // FALTA
    @Override
    public void crearTablaFalta() throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'crearTablaFalta'");
    }

    @Override
    public void eliminarTablaFalta() throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'eliminarTablaFalta'");
    }

    @Override
    public List<Falta> listarFaltas() throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'listarFaltas'");
    }

    @Override
    public List<Falta> listarFaltasPorAlumno(int alumnoId) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'listarFaltasPorAlumno'");
    }

    @Override
    public int insertarFalta(Falta f) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'insertarFalta'");
    }

    @Override
    public int insertarFaltas(List<Falta> faltas) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'insertarFaltas'");
    }

    @Override
    public int actualizarFalta(Falta f) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'actualizarFalta'");
    }

    @Override
    public int borrarFalta(Falta f) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'borrarFalta'");
    }
}
