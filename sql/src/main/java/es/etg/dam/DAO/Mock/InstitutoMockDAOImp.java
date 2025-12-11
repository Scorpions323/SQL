package es.etg.dam.DAO.Mock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.etg.dam.DAO.Alumno;
import es.etg.dam.DAO.InstitutoDAO;
import es.etg.dam.DAO.Profesor;

public class InstitutoMockDAOImp implements InstitutoDAO {

    private final List<Alumno> alumnos = new ArrayList<>();
    private final List<Profesor> profesores = new ArrayList<>();

    // ALUMNO
    @Override
    public void crearTablaAlumno() throws Exception {
        alumnos.clear();
    }

    @Override
    public void eliminarTablaAlumno() throws Exception {
        alumnos.clear();
    }

    @Override
    public List<Alumno> listarAlumnos() throws SQLException {
        return alumnos;
    }

    @Override
    public int insertarAlumno(Alumno a) throws SQLException {
        alumnos.add(a);
        return 1;
    }

    @Override
    public int insertarAlumnos(List<Alumno> listaAlumnos) throws SQLException {
        alumnos.addAll(listaAlumnos);
        return listaAlumnos.size();
    }

    @Override
    public int actualizarAlumno(Alumno a) throws SQLException {
        for (int i = 0; i < alumnos.size(); i++) {
            if (alumnos.get(i).getId() == a.getId()) {
                alumnos.set(i, a);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int borrarAlumno(Alumno a) throws SQLException {
        return alumnos.remove(a) ? 1 : 0;
    }

    // PROFESOR
    @Override
    public void crearTablaProfesor() throws Exception {
        profesores.clear();
    }

    @Override
    public void eliminarTablaProfesor() throws Exception {
        profesores.clear();
    }

    @Override
    public List<Profesor> listarProfesores() throws SQLException {
        return profesores;
    }

    @Override
    public int insertarProfesor(Profesor p) throws SQLException {
        profesores.add(p);
        return 1;
    }

    @Override
    public int insertarProfesores(List<Profesor> listaProfesores) throws SQLException {
        profesores.addAll(listaProfesores);
        return listaProfesores.size();
    }

    @Override
    public int actualizarProfesor(Profesor p) throws SQLException {
        for (int i = 0; i < profesores.size(); i++) {
            if (profesores.get(i).getId() == p.getId()) {
                profesores.set(i, p);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int borrarProfesor(Profesor p) throws SQLException {
        return profesores.remove(p) ? 1 : 0;
    }
}
