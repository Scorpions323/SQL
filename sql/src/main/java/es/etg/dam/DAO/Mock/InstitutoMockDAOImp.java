package es.etg.dam.DAO.Mock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.etg.dam.DAO.Alumno;
import es.etg.dam.DAO.Falta;
import es.etg.dam.DAO.InstitutoDAO;

public class InstitutoMockDAOImp implements InstitutoDAO {

    private final List<Alumno> alumnos = new ArrayList<>();
    private final List<Falta> faltas = new ArrayList<>();

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

    // FALTA
    @Override
    public void crearTablaFalta() throws Exception {
        faltas.clear();
    }

    @Override
    public void eliminarTablaFalta() throws Exception {
        faltas.clear();
    }

    @Override
    public List<Falta> listarFaltas() throws SQLException {
        return faltas;
    }

    @Override
    public List<Falta> listarFaltasPorAlumno(int alumnoId) throws SQLException {
        List<Falta> resultado = new ArrayList<>();
        for (Falta f : faltas) {
            if (f.getAlumnoId() == alumnoId) {
                resultado.add(f);
            }
        }
        return resultado;
    }

    @Override
    public int insertarFalta(Falta f) throws SQLException {
        faltas.add(f);
        return 1;
    }

    @Override
    public int insertarFaltas(List<Falta> listaFaltas) throws SQLException {
        faltas.addAll(listaFaltas);
        return listaFaltas.size();
    }

    @Override
    public int actualizarFalta(Falta f) throws SQLException {
        for (int i = 0; i < faltas.size(); i++) {
            if (faltas.get(i).getId() == f.getId()) {
                faltas.set(i, f);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int borrarFalta(Falta f) throws SQLException {
        return faltas.remove(f) ? 1 : 0;
    }
}
